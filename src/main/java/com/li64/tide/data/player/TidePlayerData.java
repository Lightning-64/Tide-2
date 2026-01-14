package com.li64.tide.data.player;

import com.li64.tide.Tide;
import com.li64.tide.TideConfig;
import com.li64.tide.data.TideCriteriaTriggers;
import com.li64.tide.data.TideData;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.item.TideItemData;
import com.li64.tide.network.messages.SyncPlayerDataMsg;
import com.li64.tide.util.TideUtils;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TidePlayerData {
    public static TidePlayerData CLIENT_DATA = new TidePlayerData();
    private static final String NBT_TAG = "TidePlayerData";

    public boolean gotJournal;
    public Map<Holder<Item>, FishPlayerData> fishPlayerData;

    public TidePlayerData() {
        this(new CompoundTag());
    }

    public TidePlayerData(CompoundTag tag) {
        this.gotJournal = tag.getBoolean("got_journal");

        // parse fish data tag
        ListTag fishList = tag.getList("fish_data", 10);
        this.fishPlayerData = new HashMap<>(fishList.size());
        fishList.forEach(t -> {
            if (!(t instanceof CompoundTag fishTag) || !fishTag.contains("fish")) return;
            Holder<Item> item = BuiltInRegistries.ITEM.wrapAsHolder(BuiltInRegistries.ITEM.get(
                    ResourceLocation.tryParse(fishTag.getString("fish"))));
            fishPlayerData.put(item, FishPlayerData.readFrom(fishTag, "data").orElse(new FishPlayerData()));
        });
    }

    public static TidePlayerData getOrCreate(ServerPlayer player) {
        return getOrCreate(Tide.PLATFORM.getPlayerData(player));
    }

    public static TidePlayerData getOrCreate(CompoundTag tag) {
        if (!tag.contains(NBT_TAG)) tag.put(NBT_TAG, new CompoundTag());
        return new TidePlayerData(tag.getCompound(NBT_TAG));
    }

    public CompoundTag getAsTag() {
        CompoundTag tag = new CompoundTag();

        ListTag fishList = new ListTag();
        fishPlayerData.forEach((item, data) -> {
            CompoundTag fishTag = new CompoundTag();
            fishTag.putString("fish", BuiltInRegistries.ITEM.getKey(item.value()).toString());
            data.saveTo(fishTag, "data");
            fishList.add(fishTag);
        });

        tag.put("fish_data", fishList);
        tag.putBoolean("got_journal", gotJournal);
        return tag;
    }

    public void syncTo(ServerPlayer player) {
        CompoundTag data = Tide.PLATFORM.getPlayerData(player);
        CompoundTag playerData = this.getAsTag();
        data.put(NBT_TAG, playerData);
        Tide.NETWORK.sendToPlayer(new SyncPlayerDataMsg(playerData), player);
    }

    public Optional<FishPlayerData> getDataFor(Item item) {
        return Optional.ofNullable(fishPlayerData.get(BuiltInRegistries.ITEM.wrapAsHolder(item)));
    }

    public boolean isFishUnlocked(Holder<Item> holder) {
        return Optional.ofNullable(fishPlayerData.get(holder))
                .map(data -> data.isUnlocked)
                .orElse(false);
    }

    public boolean isFishUnlocked(ItemStack stack) {
        return fishPlayerData.entrySet().stream()
                .filter(entry -> stack.is(entry.getKey()))
                .anyMatch(entry -> entry.getValue().isUnlocked);
    }

    public boolean unlockFish(Holder<Item> fish, ServerPlayer player) {
        if (!fishPlayerData.containsKey(fish)) {
            fishPlayerData.put(fish, new FishPlayerData(true, true, Optional.empty()));
            this.checkJournalCompletion(player);
            return true;
        }

        FishPlayerData data = fishPlayerData.get(fish);
        if (data.isUnlocked) return false;

        data.isUnlocked = true;
        data.isUnread = true;
        return true;
    }

    public void markAsRead(ItemStack stack) {
        Holder<Item> fish = stack.getItemHolder();
        if (!fishPlayerData.containsKey(fish)) return;
        fishPlayerData.get(fish).isUnread = false;
    }

    public boolean isUnread(ItemStack stack) {
        Holder<Item> fish = stack.getItemHolder();
        if (!fishPlayerData.containsKey(fish)) return false;
        return fishPlayerData.get(fish).isUnlocked && fishPlayerData.get(fish).isUnread;
    }

    public void logCatch(ItemStack stack, ServerPlayer player, Level level) {
        FishData data = FishData.get(stack).orElse(null);
        if (data == null) return;

        Holder<Item> fish = data.fish();
        if (this.unlockFish(fish, player)) TideUtils.showFishToast(stack, player);

        FishPlayerData playerData = fishPlayerData.get(fish);
        FishStats stats = playerData == null ? new FishStats() : playerData.stats.orElse(new FishStats());

        if (data.size().isPresent()) {
            double fishLength = 0;
            if (TideItemData.FISH_LENGTH.isPresent(stack))
                fishLength = TideItemData.FISH_LENGTH.getOrDefault(stack, 0.0);
            else if (Tide.CONFIG.items.fishItemSizes == TideConfig.Items.SizeMode.IN_JOURNAL)
                fishLength = data.getRandomLength(level.random);
            stats.logCatch(CatchTimestamp.now(level), fishLength);
        }

        if (playerData == null) fishPlayerData.put(stack.getItemHolder(), new FishPlayerData(stats));
        else fishPlayerData.get(fish).stats = Optional.of(stats);
    }

    private void checkJournalCompletion(ServerPlayer player) {
        int amountCompleted = fishPlayerData.size();
        int total = TideData.FISH.journalEntryCount();
        float ratio = (float) amountCompleted / total;
        if (ratio >= 0.5f) TideCriteriaTriggers.JOURNAL_HALFWAY_COMPLETE.trigger(player);
        if (ratio >= 1.0f) TideCriteriaTriggers.JOURNAL_COMPLETE.trigger(player);
    }

    public static class FishPlayerData {
        public static final Codec<FishPlayerData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.BOOL.fieldOf("is_unlocked").forGetter(data -> data.isUnlocked),
                Codec.BOOL.fieldOf("is_unread").forGetter(data -> data.isUnread),
                FishStats.CODEC.optionalFieldOf("stats").forGetter(data -> data.stats)
        ).apply(instance, FishPlayerData::new));

        public boolean isUnlocked;
        public boolean isUnread;
        public Optional<FishStats> stats;

        public FishPlayerData() {
            this(false, false, Optional.empty());
        }

        public FishPlayerData(FishStats stats) {
            this(false, false, Optional.of(stats));
        }

        public FishPlayerData(boolean isUnlocked, boolean isUnread, Optional<FishStats> stats) {
            this.isUnlocked = isUnlocked;
            this.isUnread = isUnread;
            this.stats = stats;
        }

        public void saveTo(CompoundTag tag, String key) {
            tag.put(key, CODEC.encode(this, NbtOps.INSTANCE, new CompoundTag()).result().orElseThrow());
        }

        public static Optional<FishPlayerData> readFrom(CompoundTag tag, String key) {
            try {
                return CODEC.decode(NbtOps.INSTANCE, tag.get(key)).result().map(Pair::getFirst);
            } catch (Exception e) {
                Tide.LOG.error("Error reading fish player data! Journal data may be lost.", e);
                return Optional.empty();
            }
        }
    }
}

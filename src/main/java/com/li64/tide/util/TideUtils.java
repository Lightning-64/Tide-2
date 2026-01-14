package com.li64.tide.util;

import com.li64.tide.Tide;
import com.li64.tide.data.TideLootTables;
import com.li64.tide.data.TideTags;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.item.TideItemData;
import com.li64.tide.data.player.TidePlayerData;
import com.li64.tide.network.messages.ShowToastMsg;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;

/*? if <1.21 {*/
/*import com.mojang.math.Divisor;
import it.unimi.dsi.fastutil.ints.IntIterator;
import net.minecraft.client.gui.GuiGraphics;
*///?}

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

public class TideUtils {
    public static boolean isJournalFish(ItemStack fish) {
        return isJournalFish(fish.getItem());
    }

    public static boolean isJournalFish(Item fish) {
        return FishData.get(fish).map(FishData::showInJournal).orElse(false);
    }

    public static Component removeRawTextInName(Component initialName) {
        String toReplace = Component.translatable("journal.remove_from_names").getString();
        String filtered = Component.translatable(initialName.getString()).getString().replace(toReplace, "");
        return Component.literal(filtered.substring(0, 1).toUpperCase() + filtered.substring(1));
    }

    public static void showFishToast(ItemStack stack, ServerPlayer player) {
        Tide.NETWORK.sendToPlayer(new ShowToastMsg(
                Component.translatable("newfish.toast.title"),
                TideUtils.removeRawTextInName(stack.getHoverName()),
                stack), player);
    }

    public static Item itemFromLocation(ResourceLocation location) {
        return BuiltInRegistries.ITEM.getOptional(location).orElse(
                BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getKey(Items.AIR)));
    }

    public static Optional<FishData> getStrongest(List<ItemStack> hookedItems) {
        float bestStrength = 0.0f;
        FishData best = null;

        for (ItemStack hookedItem : hookedItems) {
            Optional<FishData> data = FishData.get(hookedItem);
            if (data.isEmpty()) continue;

            float strength = data.get().strength();
            if (strength >= bestStrength) {
                bestStrength = strength;
                best = data.orElse(null);
            }
        }

        return Optional.ofNullable(best);
    }

    public static ItemStack getBonusGoldItem(MinecraftServer server, LootParams params) {
        LootTable table = TideLootTables.Fishing.BONUS_GOLD.getTable(server);
        if (table == LootTable.EMPTY) return null;
        return table.getRandomItems(params).get(0);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean tryLogCatch(ItemStack stack, ServerPlayer player) {
        if (!TideUtils.isJournalFish(stack)) return false;
        TidePlayerData data = TidePlayerData.getOrCreate(player);
        data.logCatch(stack, player, player.level());
        data.syncTo(player);
        return true;
    }

    public static Optional<Holder<Biome>> findClosestNonWaterBiome(Level level, BlockPos origin, int radius, int step) {
        int steps = Math.floorDiv(radius, step);
        int[] relativeYs = Mth.outFromOrigin(0, -radius, radius, step).toArray();

        for (BlockPos.MutableBlockPos mPos : BlockPos.spiralAround(BlockPos.ZERO, steps, Direction.EAST, Direction.SOUTH)) {
            int x = origin.getX() + mPos.getX() * step;
            int z = origin.getZ() + mPos.getZ() * step;
            int qx = QuartPos.fromBlock(x);
            int qz = QuartPos.fromBlock(z);

            for (int y : relativeYs) {
                int qy = QuartPos.fromBlock(y + origin.getY());
                Holder<Biome> biome = level.getNoiseBiome(qx, qy, qz);
                if (!biome.is(TideTags.Biomes.WATER_BIOMES)) return Optional.of(biome);
            }
        }

        return Optional.empty();
    }

    public static float getTemperatureAt(BlockPos pos, ServerLevel level) {
        Climate.Sampler sampler = level.getChunkSource().randomState().sampler();
        int x = QuartPos.fromBlock(pos.getX());
        int y = QuartPos.fromBlock(pos.getY());
        int z = QuartPos.fromBlock(pos.getZ());
        Climate.TargetPoint targetPoint = sampler.sample(x, y, z);
        return Climate.unquantizeCoord(targetPoint.temperature());
    }

    public static MutableComponent getFormattedLength(ItemStack stack) {
        return getFormattedLength(TideItemData.FISH_LENGTH.getOrDefault(stack, 0.0));
    }

    public static MutableComponent getFormattedLength(double length) {
        String formatted;
        if (length >= 100.0) {
            // m, two decimal places, no trailing zeros
            DecimalFormat metersFormat = new DecimalFormat("#.##");
            formatted = metersFormat.format(length / 100.0) + " m";
        } else {
            // cm, one decimal place, no trailing zeros
            DecimalFormat cmFormat = new DecimalFormat("#.#");
            formatted = cmFormat.format(length) + " cm";
        }
        return Component.literal(formatted);
    }

    public static boolean isFishAlive(ItemStack stack, long currentTime) {
        long timestamp = TideItemData.CATCH_TIMESTAMP.getOrDefault(stack, -1L);
        return timestamp >= 0L && currentTime - timestamp < Tide.CONFIG.items.fishItemLifespan * 20;
    }

    public static float mcTempToRealTemp(float mcTemp) {
        float celsius = (float)(11 * Math.pow(mcTemp - 0.23, 3) + 30 * mcTemp + 15);
        if (Tide.CONFIG.journal.useFahrenheit) return celsius * 1.8f + 32f;
        return celsius;
    }

    public static String ticksToRealTime(long time, boolean useAmPm) {
        double timeInHours = (time / 1000.0) + 6;
        if (timeInHours >= 24) timeInHours -= 24;

        int hour = (int) Math.floor(timeInHours);
        int minute = (int) Math.floor((timeInHours - hour) * 60);

        if (useAmPm) {
            String amPm = (hour < 12) ? "AM" : "PM";
            int hour12 = hour % 12;
            if (hour12 == 0) hour12 = 12;
            return String.format("%d:%02d %s", hour12, minute, amPm);
        }
        return String.format("%02d:%02d", hour, minute);
    }

    public static ResourceLocation sprite(String path) {
        /*? if >=1.21 {*/ return Tide.resource(path);
        /*?} else*//*return Tide.resource("textures/gui/sprites/" + path + ".png");*/
    }

    public static ResourceLocation holderId(Holder<Item> holder) {
        return holder.unwrap().map(ResourceKey::location, BuiltInRegistries.ITEM::getKey);
    }

    //? if >=1.21 {
    public static LootTable getLootTable(ResourceKey<LootTable> key, MinecraftServer server) {
        return server.reloadableRegistries().getLootTable(key);
    }
    //?} else {
    /*public static LootTable getLootTable(ResourceLocation id, MinecraftServer server) {
         return server.getLootData().getLootTable(id);
    }

    public static void blitNineSliced(GuiGraphics graphics, ResourceLocation tex, int x, int y, int width, int height, int sliceWidth, int sliceHeight, int uWidth, int vHeight, int textureX, int textureY, int textureWidth, int textureHeight) {
        blitNineSliced(graphics, tex, x, y, width, height, sliceWidth, sliceHeight, sliceWidth, sliceHeight, uWidth, vHeight, textureX, textureY, textureWidth, textureHeight);
    }

    public static void blitNineSliced(GuiGraphics graphics, ResourceLocation tex, int x, int y, int width, int height, int left, int top, int right, int bottom, int uWidth, int vHeight, int uOffset, int vOffset, int textureWidth, int textureHeight) {
        left = Math.min(left, width / 2);
        right = Math.min(right, width / 2);
        top = Math.min(top, height / 2);
        bottom = Math.min(bottom, height / 2);
        if (width == uWidth && height == vHeight) {
            graphics.blit(tex, x, y, uOffset, vOffset, width, height, textureWidth, textureHeight);
        } else if (height == vHeight) {
            graphics.blit(tex, x, y, uOffset, vOffset, left, height, textureWidth, textureHeight);
            blitRepeating(graphics, tex, x + left, y, width - right - left, height, uOffset + left, vOffset, uWidth - right - left, vHeight, textureWidth, textureHeight);
            graphics.blit(tex, x + width - right, y, uOffset + uWidth - right, vOffset, right, height, textureWidth, textureHeight);
        } else if (width == uWidth) {
            graphics.blit(tex, x, y, uOffset, vOffset, width, top, textureWidth, textureHeight);
            blitRepeating(graphics, tex, x, y + top, width, height - bottom - top, uOffset, vOffset + top, uWidth, vHeight - bottom - top, textureWidth, textureHeight);
            graphics.blit(tex, x, y + height - bottom, uOffset, vOffset + vHeight - bottom, width, bottom, textureWidth, textureHeight);
        } else {
            graphics.blit(tex, x, y, uOffset, vOffset, left, top, textureWidth, textureHeight);
            blitRepeating(graphics, tex, x + left, y, width - right - left, top, uOffset + left, vOffset, uWidth - right - left, top, textureWidth, textureHeight);
            graphics.blit(tex, x + width - right, y, uOffset + uWidth - right, vOffset, right, top, textureWidth, textureHeight);
            graphics.blit(tex, x, y + height - bottom, uOffset, vOffset + vHeight - bottom, left, bottom, textureWidth, textureHeight);
            blitRepeating(graphics, tex, x + left, y + height - bottom, width - right - left, bottom, uOffset + left, vOffset + vHeight - bottom, uWidth - right - left, bottom, textureWidth, textureHeight);
            graphics.blit(tex, x + width - right, y + height - bottom, uOffset + uWidth - right, vOffset + vHeight - bottom, right, bottom, textureWidth, textureHeight);
            blitRepeating(graphics, tex, x, y + top, left, height - bottom - top, uOffset, vOffset + top, left, vHeight - bottom - top, textureWidth, textureHeight);
            blitRepeating(graphics, tex, x + left, y + top, width - right - left, height - bottom - top, uOffset + left, vOffset + top, uWidth - right - left, vHeight - bottom - top, textureWidth, textureHeight);
            blitRepeating(graphics, tex, x + width - right, y + top, left, height - bottom - top, uOffset + uWidth - right, vOffset + top, right, vHeight - bottom - top, textureWidth, textureHeight);
        }
    }

    private static void blitRepeating(GuiGraphics graphics, ResourceLocation atlasLocation, int x, int y, int width, int height, int uOffset, int vOffset, int uWidth, int uHeight, int textureWidth, int textureHeight) {
        int i = x;
        int j;
        for(IntIterator intIterator = slices(width, uWidth); intIterator.hasNext(); i += j) {
            j = intIterator.nextInt();
            int k = (uWidth - j) / 2;
            int l = y;

            int m;
            for(IntIterator intIterator2 = slices(height, uHeight); intIterator2.hasNext(); l += m) {
                m = intIterator2.nextInt();
                int n = (uHeight - m) / 2;
                graphics.blit(atlasLocation, i, l, uOffset + k, vOffset + n, j, m, textureWidth, textureHeight);
            }
        }
    }

    private static IntIterator slices(int target, int total) {
        int i = Mth.positiveCeilDiv(target, total);
        return new Divisor(target, i);
    }
    *///?}
}

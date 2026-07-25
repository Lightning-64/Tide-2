package com.li64.tide.config;

import com.google.gson.Gson;
import com.li64.tide.Tide;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Config(name = Tide.MOD_ID + "_server")
public final class TideServerConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public General general;

    @ConfigEntry.Gui.CollapsibleObject
    public Items items;

    @ConfigEntry.Gui.CollapsibleObject
    public Journal journal;

    @ConfigEntry.Gui.CollapsibleObject
    public Minigame minigame;

    public TideServerConfig() {
        this.general = new General();
        this.items = new Items();
        this.journal = new Journal();
        this.minigame = new Minigame();
    }

    public static void writeToPacket(FriendlyByteBuf buf, Gson gson) {
        String json = gson.toJson(Tide.SERVER_CONFIG);
        buf.writeUtf(json);
        Tide.LOG.info("Config wrote to packet: {}", json);
    }

    public static void readFromPacket(FriendlyByteBuf buf, Gson gson) {
        String json = buf.readUtf();
        Tide.SERVER_CONFIG = gson.fromJson(json, TideServerConfig.class);
        Tide.LOG.info("Config synced to client: {}", json);
    }

    public static class General {
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.RequiresRestart
        public boolean overrideVanillaRod = true;

        @ConfigEntry.Gui.Tooltip
        public boolean holdToCast = true;

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.RequiresRestart
        public double rodDurabilityMultiplier = 1.0;

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.RequiresRestart
        public List<VoidHeightEntry> fishableVoidHeights = List.of(
                new VoidHeightEntry(Level.OVERWORLD, VoidHeightEntry.Type.RELATIVE_TO_BOTTOM, -6),
                new VoidHeightEntry(Level.NETHER, VoidHeightEntry.Type.RELATIVE_TO_BOTTOM, -6),
                new VoidHeightEntry(Level.END, VoidHeightEntry.Type.RELATIVE_TO_BOTTOM, 50)
        );

        public static class VoidHeightEntry {
            @ConfigEntry.Gui.RequiresRestart
            public String dimension = "minecraft:overworld";

            @ConfigEntry.Gui.RequiresRestart
            @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
            public Type type = Type.RELATIVE_TO_BOTTOM;

            @ConfigEntry.Gui.RequiresRestart
            public Integer height = -6;

            public VoidHeightEntry() {}

            public VoidHeightEntry(ResourceKey<Level> dimension, Type type, int height) {
                this.dimension = dimension.location().toString();
                this.type = type;
                this.height = height;
            }

            public enum Type implements SelectionListEntry.Translatable {
                RELATIVE_TO_BOTTOM, RELATIVE_TO_TOP, ABSOLUTE;

                @Override
                public @NotNull String getKey() {
                    return "text.autoconfig.tide.option.VoidHeightEntry.Type." + name().toLowerCase();
                }
            }
        }

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.RequiresRestart
        public List<String> autoFishDataBlacklist = List.of();

        @ConfigEntry.Gui.Tooltip
        public double crateWeight = 4.0;

        @ConfigEntry.Gui.Tooltip
        public double crateQuality = 1.0;

        @ConfigEntry.Gui.Tooltip
        public boolean logDataErrors = false;

        @Override
        public String toString() {
            return "General{" +
                    "overrideVanillaRod=" + overrideVanillaRod +
                    ", holdToCast=" + holdToCast +
                    ", rodDurabilityMultiplier=" + rodDurabilityMultiplier +
                    ", fishableVoidHeights=" + fishableVoidHeights +
                    ", autoFishDataBlacklist=" + autoFishDataBlacklist +
                    ", crateWeight=" + crateWeight +
                    ", crateQuality=" + crateQuality +
                    ", logDataErrors=" + logDataErrors +
                    '}';
        }
    }

    public static class Items {
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.RequiresRestart
        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public SizeMode fishItemSizes = SizeMode.ALWAYS;

        public enum SizeMode implements SelectionListEntry.Translatable {
            ALWAYS, IN_JOURNAL, NEVER;

            @Override
            public @NotNull String getKey() {
                return "text.autoconfig.tide.option.server.items.fishItemSizes." + name().toLowerCase();
            }
        }

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public BucketableMode bucketableFishItems = BucketableMode.WHEN_LIVING;

        public enum BucketableMode implements SelectionListEntry.Translatable {
            ALWAYS, WHEN_LIVING, NEVER;

            @Override
            public @NotNull String getKey() {
                return "text.autoconfig.tide.option.server.items.bucketableFishItems." + name().toLowerCase();
            }
        }

        @ConfigEntry.Gui.Tooltip
        public boolean enableBedrockBreakingItems = true;

        @ConfigEntry.Gui.Tooltip
        public List<String> pocketWatchBlacklist = List.of();

        @Override
        public String toString() {
            return "Items{" +
                    "fishItemSizes=" + fishItemSizes +
                    ", bucketableFishItems=" + bucketableFishItems +
                    ", enableBedrockBreakingItems=" + enableBedrockBreakingItems +
                    ", pocketWatchBlacklist=" + pocketWatchBlacklist +
                    '}';
        }
    }

    public static class Journal {
        @ConfigEntry.Gui.Tooltip
        public boolean giveJournal = true;

        @ConfigEntry.Gui.Tooltip
        public boolean showToasts = true;

        @Override
        public String toString() {
            return "Journal{" +
                    "giveJournal=" + giveJournal +
                    ", showToasts=" + showToasts +
                    '}';
        }
    }

    public static class Minigame {
        @ConfigEntry.Gui.Tooltip
        public boolean doMinigame = true;

        @ConfigEntry.Gui.Tooltip
        public float minigameDifficultyMultiplier = 1.0f;

        @ConfigEntry.Gui.Tooltip
        public boolean useThirdPartyMinigames = true;

        @ConfigEntry.Gui.Tooltip
        public boolean doSuccessSound = true;

        @ConfigEntry.Gui.Tooltip
        public boolean doFailSound = true;

        @Override
        public String toString() {
            return "Minigame{" +
                    "doMinigame=" + doMinigame +
                    ", minigameDifficulty=" + minigameDifficultyMultiplier +
                    ", useThirdPartyMinigames=" + useThirdPartyMinigames +
                    ", doSuccessSound=" + doSuccessSound +
                    ", doFailSound=" + doFailSound +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TideConfig{" +
                "general=" + general +
                ", items=" + items +
                ", journal=" + journal +
                ", minigame=" + minigame +
                '}';
    }
}
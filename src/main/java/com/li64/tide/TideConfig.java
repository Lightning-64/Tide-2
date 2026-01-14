package com.li64.tide;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Config(name = Tide.MOD_ID)
public final class TideConfig implements ConfigData {
    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.TransitiveObject
    public General general;

    @ConfigEntry.Category("items")
    @ConfigEntry.Gui.TransitiveObject
    public Items items;

    @ConfigEntry.Category("journal")
    @ConfigEntry.Gui.TransitiveObject
    public Journal journal;

    @ConfigEntry.Category("minigame")
    @ConfigEntry.Gui.TransitiveObject
    public Minigame minigame;

    public TideConfig() {
        this.general = new General();
        this.items = new Items();
        this.journal = new Journal();
        this.minigame = new Minigame();
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
        public boolean defaultLineColor = false;

        @ConfigEntry.Gui.Tooltip
        public boolean ambientVoidParticles = true;

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
    }

    public static class Items {
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.RequiresRestart
        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public SizeMode fishItemSizes = SizeMode.ALWAYS;

        public enum SizeMode implements SelectionListEntry.Translatable {
            ALWAYS, IN_JOURNAL, NEVER;

            @Override
            public @NotNull String getKey() {
                return "text.autoconfig.tide.option.items.fishItemSizes." + name().toLowerCase();
            }
        }

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public BucketableMode bucketableFishItems = BucketableMode.WHEN_LIVING;

        public enum BucketableMode implements SelectionListEntry.Translatable {
            ALWAYS, WHEN_LIVING, NEVER;

            @Override
            public @NotNull String getKey() {
                return "text.autoconfig.tide.option.items.bucketableFishItems." + name().toLowerCase();
            }
        }

        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.Tooltip
        public long fishItemLifespan = 180;

        @ConfigEntry.Gui.Tooltip
        public boolean enableBedrockBreakingItems = true;

        @ConfigEntry.Gui.Tooltip
        public List<String> pocketWatchBlacklist = List.of();
    }

    public static class Journal {
        @ConfigEntry.Gui.Tooltip
        public boolean giveJournal = true;

        @ConfigEntry.Gui.Tooltip
        public boolean showToasts = true;

        @ConfigEntry.Gui.Tooltip
        public boolean showUnread = true;

        @ConfigEntry.Gui.Tooltip
        public boolean useAmPm = true;

        @ConfigEntry.Gui.Tooltip
        public boolean useFahrenheit = false;

        @ConfigEntry.Gui.Tooltip
        public boolean useRealDate = true;
    }

    public static class Minigame {
        @ConfigEntry.Gui.Tooltip
        public boolean doMinigame = true;

        @ConfigEntry.Gui.Tooltip
        public boolean doFeedback = true;

        @ConfigEntry.Gui.Tooltip
        public boolean doSuccessSound = true;

        @ConfigEntry.Gui.Tooltip
        public boolean doFailSound = true;

        @ConfigEntry.Gui.Tooltip
        public float minigameDifficulty = 1.0f;
    }
}
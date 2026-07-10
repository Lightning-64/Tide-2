package com.li64.tide.config;

import com.google.gson.Gson;
import com.li64.tide.Tide;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import net.minecraft.network.FriendlyByteBuf;
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

    public static void writeToPacket(FriendlyByteBuf buf, Gson gson) {
        String json = gson.toJson(Tide.CONFIG);
        buf.writeUtf(json);
        // Tide.LOG.info("Config wrote to packet: {}", json);
    }

    public static void readFromPacket(FriendlyByteBuf buf, Gson gson) {
        String json = buf.readUtf();
        Tide.CONFIG = gson.fromJson(json, TideConfig.class);
        // Tide.LOG.info("Config synced to client: {}", json);
    }

    public static class General {
        @Comment("When enabled, the vanilla fishing rod will use Tide's fishing loot system")
        @ConfigEntry.Gui.RequiresRestart
        public boolean overrideVanillaRod = true;

        @Comment("When enabled, you can hold right-click to cast the bobber farther")
        public boolean holdToCast = true;

        @Comment("A multiplier applied to the durability of fishing rods")
        @ConfigEntry.Gui.RequiresRestart
        public double rodDurabilityMultiplier = 1.0;

        @Comment("A list that defines the fishable void heights for each dimension")
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

            public enum Type {
                RELATIVE_TO_BOTTOM, RELATIVE_TO_TOP, ABSOLUTE;
            }
        }

        @Comment("Item IDs added to this list will never have their fish data auto-generated")
        @ConfigEntry.Gui.RequiresRestart
        public List<String> autoFishDataBlacklist = List.of();

        @Comment("The chance of a crate being selected from the fishing loot table (Use \"/fishing test loot\" to see catch percentages.)")
        public double crateWeight = 4.0;

        @Comment("The amount that the crate chance is scaled with higher fishing luck")
        public double crateQuality = 1.0;

        @Comment("For datapackers: enable to show Tide data loading errors in the logs, they are suppressed otherwise")
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
        @Comment("""
                Controls when/where fish should be assigned a length.
                NOTE: If Fish Item Sizes is set to "Always" or Bucketable Fish Items is set to "While Living",
                extra data will be added to fish item stacks that make them unstackable.
                To make fish stackable, simply change both settings to something other than their defaults.
                """)
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

        @Comment("Controls when fish items should be bucketable via menus. See above note for fish stacking!")
        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public BucketableMode bucketableFishItems = BucketableMode.WHEN_LIVING;

        public enum BucketableMode {
            ALWAYS, WHEN_LIVING, NEVER;
        }

        @Comment("Enables the bedrock eating functionality of the Chasm Eel item")
        public boolean enableBedrockBreakingItems = true;

        @Comment("Entity IDs added to this list are unaffected by the Enchanted Pocket Watch")
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
        @Comment("Give players a fishing journal upon joining a world for the first time")
        public boolean giveJournal = true;

        @Comment("Shows a notification (toast) that appears when a new fish is discovered")
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
        @Comment("Enables the fishing minigame")
        public boolean doMinigame = true;

        @Comment("A multiplier that increases or decreases the speed of the minigame")
        public float minigameDifficultyMultiplier = 1.0f;

        @Comment("If enabled, minigames from other mods like starcatcher or stardew fishing will be used")
        public boolean useThirdPartyMinigames = true;

        @Comment("Determines whether fish caught with the starcatcher minigame keep Tide's fish data (length) or Starcatcher's (size, weight, golden)")
        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public FishDataSource fishDataSource = FishDataSource.TIDE;

        public enum FishDataSource {
            TIDE, STARCATCHER;
        }

        @Comment("Enables the sound played when you win the minigame")
        public boolean doSuccessSound = true;

        @Comment("Enables the sound played when you lose the minigame")
        public boolean doFailSound = true;

        @Override
        public String toString() {
            return "Minigame{" +
                    "doMinigame=" + doMinigame +
                    ", minigameDifficulty=" + minigameDifficultyMultiplier +
                    ", useThirdPartyMinigames=" + useThirdPartyMinigames +
                    ", fishDataSource=" + fishDataSource +
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
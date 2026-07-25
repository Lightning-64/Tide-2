package com.li64.tide.config;

import com.li64.tide.Tide;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = Tide.MOD_ID + "_client")
public final class TideClientConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public General general;

    @ConfigEntry.Gui.CollapsibleObject
    public Journal journal;

    @ConfigEntry.Gui.CollapsibleObject
    public Minigame minigame;

    public TideClientConfig() {
        this.general = new General();
        this.journal = new Journal();
        this.minigame = new Minigame();
    }

    public static class General {
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public InfoPlacement infoPlacement = InfoPlacement.TOP_RIGHT;

        public enum InfoPlacement {
            TOP_LEFT, TOP_RIGHT,
            BOTTOM_LEFT, BOTTOM_RIGHT;

            public boolean isTop() {
                return this.ordinal() <= 1;
            }

            public boolean isLeft() {
                return (this.ordinal() % 2) == 0;
            }
        }

        @ConfigEntry.Gui.Tooltip
        public int infoOffsetX = 0;

        @ConfigEntry.Gui.Tooltip
        public int infoOffsetY = 0;

        @ConfigEntry.Gui.Tooltip
        public boolean ambientVoidParticles = true;

        @ConfigEntry.Gui.Tooltip
        public boolean defaultLineColor = false;
    }

    public static class Journal {
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
        public boolean doFeedback = true;
    }
}
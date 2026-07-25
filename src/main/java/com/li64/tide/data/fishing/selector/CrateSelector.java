package com.li64.tide.data.fishing.selector;

import com.li64.tide.Tide;
import com.li64.tide.data.TideData;
import com.li64.tide.data.fishing.CatchResult;
import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.registries.TideItems;
import com.li64.tide.util.BaitUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Map;
import java.util.function.Predicate;

public class CrateSelector implements FishingEntry {
    private static final double MAGNETIC_BAIT_BONUS = 5.0;

    @Override
    public double weight(FishingContext context) {
        boolean hasMagneticBait = context.rod() != null && BaitUtils.hasBait(TideItems.MAGNETIC_BAIT, context.rod());
        double weight = Tide.SERVER_CONFIG.general.crateWeight * (hasMagneticBait ? MAGNETIC_BAIT_BONUS : 1.0);
        return FishingEntry.modifyWeight(weight, Tide.SERVER_CONFIG.general.crateQuality, context);
    }

    @Override
    public boolean shouldKeep(FishingContext context) {
        return getResult(context).isPresent();
    }

    @Override
    public CatchResult getResult(FishingContext context) {
        return this.getResult(context, null, entry -> false);
    }

    @Override
    public CatchResult getResult(FishingContext context, Map<FishingEntry, Double> results, Predicate<FishingEntry> entryTest) {
        return FishingRandomSelector.select(TideData.CRATES.get().values(), context, results, entryTest);
    }

    @Override
    public MutableComponent getTestKey() {
        return Component.translatable("commands.fishing.entries.crate_selector");
    }
}

package com.li64.tide.registries.items;

import com.google.common.collect.ImmutableList;
import com.li64.tide.Tide;
import com.li64.tide.client.gui.overlays.CastBarOverlay;
import com.li64.tide.client.gui.overlays.CatchMinigameOverlay;
import com.li64.tide.compat.CompatHelper;
import com.li64.tide.data.item.TideItemData;
import com.li64.tide.data.minigame.FishCatchMinigame;
import com.li64.tide.data.rods.AccessoryData;
import com.li64.tide.data.rods.BaitContents;
import com.li64.tide.data.rods.CustomRodManager;
import com.li64.tide.data.rods.FishingRodTooltip;
import com.li64.tide.registries.TideEntityTypes;
import com.li64.tide.registries.TideItems;
import com.li64.tide.registries.TideSoundEvents;
import com.li64.tide.registries.entities.misc.fishing.HookAccessor;
import com.li64.tide.registries.entities.misc.fishing.TideFishingHook;
import com.li64.tide.util.BaitUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//? if >=1.21 {
import com.li64.tide.data.item.TideDataComponents;
import net.minecraft.core.component.DataComponents;
//?}

public class TideFishingRodItem extends FishingRodItem {

    public TideFishingRodItem(double baseDurability, Properties properties) {
        super(properties
                .durability((int) (baseDurability * (Tide.CONFIG == null ? 1.0 : Tide.CONFIG.general.rodDurabilityMultiplier)))
                /*? if >=1.21*/.component(TideDataComponents.BAIT_CONTENTS, BaitContents.EMPTY)
        );
    }

    public static List<Component> getDescriptionLines(ItemStack stack) {
        ArrayList<Component> builder = new ArrayList<>();

        ItemStack bobber = CustomRodManager.getBobber(stack);
        ItemStack hook = CustomRodManager.getHook(stack);
        ItemStack line = CustomRodManager.getLine(stack);

        if (CustomRodManager.hasBobber(stack)) {
            MutableComponent bobberComponent = AccessoryData.getTranslation(bobber);
            builder.add(bobberComponent.withStyle(ChatFormatting.BLUE));
        }

        if (CustomRodManager.hasHook(stack)) {
            MutableComponent hookComponent = AccessoryData.getTranslation(hook);
            builder.add(hookComponent.withStyle(ChatFormatting.BLUE));
        }

        if (CustomRodManager.hasLine(stack)) {
            MutableComponent lineComponent = AccessoryData.getTranslation(line);
            builder.add(lineComponent.withStyle(ChatFormatting.BLUE));
        }

        if (!builder.isEmpty()) {
            builder.add(0, Component.translatable("text.tide.rod_tooltip.accessories_prefix").withStyle(ChatFormatting.GRAY));
            builder.add(0, Component.empty());
            builder.add(Component.empty());
        }

        if (stack.is(TideItems.CRYSTAL_FISHING_ROD)) builder.add(Component.translatable("text.tide.rod_tooltip.crystal_bonus").withStyle(ChatFormatting.GOLD));
        if (stack.is(TideItems.GOLDEN_FISHING_ROD) || stack.is(TideItems.MIDAS_FISHING_ROD)) builder.add(Component.translatable("text.tide.rod_tooltip.gold_bonus").withStyle(ChatFormatting.GOLD));
        if (stack.is(TideItems.DIAMOND_FISHING_ROD)) builder.add(Component.translatable("text.tide.rod_tooltip.diamond_bonus").withStyle(ChatFormatting.GOLD));
        if (stack.is(TideItems.NETHERITE_FISHING_ROD)) builder.add(Component.translatable("text.tide.rod_tooltip.netherite_bonus").withStyle(ChatFormatting.GOLD));
        if (stack.is(TideItems.MIDAS_FISHING_ROD)) builder.add(Component.translatable("text.tide.rod_tooltip.midas_bonus").withStyle(ChatFormatting.GOLD));

        return ImmutableList.copyOf(builder);
    }

    //? if >=1.21 {
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return !stack.has(DataComponents.HIDE_TOOLTIP) && !stack.has(DataComponents.HIDE_ADDITIONAL_TOOLTIP)
                ? Optional.ofNullable(TideItemData.BAIT_CONTENTS.get(stack)).map(FishingRodTooltip::new)
                : Optional.empty();
    }
    //?} else {
    /*@Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
        return Optional.of(new FishingRodTooltip(TideItemData.BAIT_CONTENTS.getOrDefault(stack, new BaitContents())));
    }
    *///?}

    @Override
    public boolean overrideStackedOnOther(@NotNull ItemStack stack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player) {
        if (action != ClickAction.SECONDARY) return false;
        BaitContents contents = TideItemData.BAIT_CONTENTS.getOrDefault(stack, new BaitContents());
        BaitContents.Mutable mutable = new BaitContents.Mutable(contents);
        ItemStack slotStack = slot.getItem();

        if (slotStack.isEmpty() && !mutable.isEmpty()) {
            // place next stack
            ItemStack removedStack = mutable.removeStack();
            if (removedStack != null && !removedStack.isEmpty()) slot.safeInsert(removedStack);
            else return false;
        } else if (!slotStack.isEmpty()
                && slotStack.getItem().canFitInsideContainerItems()
                && BaitUtils.isBait(slotStack)) {
            // insert stack
            mutable.tryTransfer(slot, player);
        } else return false;

        TideItemData.BAIT_CONTENTS.set(stack, mutable.toImmutable());
        return true;
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack stack, @NotNull ItemStack other, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) return false;
        BaitContents contents = TideItemData.BAIT_CONTENTS.getOrDefault(stack, new BaitContents());
        BaitContents.Mutable mutable = new BaitContents.Mutable(contents);

        if (other.isEmpty()) {
            // try pull next stack
            ItemStack removedStack = mutable.removeStack();
            if (removedStack != null && !removedStack.isEmpty()) access.set(removedStack);
            else return false;
        } else if (other.getItem().canFitInsideContainerItems() && BaitUtils.isBait(other)) {
            // insert stack
            mutable.tryInsert(other);
        } else return false;

        TideItemData.BAIT_CONTENTS.set(stack, mutable.toImmutable());
        return true;
    }

    public boolean isLavaproof(ItemStack stack) {
        return CustomRodManager.getHook(stack).is(TideItems.LAVAPROOF_FISHING_HOOK)
                || (this == TideItems.NETHERITE_FISHING_ROD);
    }

    public boolean isVoidproof(ItemStack stack) {
        return CustomRodManager.getHook(stack).is(TideItems.VOID_FISHING_HOOK);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (isHookActive(player)) {
            TideFishingHook hook = getHook(player);

            if (isMinigameStopped(player, level.isClientSide()) && Tide.CONFIG.minigame.doMinigame) {
                // No minigame active, create a new one if necessary
                if (Tide.PLATFORM.isModLoaded("stardew_fishing")) {
                    if (hook.getCatchType() == TideFishingHook.CatchType.FISH
                        || hook.getCatchType() == TideFishingHook.CatchType.ITEM) {

                        if (!level.isClientSide()) {
                            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE,
                                    SoundSource.NEUTRAL, 1.2F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

                            if (!CompatHelper.stardewFishingStartMinigame((ServerPlayer) player, (HookAccessor) player.fishing,
                                    player.getItemInHand(hand), hook.getHookedItems())) {
                                retrieveHook(player.getItemInHand(hand), player, level);
                            }
                        }
                    } else retrieveHook(player.getItemInHand(hand), player, level);
                }
                else if (hook.getCatchType() == TideFishingHook.CatchType.FISH) {
                    if (!level.isClientSide() && isMinigameStopped(player, level.isClientSide())) {
                        // start fishing minigame
                        FishCatchMinigame.create(player);
                        level.playSound(null, player.getX(), player.getY(), player.getZ(), TideSoundEvents.FISHING_REEL,
                                SoundSource.NEUTRAL, 0.7f, 0.85f + (level.getRandom().nextFloat() * 0.25f));
                    }
                }
                else {
                    if (!level.isClientSide() && FishCatchMinigame.delayActive((ServerPlayer) player))
                        return InteractionResultHolder.consume(player.getItemInHand(hand));
                    retrieveHook(player.getItemInHand(hand), player, level);
                }
            }
            else {
                // Minigame is either active or disabled
                if (!Tide.CONFIG.minigame.doMinigame) {
                    if (!level.isClientSide()) hook.retrieve();
                } else if (level.isClientSide()) {
                    // Interact with minigame (from the client)
                    CatchMinigameOverlay.interact();
                }
            }
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        else {
            if (!level.isClientSide() && FishCatchMinigame.delayActive((ServerPlayer) player))
                return InteractionResultHolder.consume(player.getItemInHand(hand));

            if (Tide.CONFIG.general.holdToCast) {
                // Charge the cast if the hook isn't active
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE,
                        SoundSource.NEUTRAL, 1.5F, 0.3F / (level.getRandom().nextFloat() * 0.4F + 0.7F));
                player.startUsingItem(hand);
                return InteractionResultHolder.consume(player.getItemInHand(hand));
            }
            else {
                // Cast the hook normally
                castHook(player.getItemInHand(hand), player, level, 1f);
                return InteractionResultHolder.success(player.getItemInHand(hand));
            }
        }
    }

    private boolean isMinigameStopped(Player player, boolean clientSide) {
        return clientSide ? !CatchMinigameOverlay.isActive() : !FishCatchMinigame.minigameActive(player);
    }

    @Override
    public void releaseUsing(@NotNull ItemStack rod, @NotNull Level level, @NotNull LivingEntity user, int charge) {
        if (user instanceof Player player) {
            int chargeDifference = this.getUseDuration(rod, user) - charge;
            int chargeDuration = getChargeDuration(rod, user);

            // Actually cast the hook
            if (chargeDifference > chargeDuration) chargeDifference = chargeDuration;
            float chargeMultiplier = ((float) chargeDifference / (float) chargeDuration) + 0.5f;
            castHook(rod, player, level, chargeMultiplier);
        }
    }

    public boolean isHookActive(Player player) {
        TideFishingHook hook = HookAccessor.getHook(player);
        return hook != null;
    }

    public TideFishingHook getHook(Player player) {
        return HookAccessor.getHook(player);
    }

    public void castHook(ItemStack rod, Player player, Level level, float charge) {
        TideFishingHook activeHook = HookAccessor.getHook(player);
        if (activeHook == null) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW,
                    SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {
                //? if >=1.21 {
                int speed = (int)(EnchantmentHelper.getFishingTimeReduction((ServerLevel) level, rod, player) / 5f);
                int luck = EnchantmentHelper.getFishingLuckBonus((ServerLevel) level, rod, player);
                //?} else {
                /*int speed = EnchantmentHelper.getFishingSpeedBonus(rod);
                int luck = EnchantmentHelper.getFishingLuckBonus(rod);
                *///?}

                if (BaitUtils.isHoldingBait(rod)) {
                    speed += BaitUtils.getBaitSpeed(BaitUtils.getPrimaryBait(rod));
                    luck += BaitUtils.getBaitLuck(BaitUtils.getPrimaryBait(rod));
                }

                if (rod.is(TideItems.GOLDEN_FISHING_ROD) || rod.is(TideItems.MIDAS_FISHING_ROD)) luck += 1;
                if (CompatHelper.isHybridAquaticLoaded()) {
                    if (CustomRodManager.getHook(rod).getItem().toString().matches("barbed_hook") && level.isDay()) speed += 1;
                    if (CustomRodManager.getHook(rod).getItem().toString().matches("glowing_hook") && level.isNight()) speed += 1;
                    if (CustomRodManager.getHook(rod).getItem().toString().matches("magnetic_hook")) luck += 1;
                }

                level.addFreshEntity(new TideFishingHook(TideEntityTypes.FISHING_BOBBER,
                        player, level, luck, speed, charge, rod));
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }
    }

    public void retrieveHook(ItemStack rod, Player player, Level level) {
        TideFishingHook activeHook = HookAccessor.getHook(player);
        if (activeHook != null) {
            if (!level.isClientSide) {
                int durabilityLoss = activeHook.retrieve(rod, (ServerLevel) level, player);
                /*? if >=1.21 {*/rod.hurtAndBreak(durabilityLoss, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
                /*?} else*//*rod.hurtAndBreak(durabilityLoss, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));*/
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE,
                    SoundSource.NEUTRAL, 1.2F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        }
    }

    @Override
    public void onUseTick(@NotNull Level level, @NotNull LivingEntity user, @NotNull ItemStack rod, int charge) {
        super.onUseTick(level, user, rod, charge);

        int chargeDuration = getChargeDuration(rod, user);

        if (level.isClientSide() && user == Minecraft.getInstance().player) {
            int chargeDifference = this.getUseDuration(rod, user) - charge;
            if (chargeDifference > chargeDuration) chargeDifference = chargeDuration;

            CastBarOverlay.rodChargeTick((float) chargeDifference / (float) chargeDuration);
        }
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack/*? if >=1.21 {*/, @NotNull LivingEntity entity/*?}*/) {
        return 60000;
    }

    //? if <1.21 {
    /*@SuppressWarnings("unused")
    public int getUseDuration(@NotNull ItemStack stack, LivingEntity entity) {
        return this.getUseDuration(stack);
    }
    *///?}

    public int getChargeDuration(ItemStack rod, LivingEntity entity) {
        return CustomRodManager.getLine(rod).is(TideItems.BRAIDED_LINE) ? 15 : 25;
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BOW;
    }

    public void onItemBroken(ItemStack stack, ServerPlayer player) {
        List<ItemStack> accessories = CustomRodManager.getAccessoryList(stack);
        accessories.forEach(accessory -> giveOrDrop(accessory, player));
    }

    private void giveOrDrop(ItemStack stack, Player player) {
        if (stack == null || stack.isEmpty()) return;
        ItemEntity item = player.drop(stack, false);
        if (item != null) item.setNoPickUpDelay();
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return repairCandidate.is(Items.STRING);
    }
}
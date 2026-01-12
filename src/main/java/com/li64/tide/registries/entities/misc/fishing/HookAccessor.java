package com.li64.tide.registries.entities.misc.fishing;

import com.li64.tide.Tide;
import com.li64.tide.registries.TideEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HookAccessor extends FishingHook {
    private TideFishingHook hook = null;

    public HookAccessor(TideFishingHook hook, Level level) {
        super(TideEntityTypes.HOOK_ACCESSOR, level);
        this.hook = hook;
    }

    public HookAccessor(EntityType<? extends FishingHook> entityType, Level level) {
        super(entityType, level);
    }

    private boolean checkIsUnlinked() {
        if (hook != null) return false;
        Tide.LOG.error("Hook accessor unexpectedly unlinked from Tide fishing hook!");
        return true;
    }

    public static boolean bobberRemoved(Player player) {
        return player == null || player.fishing == null || getHook(player) == null;
    }

    public void clearHook(Player player) {
        this.hook = null;
        player.fishing = null;
        super.remove(RemovalReason.DISCARDED);
    }

    public boolean isFishing() {
        return hook != null;
    }

    public static TideFishingHook getHook(Player player) {
        if (player.fishing instanceof HookAccessor accessor) return accessor.hook;
        return null;
    }

    @Override
    public boolean isOpenWaterFishing() {
        if (this.checkIsUnlinked()) return false;
        return hook.isOpenWaterFishing();
    }

    @Override
    public double getX() {
        if (this.checkIsUnlinked()) return 0.0;
        return hook.getX();
    }

    @Override
    public double getY() {
        if (this.checkIsUnlinked()) return 0.0;
        return hook.getY();
    }

    @Override
    public double getZ() {
        if (this.checkIsUnlinked()) return 0.0;
        return hook.getZ();
    }

    @Override
    public void remove(@NotNull RemovalReason reason) {
        if (hook != null) hook.remove(reason);
        super.remove(reason);
    }

    @Override
    public @NotNull Vec3 position() {
        if (this.checkIsUnlinked()) return new Vec3(0, 0, 0);
        return hook.position();
    }

    @Override
    public @NotNull BlockPos blockPosition() {
        if (this.checkIsUnlinked()) return new BlockPos(0, 0, 0);
        return hook.blockPosition();
    }

    @Nullable
    @Override
    public Player getPlayerOwner() {
        if (hook == null) return null;
        return hook.getPlayerOwner();
    }
}

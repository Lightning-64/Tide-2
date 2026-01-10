//? if neoforge {
/*package com.li64.tide.loaders.neoforge;

import com.li64.tide.loaders.LoaderPlatform;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class NeoforgePlatform implements LoaderPlatform {
    @Override
    public String getPlatformName() {
        return "Neoforge";
    }

    @Override
    public String getMCVersion() {
        /^? if 1.20.1^/ return "1.20.1";
        return "1.21.1";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public CompoundTag getPlayerData(ServerPlayer player) {
        return player.getPersistentData();
    }

    @Override
    public <T> Registry<T> createRegistry(ResourceKey<Registry<T>> registryKey) {
        return new RegistryBuilder<>(registryKey).sync(true).create();
    }

    @Override
    public <T> T register(Registry<? super T> registry, ResourceLocation id, T toRegister) {
        return NeoforgeEntrypoint.registerSomething(registry, id.getPath(), toRegister);
    }

    @Override
    public <T> T register(Registry<? super T> registry, ResourceKey<T> id, T toRegister) {
        return NeoforgeEntrypoint.registerSomething(registry, id.location().getPath(), toRegister);
    }

    @Override
    public <T> Holder<T> registerForHolder(Registry<T> registry, ResourceLocation id, T toRegister) {
        return NeoforgeEntrypoint.registerSomethingForHolder(registry, id.getPath(), toRegister);
    }

    @Override
    public <T extends AbstractContainerMenu> MenuType<T> createMenuType(MenuType.MenuSupplier<T> menuSupplier, FeatureFlagSet flags) {
        return new MenuType<>(menuSupplier, flags);
    }
}
*///?}
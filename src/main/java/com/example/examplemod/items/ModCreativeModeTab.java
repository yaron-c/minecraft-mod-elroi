package com.example.examplemod.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import com.example.examplemod.ExampleMod;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExampleMod.MODID);

    public static final RegistryObject<CreativeModeTab> MOD_TAB = CREATIVE_MODE_TABS.register("mod_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.PIZZA.get()))
                    .title(Component.translatable("creativetab.mod_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.PIZZA.get());
                    })
                    .build());
}

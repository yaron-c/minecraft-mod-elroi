package com.example.examplemod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import com.example.examplemod.items.ModItems;
import com.example.examplemod.items.ModCreativeModeTab;

@Mod(ExampleMod.MODID)
public class ExampleMod {
    public static final String MODID = "mod_legendery";

    public ExampleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(modEventBus);
        ModCreativeModeTab.CREATIVE_MODE_TABS.register(modEventBus);
    }
}

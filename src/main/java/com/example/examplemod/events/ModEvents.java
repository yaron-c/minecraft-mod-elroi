package com.example.examplemod.events;

import com.example.examplemod.ExampleMod;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import com.example.examplemod.items.ModItems;
import net.minecraft.nbt.CompoundTag;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID)
public class ModEvents {
    
    @SubscribeEvent
    public static void onChat(ServerChatEvent event) {
        String message = event.getMessage().getString();
        Player player = event.getPlayer();
        ItemStack heldItem = player.getMainHandItem();
        
        if (heldItem.getItem() == ModItems.MAGIC_WAND.get()) {
            if (message.equalsIgnoreCase("lumos")) {
                CompoundTag tag = heldItem.getOrCreateTag();
                tag.putBoolean("isLit", true);
                player.sendSystemMessage(Component.literal("✨ Lumos! ✨"));
                event.setCanceled(true);
            } 
            else if (message.equalsIgnoreCase("nox")) {
                CompoundTag tag = heldItem.getOrCreateTag();
                tag.putBoolean("isLit", false);
                player.sendSystemMessage(Component.literal("🌑 Nox! 🌑"));
                event.setCanceled(true);
            }
        }
    }
} 
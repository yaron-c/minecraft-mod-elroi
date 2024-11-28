package com.example.examplemod.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.example.examplemod.ExampleMod;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID)
public class MagicWandItem extends Item {
    private static BlockPos lastLightPos = null;

    public MagicWandItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getBoolean("isLit");
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level level = player.level();
            
            if (!level.isClientSide) {
                ItemStack heldItem = player.getMainHandItem();
                
                if (heldItem.getItem() instanceof MagicWandItem) {
                    CompoundTag tag = heldItem.getOrCreateTag();
                    
                    if (tag.getBoolean("isLit")) {
                        // מסיר את בלוק האור הקודם
                        if (lastLightPos != null) {
                            level.setBlock(lastLightPos, Blocks.AIR.defaultBlockState(), 3);
                        }
                        
                        // מציב בלוק אור חדש במיקום הנוכחי
                        BlockPos newPos = player.blockPosition().above();
                        if (level.getBlockState(newPos).isAir()) {
                            level.setBlock(newPos, Blocks.LIGHT.defaultBlockState(), 3);
                            lastLightPos = newPos;
                        }
                    } else if (lastLightPos != null) {
                        // מסיר את האור כשהשרביט כבוי
                        level.setBlock(lastLightPos, Blocks.AIR.defaultBlockState(), 3);
                        lastLightPos = null;
                    }
                }
            }
        }
    }
}

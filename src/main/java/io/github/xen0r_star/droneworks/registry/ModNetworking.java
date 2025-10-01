package io.github.xen0r_star.droneworks.registry;

import io.github.xen0r_star.droneworks.network.BoxCraftPacket;
import io.github.xen0r_star.droneworks.screen.BoxScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;


public class ModNetworking {
    public static void init() {
        PayloadTypeRegistry.playC2S().register(
                BoxCraftPacket.PACKET_ID,
                BoxCraftPacket.CODEC
        );

        ServerPlayNetworking.registerGlobalReceiver(BoxCraftPacket.PACKET_ID, (packet, context) -> {
            MinecraftServer server = context.player().getServer();
            if (server == null) return;

            server.execute(() -> {
                ServerPlayerEntity player = context.player();

                if (player.currentScreenHandler instanceof BoxScreenHandler handler) {
                    ItemStack stackSprayPaint = handler.getSlot(6).getStack();
                    Item drone;

                    if (!stackSprayPaint.isEmpty()) {
                        Item sprayPaint = stackSprayPaint.getItem();
                        if (sprayPaint == ModItems.SPRAY_PAINT_WHITE_ITEM) {
                            drone = ModItems.DRONE_WHITE_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_LIGHT_GRAY_ITEM) {
                            drone = ModItems.DRONE_LIGHT_GRAY_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_GRAY_ITEM) {
                            drone = ModItems.DRONE_GRAY_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_BLACK_ITEM) {
                            drone = ModItems.DRONE_BLACK_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_BROWN_ITEM) {
                            drone = ModItems.DRONE_BROWN_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_RED_ITEM) {
                            drone = ModItems.DRONE_RED_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_ORANGE_ITEM) {
                            drone = ModItems.DRONE_ORANGE_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_YELLOW_ITEM) {
                            drone = ModItems.DRONE_YELLOW_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_LIME_ITEM) {
                            drone = ModItems.DRONE_LIME_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_GREEN_ITEM) {
                            drone = ModItems.DRONE_GREEN_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_CYAN_ITEM) {
                            drone = ModItems.DRONE_CYAN_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_LIGHT_BLUE_ITEM) {
                            drone = ModItems.DRONE_LIGHT_BLUE_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_BLUE_ITEM) {
                            drone = ModItems.DRONE_BLUE_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_PURPLE_ITEM) {
                            drone = ModItems.DRONE_PURPLE_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_MAGENTA_ITEM) {
                            drone = ModItems.DRONE_MAGENTA_ITEM;
                        } else if (sprayPaint == ModItems.SPRAY_PAINT_PINK_ITEM) {
                            drone = ModItems.DRONE_PINK_ITEM;
                        } else {
                            drone = ModItems.DRONE_DEFAULT_ITEM;
                        }
                    } else {
                        drone = ModItems.DRONE_DEFAULT_ITEM;
                    }

                    for (int i = 0; i < 7; i++) {
                        Slot slot = handler.getSlot(i);
                        ItemStack stack = slot.getStack();
                        if (!stack.isEmpty()) {
                            slot.setStack(ItemStack.EMPTY);
                        }
                    }

                    handler.sendContentUpdates();


                    assert drone != null;
                    player.getInventory().offerOrDrop(new ItemStack(drone));
                }
            });
        });

        System.out.println("[Droneworks] ModNetworking initialisés");
    }
}
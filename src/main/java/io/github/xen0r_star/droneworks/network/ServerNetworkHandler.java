package io.github.xen0r_star.droneworks.network;

import io.github.xen0r_star.droneworks.block.StationBlockEntity;
import io.github.xen0r_star.droneworks.screen.StationScreenHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ServerNetworkHandler {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(
            PlayButtonPayload.ID,
            (payload, context) -> {
                var player = context.player();
                var server = player.getServer();
                boolean isPlaying = payload.play();

                server.execute(() -> {
                    if (player.currentScreenHandler instanceof StationScreenHandler handler) {
                        BlockPos pos = handler.getBlockPos();

                        BlockEntity be = player.getWorld().getBlockEntity(pos);
                        if (be instanceof StationBlockEntity station) {

                            if (!isPlaying) {
                                station.orderDroneReturnToBase();
                            } else {
                                station.orderDroneResume();
                            }
                        }
                    }
                });
            }
        );
    }
}

package io.github.xen0r_star.droneworks.registry;

import io.github.xen0r_star.droneworks.network.PlayButtonPayload;
import io.github.xen0r_star.droneworks.network.ServerNetworkHandler;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;


public class ModNetworking {
    public static void init() {
        PayloadTypeRegistry.playC2S().register(
            PlayButtonPayload.ID,
            PlayButtonPayload.CODEC
        );

        ServerNetworkHandler.register();

        System.out.println("[Droneworks] ModNetworking initialisés");
    }
}

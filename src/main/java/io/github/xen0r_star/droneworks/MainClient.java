package io.github.xen0r_star.droneworks;

import io.github.xen0r_star.droneworks.client.renderer.DroneRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(
                Main.DRONE,
                context -> new DroneRenderer(context)
        );
    }
}

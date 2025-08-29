package io.github.xen0r_star.droneworks;

import io.github.xen0r_star.droneworks.entity.client.DroneModel;
import io.github.xen0r_star.droneworks.entity.client.DroneRenderer;
import io.github.xen0r_star.droneworks.entity.client.ModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class MainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModelLayers.DRONE, DroneModel::getTexturedModelData);
        EntityRendererRegistry.register(Main.DRONE_ENTITY, DroneRenderer::new);
    }
}

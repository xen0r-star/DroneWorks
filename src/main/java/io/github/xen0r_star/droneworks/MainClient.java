package io.github.xen0r_star.droneworks;

import io.github.xen0r_star.droneworks.client.model.DroneModel;
import io.github.xen0r_star.droneworks.client.renderer.DroneRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // 1. Enregistrement du modèle
        EntityModelLayerRegistry.registerModelLayer(DroneModel.MODEL_LAYER, DroneModel::getTexturedModelData);

        // 2. Enregistrement du renderer
        EntityRendererRegistry.register(Main.DRONE, context -> new DroneRenderer(context));
    }
}

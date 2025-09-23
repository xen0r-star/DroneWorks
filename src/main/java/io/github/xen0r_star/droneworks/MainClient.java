package io.github.xen0r_star.droneworks;

import io.github.xen0r_star.droneworks.client.model.DroneModel;
import io.github.xen0r_star.droneworks.client.renderer.DroneRenderer;
import io.github.xen0r_star.droneworks.client.screen.StationScreen;
import io.github.xen0r_star.droneworks.registry.ModScreenHandlers;
import io.github.xen0r_star.droneworks.client.screen.BoxScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;


public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(DroneModel.MODEL_LAYER, DroneModel::getTexturedModelData);
        EntityRendererRegistry.register(Main.DRONE, DroneRenderer::new);

        HandledScreens.register(ModScreenHandlers.BOX_SCREEN_HANDLER, BoxScreen::new);
        HandledScreens.register(ModScreenHandlers.STATION_SCREEN_HANDLER, StationScreen::new);
    }
}

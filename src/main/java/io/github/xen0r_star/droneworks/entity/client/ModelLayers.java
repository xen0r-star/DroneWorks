package io.github.xen0r_star.droneworks.entity.client;

import io.github.xen0r_star.droneworks.Main;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModelLayers {
    public static final EntityModelLayer DRONE =
            new EntityModelLayer(Identifier.of(Main.MOD_ID, "drone"), "main");
}


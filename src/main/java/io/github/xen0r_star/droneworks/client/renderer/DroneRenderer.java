package io.github.xen0r_star.droneworks.client.renderer;

import io.github.xen0r_star.droneworks.client.model.DroneModel;
import io.github.xen0r_star.droneworks.entity.DroneEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class DroneRenderer extends MobEntityRenderer<DroneEntity, DroneModel> {

    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(
            Identifier.of("droneworks", "drone"), "main"
    );

    public DroneRenderer(EntityRendererFactory.Context context) {
        super(context, new DroneModel(context.getPart(MODEL_LAYER)), 0.4f);
    }

    @Override
    public Identifier getTexture(DroneEntity entity) {
        return Identifier.of("droneworks", "textures/entity/drone.png");
    }
}
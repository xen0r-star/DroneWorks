package io.github.xen0r_star.droneworks.client.renderer;

import io.github.xen0r_star.droneworks.client.model.DroneModel;
import io.github.xen0r_star.droneworks.entity.DroneEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;


public class DroneRenderer extends MobEntityRenderer<DroneEntity, LivingEntityRenderState, DroneModel> {
    public DroneRenderer(EntityRendererFactory.Context context) {
        super(context, new DroneModel(context.getPart(DroneModel.MODEL_LAYER)), 0.3f);
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return Identifier.of("droneworks", "textures/entity/drone.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}

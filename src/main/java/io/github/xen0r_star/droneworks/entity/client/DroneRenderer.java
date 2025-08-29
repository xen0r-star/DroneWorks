package io.github.xen0r_star.droneworks.entity.client;

import io.github.xen0r_star.droneworks.Main;
import io.github.xen0r_star.droneworks.entity.custom.DroneEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.MobEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DroneRenderer extends MobEntityRenderer<DroneEntity, MobEntityRenderState, DroneModel> {
    public DroneRenderer(EntityRendererFactory.Context context) {
        super(context, new DroneModel(context.getPart(ModelLayers.DRONE)), 0.75f);
    }

    @Override
    public Identifier getTexture(MobEntityRenderState state) {
        return Identifier.of(Main.MOD_ID, "textures/entity/drone/drone.png");
    }

    @Override
    protected void scale(MobEntityRenderState state, MatrixStack matrices) {
        if (state.isBaby) {
            matrices.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrices.scale(1f, 1f, 1f);
        }
    }

    @Override
    public void updateRenderState(DroneEntity entity, MobEntityRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
        state.isBaby = entity.isBaby();
    }
}

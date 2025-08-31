package io.github.xen0r_star.droneworks.client.renderer;

import io.github.xen0r_star.droneworks.client.model.DroneModel;
import io.github.xen0r_star.droneworks.client.renderer.state.DroneEntityRenderState;
import io.github.xen0r_star.droneworks.entity.DroneEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.util.Identifier;

public class DroneRenderer extends EntityRenderer<DroneEntity, DroneEntityRenderState> {

    private final DroneModel model;

    public static final Identifier TEXTURE = Identifier.of("droneworks", "textures/entity/drone.png");

    public DroneRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new DroneModel(context.getPart(DroneModel.MODEL_LAYER));
    }

    @Override
    public DroneEntityRenderState createRenderState() {
        return new DroneEntityRenderState(); // vide car pas d'animations
    }

    @Override
    public void render(DroneEntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
    }
}

package io.github.xen0r_star.droneworks.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;


public class DroneModel extends EntityModel<EntityRenderState> {
    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(
            Identifier.of("droneworks", "drone"), "main"
    );


    public DroneModel(ModelPart root) {
        super(root);

        ModelPart drone = root.getChild("Drone");
		drone.getChild("Body");
        ModelPart PropellerElement = drone.getChild("PropellerElement");
		PropellerElement.getChild("Propeller");
        ModelPart PropellerElement2 = drone.getChild("PropellerElement2");
		PropellerElement2.getChild("Propeller2");
		drone.getChild("toolSocket");
		drone.getChild("Eye");
		drone.getChild("ChargeSocket");
		drone.getChild("Antenna");
		root.getChild("octagon");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Drone = modelPartData.addChild("Drone", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		ModelPartData Body = Drone.addChild("Body", ModelPartBuilder.create().uv(18, 14).cuboid(2.0F, -3.5F, -4.5F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(4, 46).cuboid(2.0F, -2.5F, -4.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 46).cuboid(2.0F, -2.5F, 2.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 23).cuboid(2.0F, 3.5F, -4.5F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 14).cuboid(-5.0F, 3.5F, -4.5F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(18, 23).cuboid(-5.0F, -3.5F, -4.5F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(12, 47).cuboid(-5.0F, -2.5F, 2.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 47).cuboid(-5.0F, -2.5F, -4.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-4.5F, -3.0F, -4.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F))
		.uv(28, 6).cuboid(-4.7F, -1.5F, -2.5F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(48, 0).cuboid(-5.0F, -0.5F, -1.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(4, 32).cuboid(1.7F, -1.5F, -2.5F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.5F, 0.5F, 0.0F, 0.0F, 0.0F));

		Body.addChild("cube_r1", ModelPartBuilder.create().uv(48, 4).cuboid(-3.05F, 6.3F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.05F, -6.8F, -1.5F, 0.0F, 3.1416F, 0.0F));

		ModelPartData PropellerElement = Drone.addChild("PropellerElement", ModelPartBuilder.create().uv(46, 36).cuboid(-2.55F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.45F, -4.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		ModelPartData Propeller = PropellerElement.addChild("Propeller", ModelPartBuilder.create().uv(12, 40).cuboid(-1.9964F, -1.0F, 3.1911F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(36, 33).cuboid(3.2035F, -1.0F, -2.0089F, 1.0F, 2.0F, 4.0F, new Dilation(0.0F))
		.uv(40, 12).cuboid(-1.9964F, -1.0F, -4.2089F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(38, 6).cuboid(-4.1965F, -1.0F, -2.0089F, 1.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-6.7535F, 0.0F, 0.0089F, 0.0F, 0.0F, 0.0F));

		Propeller.addChild("cube_r2", ModelPartBuilder.create().uv(40, 25).cuboid(5.6671F, 6.3F, -12.9298F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(44, 42).cuboid(5.6671F, 6.3F, -5.2931F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(11.1535F, -7.3F, -1.0089F, -3.1416F, 0.7854F, 3.1416F));

		Propeller.addChild("cube_r3", ModelPartBuilder.create().uv(20, 44).cuboid(7.1064F, 6.3F, 10.4905F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(44, 39).cuboid(7.1064F, 6.3F, 2.8537F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(11.1535F, -7.3F, -1.0089F, -3.1416F, -0.7854F, 3.1416F));

		ModelPartData PropellerElement2 = Drone.addChild("PropellerElement2", ModelPartBuilder.create().uv(48, 25).cuboid(-1.7F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.3F, -4.0F, 0.0F, 3.1416F, 0.0F, 3.1416F));

		ModelPartData Propeller2 = PropellerElement2.addChild("Propeller2", ModelPartBuilder.create().uv(40, 15).cuboid(-2.0964F, -1.0F, 3.1911F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(24, 38).cuboid(3.1036F, -1.0F, -2.0089F, 1.0F, 2.0F, 4.0F, new Dilation(0.0F))
		.uv(40, 18).cuboid(-2.0964F, -1.0F, -4.2089F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(34, 39).cuboid(-4.2965F, -1.0F, -2.0089F, 1.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-5.8035F, 0.0F, 0.0089F, 0.0F, 0.0F, 0.0F));

		Propeller2.addChild("cube_r4", ModelPartBuilder.create().uv(44, 45).cuboid(5.6671F, 6.3F, -10.1014F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(28, 45).cuboid(5.6671F, 6.3F, -2.4646F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(9.0535F, -7.3F, 0.9911F, -3.1416F, 0.7854F, 3.1416F));

		Propeller2.addChild("cube_r5", ModelPartBuilder.create().uv(36, 45).cuboid(4.278F, 6.3F, 10.4905F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(46, 33).cuboid(4.278F, 6.3F, 2.8537F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(9.0535F, -7.3F, 0.9911F, -3.1416F, -0.7854F, 3.1416F));

		ModelPartData toolSocket = Drone.addChild("toolSocket", ModelPartBuilder.create(), ModelTransform.of(0.5F, -0.8F, 1.0F, 0.0F, 0.0F, 0.0F));

		toolSocket.addChild("cube_r6", ModelPartBuilder.create().uv(40, 0).cuboid(-11.0F, -0.55F, -2.5F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F))
		.uv(48, 8).cuboid(-11.2F, -0.05F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.45F, -10.5F, -2.0F, 0.0F, 3.1416F, 1.5708F));

		ModelPartData Eye = Drone.addChild("Eye", ModelPartBuilder.create(), ModelTransform.of(0.0F, -4.0F, -3.34F, 0.0F, 3.1416F, 0.0F));

		Eye.addChild("cube_r7", ModelPartBuilder.create().uv(24, 32).cuboid(1.88F, 5.3F, -1.95F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.75F, -8.1F, -2.34F, 0.0F, -1.5708F, 0.0F));

		Eye.addChild("cube_r8", ModelPartBuilder.create().uv(20, 47).cuboid(1.88F, 5.3F, -2.95F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, -7.6F, -2.34F, 0.0F, -1.5708F, 0.0F));

		Eye.addChild("cube_r9", ModelPartBuilder.create().uv(28, 0).cuboid(1.88F, 8.3F, -4.95F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.65F, -6.6F, -2.34F, 0.0F, -1.5708F, 0.0F));

		Eye.addChild("cube_r10", ModelPartBuilder.create().uv(14, 32).cuboid(1.78F, 5.3F, -2.95F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.95F, -7.3F, -2.34F, 0.0F, -1.5708F, 0.0F));

		Eye.addChild("cube_r11", ModelPartBuilder.create().uv(24, 47).cuboid(1.88F, 5.3F, 0.05F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.65F, -8.1F, -2.34F, 0.0F, -1.5708F, 0.0F));

		ModelPartData ChargeSocket = Drone.addChild("ChargeSocket", ModelPartBuilder.create(), ModelTransform.of(0.0F, -4.0F, 3.35F, 0.0F, 3.1416F, 0.0F));

		ChargeSocket.addChild("cube_r12", ModelPartBuilder.create().uv(48, 21).cuboid(-5.0F, 6.3F, -1.95F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(4, 40).cuboid(-4.7F, 5.8F, -2.45F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.95F, -7.3F, 4.35F, 0.0F, -1.5708F, 0.0F));

		Drone.addChild("Antenna", ModelPartBuilder.create().uv(36, 28).cuboid(-2.45F, 1.5F, -2.5F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(40, 21).cuboid(-1.95F, 1.2F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 48).cuboid(-1.45F, -0.2F, -1.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 43).cuboid(-1.95F, -2.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.95F, -9.3F, 1.0F, 0.0F, 0.0F, 0.0F));

		ModelPartData octagon = modelPartData.addChild("octagon", ModelPartBuilder.create().uv(36, 14).cuboid(-0.8284F, -0.1F, -2.0F, 1.6569F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(36, 14).cuboid(-0.8284F, -0.1F, 2.0F, 1.6569F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 32).cuboid(2.0F, -0.1F, -0.8284F, 0.0F, 0.0F, 1.6569F, new Dilation(0.0F))
		.uv(0, 32).cuboid(-2.0F, -0.1F, -0.8284F, 0.0F, 0.0F, 1.6569F, new Dilation(0.0F)), ModelTransform.of(0.0F, 23.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		octagon.addChild("octagon_r1", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, -2.5F, -0.8284F, 0.0F, 0.0F, 1.6569F, new Dilation(0.0F))
		.uv(0, 32).cuboid(2.0F, -2.5F, -0.8284F, 0.0F, 0.0F, 1.6569F, new Dilation(0.0F))
		.uv(36, 14).cuboid(-0.8284F, -2.5F, 2.0F, 1.6569F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(36, 14).cuboid(-0.8284F, -2.5F, -2.0F, 1.6569F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.4F, 0.0F, 0.0F, -0.7854F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
	}

    @Override
    public void setAngles(EntityRenderState state) {
        super.setAngles(state);

        if (state instanceof LivingEntityRenderState livingState) {
            this.root.yaw = livingState.bodyYaw * ((float)Math.PI / 180F);
            this.root.pitch = livingState.pitch * ((float)Math.PI / 180F);
        }
    }


//    public static final AnimationDefinition beam_start = AnimationDefinition.Builder
//        .create(0.25F)
//        .addBoneAnimation("octagon", new Transformation(Transformation.Targets.SCALE,
//                new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR),
//                new Keyframe(0.25F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
//        ))
//        .build();
//
//    public static final AnimationDefinition beam_end = AnimationDefinition.Builder
//        .create(0.25F)
//        .addBoneAnimation("octagon", new Transformation(Transformation.Targets.SCALE,
//                new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
//                new Keyframe(0.25F, AnimationHelper.createScalingVector(1.0F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
//        ))
//        .build();
}
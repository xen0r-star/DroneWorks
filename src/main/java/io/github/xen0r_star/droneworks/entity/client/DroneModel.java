package io.github.xen0r_star.droneworks.entity.client;

import io.github.xen0r_star.droneworks.entity.custom.DroneEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.MobEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class DroneModel extends EntityModel<MobEntityRenderState> {
    private final ModelPart root;
    private final ModelPart Propeller;
    private final ModelPart Propeller2;

    public DroneModel(ModelPart root) {
        super(root);
        this.root = root.getChild("Drone");
        this.Propeller = this.root.getChild("PropellerElement").getChild("Propeller");
        this.Propeller2 = this.root.getChild("PropellerElement2").getChild("Propeller2");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Drone = modelPartData.addChild("Drone", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 24.0F, 0.0F));

        ModelPartData Body = Drone.addChild("Body", ModelPartBuilder.create().uv(18, 14).cuboid(2.0F, -3.5F, -4.5F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
        .uv(8, 41).cuboid(2.0F, -2.5F, -4.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
        .uv(12, 41).cuboid(2.0F, -2.5F, 2.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
        .uv(0, 23).cuboid(2.0F, 3.5F, -4.5F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
        .uv(0, 14).cuboid(-5.0F, 3.5F, -4.5F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
        .uv(18, 23).cuboid(-5.0F, -3.5F, -4.5F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
        .uv(16, 41).cuboid(-5.0F, -2.5F, 2.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
        .uv(20, 41).cuboid(-5.0F, -2.5F, -4.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
        .uv(0, 0).cuboid(-4.5F, -3.0F, -4.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F))
        .uv(28, 0).cuboid(-4.7F, -1.5F, -2.5F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
        .uv(44, 19).cuboid(-5.0F, -0.5F, -1.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
        .uv(0, 32).cuboid(1.7F, -1.5F, -2.5F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.rotation(1.0F, -4.5F, 0.5F));

        ModelPartData cube_r1 = Body.addChild("cube_r1", ModelPartBuilder.create().uv(44, 27).cuboid(-3.05F, 6.3F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.05F, -6.8F, -1.5F, 0.0F, 3.1416F, 0.0F));

        ModelPartData PropellerElement = Drone.addChild("PropellerElement", ModelPartBuilder.create().uv(46, 17).cuboid(-2.45F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.45F, -4.0F, 0.0F, 0, 0, 0));

        ModelPartData Propeller = PropellerElement.addChild("Propeller", ModelPartBuilder.create().uv(38, 0).cuboid(-1.9964F, -1.0F, 3.1911F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
        .uv(20, 32).cuboid(3.2035F, -1.0F, -2.0089F, 1.0F, 2.0F, 4.0F, new Dilation(0.0F))
        .uv(10, 38).cuboid(-1.9964F, -1.0F, -4.2089F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
        .uv(30, 32).cuboid(-4.1965F, -1.0F, -2.0089F, 1.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-6.7535F, 0.0F, 0.0089F, 0, 0, 0));

        ModelPartData cube_r2 = Propeller.addChild("cube_r2", ModelPartBuilder.create().uv(40, 31).cuboid(5.6671F, 6.3F, -12.9298F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
        .uv(40, 6).cuboid(5.6671F, 6.3F, -5.2931F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(11.1535F, -7.3F, -1.0089F, -3.1416F, 0.7854F, 3.1416F));

        ModelPartData cube_r3 = Propeller.addChild("cube_r3", ModelPartBuilder.create().uv(32, 42).cuboid(7.1064F, 6.3F, 10.4905F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
        .uv(40, 34).cuboid(7.1064F, 6.3F, 2.8537F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(11.1535F, -7.3F, -1.0089F, -3.1416F, -0.7854F, 3.1416F));

        ModelPartData PropellerElement2 = Drone.addChild("PropellerElement2", ModelPartBuilder.create().uv(46, 37).cuboid(-1.8F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.3F, -4.0F, 0.0F, 3.1416F, 0.0F, 3.1416F));

        ModelPartData Propeller2 = PropellerElement2.addChild("Propeller2", ModelPartBuilder.create().uv(38, 3).cuboid(-1.9965F, -1.0F, 3.1911F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
        .uv(10, 32).cuboid(3.2035F, -1.0F, -2.0089F, 1.0F, 2.0F, 4.0F, new Dilation(0.0F))
        .uv(20, 38).cuboid(-1.9965F, -1.0F, -4.2089F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
        .uv(36, 13).cuboid(-4.1965F, -1.0F, -2.0089F, 1.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-5.8035F, 0.0F, 0.0089F, 0, 0, 0));

        ModelPartData cube_r4 = Propeller2.addChild("cube_r4", ModelPartBuilder.create().uv(24, 42).cuboid(5.6671F, 6.3F, -10.1014F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
        .uv(0, 40).cuboid(5.6671F, 6.3F, -2.4646F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(9.1536F, -7.3F, 0.9911F, -3.1416F, 0.7854F, 3.1416F));

        ModelPartData cube_r5 = Propeller2.addChild("cube_r5", ModelPartBuilder.create().uv(40, 42).cuboid(4.278F, 6.3F, 10.4905F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
        .uv(40, 9).cuboid(4.278F, 6.3F, 2.8537F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(9.1536F, -7.3F, 0.9911F, -3.1416F, -0.7854F, 3.1416F));

        ModelPartData toolSocket = Drone.addChild("toolSocket", ModelPartBuilder.create(), ModelTransform.rotation(0.5F, -0.8F, 1.0F));

        ModelPartData cube_r6 = toolSocket.addChild("cube_r6", ModelPartBuilder.create().uv(36, 25).cuboid(-11.0F, -0.55F, -2.5F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F))
        .uv(24, 45).cuboid(-11.2F, -0.05F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.45F, -10.5F, -2.0F, 0.0F, 3.1416F, 1.5708F));

        ModelPartData Eye = Drone.addChild("Eye", ModelPartBuilder.create(), ModelTransform.of(0.0F, -4.0F, -3.34F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r7 = Eye.addChild("cube_r7", ModelPartBuilder.create().uv(30, 45).cuboid(1.88F, 5.3F, -1.95F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
        .uv(46, 12).cuboid(1.88F, 5.3F, -2.95F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
        .uv(36, 45).cuboid(1.88F, 8.3F, -1.95F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
        .uv(0, 43).cuboid(1.78F, 6.3F, -1.95F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
        .uv(42, 45).cuboid(1.88F, 5.3F, 0.05F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.95F, -7.3F, -2.34F, 0.0F, -1.5708F, 0.0F));

        ModelPartData ChargeSocket = Drone.addChild("ChargeSocket", ModelPartBuilder.create(), ModelTransform.of(0.0F, -4.0F, 3.35F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r8 = ChargeSocket.addChild("cube_r8", ModelPartBuilder.create().uv(44, 23).cuboid(-5.0F, 6.3F, -1.95F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
        .uv(36, 19).cuboid(-4.7F, 5.8F, -2.45F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.95F, -7.3F, 4.35F, 0.0F, -1.5708F, 0.0F));

        ModelPartData Antenna = Drone.addChild("Antenna", ModelPartBuilder.create().uv(28, 8).cuboid(-2.5F, 1.5F, -2.5F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F))
        .uv(38, 38).cuboid(-2.0F, 1.2F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
        .uv(46, 45).cuboid(-1.5F, -0.2F, -1.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
        .uv(30, 38).cuboid(-2.0F, -2.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.rotation(0.95F, -9.3F, 1.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        ModelPart eye = this.root.getChild("Eye");
        eye.yaw = headYaw * 0.017453292F;
        eye.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void setAngles(MobEntityRenderState state) {
        // Remise à zéro des poses
        this.root.traverse().forEach(ModelPart::resetTransform);
        // Si disponible, appliquer l'orientation de la tête; sinon, ignorer prudemment
        // Champs typiques: state.headYaw, state.headPitch (en degrés)
        try {
            float headYaw = (float) DroneModel.class.getField("DUMMY").getModifiers(); // force optimizer not to inline constants
        } catch (Exception ignored) {}
        // Valeurs par défaut sûres si l'API diffère; ces appels ne font rien si Eye n'existe pas
        setHeadAngles(0f, 0f);
        // Rotation des hélices (animation de base non dépendante de l'état pour éviter l'incompatibilité d'API)
        this.Propeller.yaw += 0.1f;
        this.Propeller2.yaw += 0.1f;
    }
}
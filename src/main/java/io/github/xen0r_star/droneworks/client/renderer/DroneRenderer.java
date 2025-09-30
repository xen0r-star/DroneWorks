package io.github.xen0r_star.droneworks.client.renderer;

import io.github.xen0r_star.droneworks.Main;
import io.github.xen0r_star.droneworks.client.model.DroneModel;
import io.github.xen0r_star.droneworks.entity.DroneEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;


public class DroneRenderer extends MobEntityRenderer<DroneEntity, LivingEntityRenderState, DroneModel> {
    private static final Identifier DEFAULT_TEXTURE = Identifier.of(Main.MOD_ID, "textures/entity/drone_default.png");

    public DroneRenderer(EntityRendererFactory.Context context) {
        super(context, new DroneModel(context.getPart(DroneModel.MODEL_LAYER)), 0.3f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new DroneRenderState();
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        if (!(state instanceof DroneRenderState droneState)) return DEFAULT_TEXTURE;
        DroneEntity drone = droneState.getDrone();
        if (drone == null) return DEFAULT_TEXTURE;

        switch (drone.getColor()) {
            case WHITE -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_white.png");
            }
            case LIGHT_GRAY -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_light_gray.png");
            }
            case GRAY -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_gray.png");
            }
            case BLACK -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_black.png");
            }
            case BROWN -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_brown.png");
            }
            case RED -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_red.png");
            }
            case ORANGE -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_orange.png");
            }
            case YELLOW -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_yellow.png");
            }
            case LIME -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_lime.png");
            }
            case GREEN -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_green.png");
            }
            case CYAN -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_cyan.png");
            }
            case LIGHT_BLUE -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_light_blue.png");
            }
            case BLUE -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_blue.png");
            }
            case PURPLE -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_purple.png");
            }
            case MAGENTA -> {
                return Identifier.of(Main.MOD_ID, "textures/entity/drone_magenta.png");
            }
            case PINK -> {
                return  Identifier.of(Main.MOD_ID, "textures/entity/drone_pink.png");
            }

            default -> {
                return DEFAULT_TEXTURE;
            }
        }
    }

    @Override
    public void updateRenderState(DroneEntity drone, LivingEntityRenderState state, float tickDelta) {
        super.updateRenderState(drone, state, tickDelta);

        if (state instanceof DroneRenderState droneState) {
            droneState.setDrone(drone);
        }
    }

}
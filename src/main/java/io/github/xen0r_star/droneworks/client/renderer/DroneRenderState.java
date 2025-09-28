package io.github.xen0r_star.droneworks.client.renderer;

import io.github.xen0r_star.droneworks.entity.DroneEntity;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;


public class DroneRenderState extends LivingEntityRenderState {
    private DroneEntity drone;

    public void setDrone(DroneEntity drone) {
        this.drone = drone;
    }

    public DroneEntity getDrone() {
        return drone;
    }
}
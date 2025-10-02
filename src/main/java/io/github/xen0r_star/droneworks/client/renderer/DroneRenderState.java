package io.github.xen0r_star.droneworks.client.renderer;

import io.github.xen0r_star.droneworks.entity.DroneEntity;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;


public class DroneRenderState extends LivingEntityRenderState {
    private DroneEntity drone;

    public boolean showBody = true;
    public boolean showAntenna = true;
    public boolean showToolSocket = true;
    public boolean showEye = true;
    public boolean showChargeSocket = true;
    public boolean showPropeller1 = true;
    public boolean showPropeller2 = true;


    public void setDrone(DroneEntity drone) {
        this.drone = drone;
    }

    public DroneEntity getDrone() {
        return drone;
    }
}
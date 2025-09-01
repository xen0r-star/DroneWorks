package io.github.xen0r_star.droneworks.entity;

import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;


class DroneMoveControl extends FlightMoveControl {
    private final DroneEntity drone;

    public DroneMoveControl(DroneEntity drone) {
        super(drone, 10, true);
        this.drone = drone;
    }

    @Override
    public void tick() {
        super.tick();

        Vec3d v = this.drone.getVelocity();
        if (v.lengthSquared() > 1.0E-4) {
            float yaw = (float)(MathHelper.atan2(v.z, v.x) * (180F / Math.PI)) - 90.0F;
            this.drone.setYaw(yaw);
            this.drone.bodyYaw = yaw;

            float pitch = (float)(-(MathHelper.atan2(v.y, v.horizontalLength()) * (180F / Math.PI)));
            this.drone.setPitch(pitch);
        }
    }
}


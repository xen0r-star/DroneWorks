package io.github.xen0r_star.droneworks.entity.goal;

import io.github.xen0r_star.droneworks.entity.DroneEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class DroneReturnToStationGoal extends Goal {
    private final DroneEntity drone;
    private final double speed;
    private BlockPos station;
    private State state = State.IDLE;
    private int stateTicks = 0;

    private enum State {
        IDLE,
        MOVING,
        UNLOADING
    }

    public DroneReturnToStationGoal(DroneEntity drone, double speed) {
        this.drone = drone;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        station = drone.getLinkedStationPos();
        if (station == null) return false;

        if (drone.isForcedToReturn()) return true;

        return drone.areFirstSixSlotsFull();
    }

    @Override
    public void start() {
        if (station != null) {
            state = State.MOVING;
            stateTicks = 0;
        }
    }

    @Override
    public void stop() {
        state = State.IDLE;
        drone.setVelocity(Vec3d.ZERO);
        drone.velocityDirty = true;
        drone.clearForcedReturnFlag();
    }

    @Override
    public void tick() {
        if (station == null) {
            stop();
            return;
        }

        double targetX = station.getX() + 0.5;
        double targetY = station.getY() + 1.0;
        double targetZ = station.getZ() + 0.5;

        double dx = targetX - drone.getX();
        double dy = targetY - drone.getY();
        double dz = targetZ - drone.getZ();
        double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

        switch (state) {
            case MOVING -> {
                if (dist <= 0.5) {
                    state = State.UNLOADING;
                    stateTicks = 0;
                    drone.setVelocity(Vec3d.ZERO);
                    drone.velocityDirty = true;
                    return;
                }

                double vx = dx / dist * speed;
                double vy = dy / dist * speed;
                double vz = dz / dist * speed;

                drone.setVelocity(
                        drone.getVelocity().x + (vx - drone.getVelocity().x) * 0.2,
                        drone.getVelocity().y + (vy - drone.getVelocity().y) * 0.2,
                        drone.getVelocity().z + (vz - drone.getVelocity().z) * 0.2
                );
                drone.velocityDirty = true;
            }

            case UNLOADING -> {
                stateTicks++;
                if (stateTicks == 5) {
                    drone.dumpInventoryToStation();
                }
                if (stateTicks >= 10) {
                    stop();
                }
            }

            case IDLE -> {}
        }
    }

    @Override
    public boolean shouldContinue() {
        return state != State.IDLE;
    }
}

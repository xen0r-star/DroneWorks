package io.github.xen0r_star.droneworks.entity.goal;

import io.github.xen0r_star.droneworks.entity.DroneEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;

public class DroneReturnToStationGoal extends Goal {
    private final DroneEntity drone;
    private final double speed;
    private BlockPos station;

    public DroneReturnToStationGoal(DroneEntity drone, double speed) {
        this.drone = drone;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        station = drone.getLinkedStationPos();
        if (station == null) return false;

        // Ne démarre que si navigation idle (autre goal prioritaire gère récolte)
        return drone.getNavigation().isIdle();
    }

    @Override
    public void start() {
        if (station != null) {
            drone.getNavigation().startMovingTo(
                    station.getX() + 0.5,
                    station.getY() + 2.0, // au-dessus
                    station.getZ() + 0.5,
                    speed
            );
        }
    }

    @Override
    public boolean shouldContinue() {
        if (station == null) return false;
        double ds = drone.squaredDistanceTo(station.getX()+0.5, station.getY()+2.0, station.getZ()+0.5);
        return ds > 2.25 && !drone.getNavigation().isIdle();
    }
}
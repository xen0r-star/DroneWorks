package io.github.xen0r_star.droneworks.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.EnumSet;

public class DroneEntity extends PathAwareEntity {
    public DroneEntity(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
        this.moveControl = new DroneMoveControl(this);
        this.setNoGravity(true);
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20.0)
                .add(EntityAttributes.FLYING_SPEED, 0.2)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.15);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new DroneFlyAroundGoal(this, 0.6));
        this.goalSelector.add(2, new LookAroundGoal(this));
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation nav = new BirdNavigation(this, world);
        nav.setCanOpenDoors(true);
        nav.setCanSwim(false);
        return nav;
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        this.setNoGravity(true);

        if (!this.getVelocity().equals(Vec3d.ZERO)) {
            float yaw = (float)(MathHelper.atan2(this.getVelocity().z, this.getVelocity().x) * (180F / Math.PI)) - 90.0F;
            this.setYaw(yaw);
            this.bodyYaw = yaw;
        }
    }

    static class DroneFlyAroundGoal extends Goal {
        private final PathAwareEntity mob;
        private final double speed;

        DroneFlyAroundGoal(PathAwareEntity mob, double speed) {
            this.mob = mob;
            this.speed = speed;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return this.mob.getNavigation().isIdle();
        }

        @Override
        public void start() {
            Random rnd = this.mob.getRandom();
            double x = this.mob.getX() + (rnd.nextDouble() - 0.5) * 8.0;
            double y = this.mob.getY() + (rnd.nextDouble() * 4.0) - 1.0;
            double z = this.mob.getZ() + (rnd.nextDouble() - 0.5) * 8.0;

            this.mob.getNavigation().startMovingTo(x, y, z, speed);
        }
    }
}
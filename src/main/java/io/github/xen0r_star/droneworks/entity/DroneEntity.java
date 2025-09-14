package io.github.xen0r_star.droneworks.entity;

import io.github.xen0r_star.droneworks.block.StationBlockEntity;
import io.github.xen0r_star.droneworks.registry.ModItems;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class DroneEntity extends PathAwareEntity {
    private int particleCooldown = 0;
    private BlockPos linkedStationPos;


    public DroneEntity(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
        this.moveControl = new DroneMoveControl(this);
        this.setNoGravity(true);
        this.setPersistent();
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return MobEntity.createMobAttributes()
            .add(EntityAttributes.MAX_HEALTH, 20.0)
            .add(EntityAttributes.FLYING_SPEED, 0.2)
            .add(EntityAttributes.MOVEMENT_SPEED, 0.15);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new LookAroundGoal(this));
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return super.createNavigation(world);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient) return;

        particleCooldown--;
        if (particleCooldown > 0) return;
        particleCooldown = 10; // 10 ticks = 0.5 sec

        float healthPercent = this.getHealth() / this.getMaxHealth();

        if (healthPercent <= 0.5f && healthPercent > 0.25f) {
            ((ServerWorld)this.getWorld()).spawnParticles(
                    ParticleTypes.SMOKE,
                    this.getX(), this.getY() + 0.5, this.getZ(),
                    2, 0.1, 0.1, 0.1,
                    0.01
            );
        } else if (healthPercent <= 0.25f) {
            ((ServerWorld)this.getWorld()).spawnParticles(
                    ParticleTypes.SMOKE,
                    this.getX(), this.getY() + 0.5, this.getZ(),
                    6, 0.2, 0.2, 0.2,
                    0.02
            );
        }
    }

    @Override
    protected boolean shouldDropLoot() {
        return false;
    }

    @Override
    public void onDeath(DamageSource source) {
        ServerWorld serverWorld = (ServerWorld) this.getWorld();

        if (!serverWorld.isClient) {
            dropItem(serverWorld, ModItems.DRONE_ITEM);

            if (linkedStationPos != null) {
                BlockEntity be = serverWorld.getBlockEntity(linkedStationPos);
                if (be instanceof StationBlockEntity station) {
                    station.clearLinkedDrone();
                }
            }

            this.remove(RemovalReason.KILLED);
        }
    }


    public void setLinkedStation(StationBlockEntity station) {
        this.linkedStationPos = station.getPos();
    }
}
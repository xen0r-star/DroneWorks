package io.github.xen0r_star.droneworks.entity;

import io.github.xen0r_star.droneworks.block.StationBlockEntity;
import io.github.xen0r_star.droneworks.client.model.DroneModel;
import io.github.xen0r_star.droneworks.client.renderer.DRONE_COLOR;
import io.github.xen0r_star.droneworks.client.renderer.DroneRenderer;
import io.github.xen0r_star.droneworks.entity.goal.DroneHarvestCropsGoal;
import io.github.xen0r_star.droneworks.entity.goal.DroneReturnToStationGoal;
import io.github.xen0r_star.droneworks.entity.goal.DroneTillAndPlantGoal;
import io.github.xen0r_star.droneworks.registry.ModItems;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.particle.ParticleTypes;


public class DroneEntity extends PathAwareEntity {
    private BlockPos linkedStationPos;
    private int particleCooldown = 0;
    private final SimpleInventory inventory = new SimpleInventory(27); // 27 slots

    private static final TrackedData<Integer> COLOR =
            DataTracker.registerData(DroneEntity.class, TrackedDataHandlerRegistry.INTEGER);


    public DroneEntity(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
        this.moveControl = new DroneMoveControl(this);
        this.setNoGravity(true);
        this.setPersistent();
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(COLOR, 0);
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20.0)
                .add(EntityAttributes.FLYING_SPEED, 0.2)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.15);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new DroneHarvestCropsGoal(this, 10, 0.15));
        this.goalSelector.add(2, new DroneTillAndPlantGoal(this, 10, 0.15));
        this.goalSelector.add(3, new DroneReturnToStationGoal(this, 1.0));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new BirdNavigation(this, world);
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
        
        // Particles
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



    // Loot
    @Override
    protected boolean shouldDropLoot() { return false; }

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



    // Station link
    public void setLinkedStationPos(BlockPos pos) {
        this.linkedStationPos = pos;
    }

    public BlockPos getLinkedStationPos() {
        return this.linkedStationPos;
    }

    // Inventory
    public SimpleInventory getInventory() {
        return inventory;
    }

    // Color
    public void setColor(DRONE_COLOR color) {
        this.dataTracker.set(COLOR, color.getValue());
    }

    public DRONE_COLOR getColor() {
        return DRONE_COLOR.fromValue(this.dataTracker.get(COLOR));
    }

}
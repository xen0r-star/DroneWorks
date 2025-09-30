package io.github.xen0r_star.droneworks.entity;

import io.github.xen0r_star.droneworks.block.StationBlockEntity;
import io.github.xen0r_star.droneworks.client.renderer.DRONE_COLOR;
import io.github.xen0r_star.droneworks.entity.goal.DroneHarvestCropsGoal;
import io.github.xen0r_star.droneworks.entity.goal.DroneReturnToStationGoal;
import io.github.xen0r_star.droneworks.entity.goal.DroneTillAndPlantGoal;
import io.github.xen0r_star.droneworks.registry.ModItems;
import net.minecraft.block.entity.BlockEntity;
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
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.particle.ParticleTypes;


public class DroneEntity extends PathAwareEntity {
    private BlockPos linkedStationPos;
    private int particleCooldown = 0;
    private boolean forcedReturnToStation = false;
    private final SimpleInventory inventory = new SimpleInventory(10);

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
        this.goalSelector.add(1, new DroneReturnToStationGoal(this, 0.15));
        this.goalSelector.add(2, new DroneHarvestCropsGoal(this, 10, 0.15));
        this.goalSelector.add(3, new DroneTillAndPlantGoal(this, 10, 0.15));
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


    public boolean areFirstSixSlotsFull() {
        for (int i = 0; i < 3 && i < inventory.size(); i++) {
            if (inventory.getStack(i).isEmpty()) return false;
        }
        return true;
    }

    public void dumpInventoryToStation() {
        if (linkedStationPos == null || this.getWorld().isClient) return;

        BlockEntity be = this.getWorld().getBlockEntity(linkedStationPos);
        if (!(be instanceof StationBlockEntity station)) return;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isEmpty()) continue;

            ItemStack remaining = insertStackIntoStation(station, stack);
            if (remaining.isEmpty()) {
                inventory.setStack(i, ItemStack.EMPTY);
            } else {
                inventory.setStack(i, remaining);
            }
        }

        station.markDirty();
    }

    private ItemStack insertStackIntoStation(StationBlockEntity station, ItemStack stack) {
        if (stack.isEmpty()) return ItemStack.EMPTY;

        ItemStack remaining = stack.copy();

        for (int slot = 0; slot < station.size(); slot++) {
            ItemStack existing = station.getStack(slot);

            if (existing.isEmpty()) {
                int toInsert = Math.min(remaining.getCount(), remaining.getMaxCount());
                ItemStack insert = remaining.copy();
                insert.setCount(toInsert);
                station.setStack(slot, insert);
                remaining.decrement(toInsert);
                if (remaining.isEmpty()) return ItemStack.EMPTY;
                else continue;
            }

            if (ItemStack.areItemsAndComponentsEqual(existing, remaining) && existing.isStackable()) {
                int canAdd = Math.min(remaining.getCount(), existing.getMaxCount() - existing.getCount());
                if (canAdd > 0) {
                    existing.increment(canAdd);
                    remaining.decrement(canAdd);
                    station.setStack(slot, existing);
                    if (remaining.isEmpty()) return ItemStack.EMPTY;
                }
            }
        }

        return remaining;
    }


    // Loot
    @Override
    protected boolean shouldDropLoot() { return false; }

    @Override
    public void onDeath(DamageSource source) {
        ServerWorld serverWorld = (ServerWorld) this.getWorld();

        if (!serverWorld.isClient) {
            dropItem(serverWorld, ModItems.DRONE_DEFAULT_ITEM);

            if (linkedStationPos != null) {
                BlockEntity be = serverWorld.getBlockEntity(linkedStationPos);
                if (be instanceof StationBlockEntity station) {
                    station.clearLinkedDrone();
                }
            }

            this.remove(RemovalReason.KILLED);
        }
    }


    @Override
    public void writeData(WriteView view) {
        super.writeData(view);
        if (linkedStationPos != null) {
            view.putInt("LinkedX", linkedStationPos.getX());
            view.putInt("LinkedY", linkedStationPos.getY());
            view.putInt("LinkedZ", linkedStationPos.getZ());
        }
    }

    @Override
    public void readData(ReadView view) {
        super.readData(view);
        if (view.contains("LinkedX")) {
            int x = view.getInt("LinkedX", 0);
            int y = view.getInt("LinkedY", 0);
            int z = view.getInt("LinkedZ", 0);
            linkedStationPos = new BlockPos(x, y, z);
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

    // Return to station
    public void forceReturnToStation() {
        this.forcedReturnToStation = true;
    }

    public void clearForcedReturnFlag() {
        this.forcedReturnToStation = false;
    }

    public boolean isForcedToReturn() {
        return this.forcedReturnToStation;
    }


    // Return work
    public void resumeWork() {
        this.clearForcedReturnFlag();
    }
}
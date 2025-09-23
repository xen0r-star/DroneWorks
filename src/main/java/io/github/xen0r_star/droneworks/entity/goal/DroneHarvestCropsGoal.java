package io.github.xen0r_star.droneworks.entity.goal;

import io.github.xen0r_star.droneworks.entity.DroneEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

import java.util.EnumSet;
import java.util.List;


public class DroneHarvestCropsGoal extends Goal {
    private final DroneEntity drone;
    private final int searchRadius;
    private final double speed;
    private BlockPos targetCrop;

    private enum HarvestState {
        MOVING,          // en déplacement vers la carotte
        CHARGING,        // animation beam_start
        BREAKING,        // carotte cassée
        ENDING           // animation beam_end
    }

    private HarvestState state = HarvestState.MOVING;
    private int stateTicks = 0;


    public DroneHarvestCropsGoal(DroneEntity drone, int searchRadius, double speed) {
        this.drone = drone;
        this.searchRadius = searchRadius;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (drone.getLinkedStationPos() == null) {
            return false;
        }

        targetCrop = findNearestMatureCrop();
        return targetCrop != null;
    }

    @Override
    public void start() { }

    @Override
    public void stop() {
        state = HarvestState.MOVING;
        stateTicks = 0;
        targetCrop = null;
        drone.getNavigation().stop();
    }

    @Override
    public boolean shouldContinue() {
        return targetCrop != null;
    }


    @Override
    public void tick() {
        if (targetCrop == null) return;

        // --- calcul de la direction vers la cible ---
        double targetX = targetCrop.getX() + 0.5;
        double targetY = targetCrop.getY() + 1.5;
        double targetZ = targetCrop.getZ() + 0.5;

        if (!isPathClear(targetX, targetY, targetZ)) {
            stop();
            return;
        }

        double dx = targetX - drone.getX();
        double dy = targetY - drone.getY();
        double dz = targetZ - drone.getZ();
        double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

        switch (state) {
            case MOVING -> {
                if (dist <= 0.1) {
                    state = HarvestState.CHARGING;
                    stateTicks = 0;
//                    drone.playAnimation("beam_start");
                    drone.setVelocity(Vec3d.ZERO);
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

            case CHARGING -> {
                stateTicks++;
                if (stateTicks >= 20) {
                    BlockState stateBlock = drone.getWorld().getBlockState(targetCrop);

                    if (stateBlock.getBlock() instanceof CropBlock crop && crop.isMature(stateBlock)) {
                        ServerWorld serverWorld = (ServerWorld) drone.getWorld();

                        List<ItemStack> drops = Block.getDroppedStacks(
                                stateBlock,
                                serverWorld,
                                targetCrop,
                                serverWorld.getBlockEntity(targetCrop),
                                drone,
                                ItemStack.EMPTY
                        );

                        for (ItemStack stack : drops) {
                            drone.getInventory().addStack(stack);
                        }

                        serverWorld.setBlockState(targetCrop, crop.withAge(0), 3);
                    }

                    state = HarvestState.ENDING;
                    stateTicks = 0;
//                    drone.playAnimation("beam_end");
                }
            }

            case ENDING -> {
                stateTicks++;
                if (stateTicks >= 10) {
                    stop();
                }
            }

            case BREAKING -> {}
        }
    }

    private boolean isPathClear(double targetX, double targetY, double targetZ) {
        Vec3d start = drone.getPos();
        Vec3d end = new Vec3d(targetX, targetY, targetZ);

        BlockHitResult result = drone.getWorld().raycast(new RaycastContext(
                start,
                end,
                RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE,
                drone
        ));

        return result.getType() == HitResult.Type.MISS;
    }

    private BlockPos findNearestMatureCrop() {
        BlockPos center = drone.getLinkedStationPos();
        BlockPos nearest = null;
        double bestDist = Double.MAX_VALUE;

        for (BlockPos pos : BlockPos.iterate(
                center.add(-searchRadius, -1, -searchRadius),
                center.add(searchRadius, 1, searchRadius))) {

            BlockState st = drone.getWorld().getBlockState(pos);
            if (st.isIn(BlockTags.CROPS) && st.getBlock() instanceof CropBlock crop) {
                if (crop.isMature(st)) {
                    double ds = drone.squaredDistanceTo(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
                    if (ds < bestDist) {
                        bestDist = ds;
                        nearest = pos.toImmutable();
                    }
                }
            }
        }
        return nearest;
    }
}
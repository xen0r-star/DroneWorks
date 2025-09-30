package io.github.xen0r_star.droneworks.block;

import io.github.xen0r_star.droneworks.client.renderer.DRONE_COLOR;
import io.github.xen0r_star.droneworks.entity.DroneEntity;
import io.github.xen0r_star.droneworks.registry.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.UUID;


public class StationBlock extends OrientableBlock {
    public static final BooleanProperty LINKED = BooleanProperty.of("linked");


    public StationBlock(Block.Settings settings) {
        super(settings);
        this.setDefaultState(
            this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(LINKED, false)
        );
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StationBlockEntity(pos, state);
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            Hand hand = player.getActiveHand();
            ItemStack stack = player.getStackInHand(hand);
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof StationBlockEntity station) {
                if (!stack.isEmpty() && stack.isIn(ModTags.DRONES)) {
                    if (station.hasLinkedDrone()) {
                        UUID uuid = station.getLinkedDroneUuid();
                        Entity linked = world.getEntity(uuid);

                        if (linked instanceof DroneEntity) {
                            player.sendMessage(
                                    Text.translatable("message.drone.already_linked")
                                            .styled(style -> style.withColor(Formatting.RED)),
                                    true
                            );
                            return ActionResult.FAIL;

                        } else {
                            station.clearLinkedDrone();
                        }
                    }

                    float facing = switch (state.get(StationBlock.FACING)) {
                        case NORTH -> 180f;
                        case WEST -> 90f;
                        case EAST -> 270f;
                        default -> 0f;
                    };

                    DroneEntity drone = new DroneEntity(ModEntities.DRONE, world);
                    drone.refreshPositionAndAngles(
                            pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                            facing, 0
                    );
                    drone.setPersistent();
                    drone.setLinkedStationPos(station.getPos());
                    world.spawnEntity(drone);

                    Item droneColor = stack.getItem();
                    if (droneColor == ModItems.DRONE_BLUE_ITEM) {

                    }

                    if (droneColor == ModItems.DRONE_WHITE_ITEM) {
                        drone.setColor(DRONE_COLOR.WHITE);
                    } else if (droneColor == ModItems.DRONE_LIGHT_GRAY_ITEM) {
                        drone.setColor(DRONE_COLOR.LIGHT_GRAY);
                    } else if (droneColor == ModItems.DRONE_GRAY_ITEM) {
                        drone.setColor(DRONE_COLOR.LIGHT_GRAY);
                    } else if (droneColor == ModItems.DRONE_BLACK_ITEM) {
                        drone.setColor(DRONE_COLOR.BLACK);
                    } else if (droneColor == ModItems.DRONE_BROWN_ITEM) {
                        drone.setColor(DRONE_COLOR.BROWN);
                    } else if (droneColor == ModItems.DRONE_RED_ITEM) {
                        drone.setColor(DRONE_COLOR.RED);
                    } else if (droneColor == ModItems.DRONE_ORANGE_ITEM) {
                        drone.setColor(DRONE_COLOR.ORANGE);
                    } else if (droneColor == ModItems.DRONE_YELLOW_ITEM) {
                        drone.setColor(DRONE_COLOR.YELLOW);
                    } else if (droneColor == ModItems.DRONE_LIME_ITEM) {
                        drone.setColor(DRONE_COLOR.LIME);
                    } else if (droneColor == ModItems.DRONE_GREEN_ITEM) {
                        drone.setColor(DRONE_COLOR.GREEN);
                    } else if (droneColor == ModItems.DRONE_CYAN_ITEM) {
                        drone.setColor(DRONE_COLOR.CYAN);
                    } else if (droneColor == ModItems.DRONE_LIGHT_BLUE_ITEM) {
                        drone.setColor(DRONE_COLOR.LIGHT_BLUE);
                    } else if (droneColor == ModItems.DRONE_BLUE_ITEM) {
                        drone.setColor(DRONE_COLOR.BLUE);
                    } else if (droneColor == ModItems.DRONE_PURPLE_ITEM) {
                        drone.setColor(DRONE_COLOR.PURPLE);
                    } else if (droneColor == ModItems.DRONE_MAGENTA_ITEM) {
                        drone.setColor(DRONE_COLOR.MAGENTA);
                    } else if (droneColor == ModItems.DRONE_PINK_ITEM) {
                        drone.setColor(DRONE_COLOR.PINK);
                    } else {
                        drone.setColor(DRONE_COLOR.DEFAULT);
                    }



                    station.setLinkedDrone(drone);
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        ModCriteria.DRONE_LINKED.trigger(serverPlayer, drone);
                    }

                    BlockState newState = state.with(StationBlock.LINKED, true);
                    world.setBlockState(pos, newState, 3);

                    player.sendMessage(
                        Text.translatable("message.drone.linked")
                            .styled(style -> style.withColor(Formatting.GREEN)),
                        true
                    );

                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }

                    return ActionResult.SUCCESS;

                } else {
                    player.openHandledScreen(station);
                    return ActionResult.SUCCESS;
                }
            }
        }

        return ActionResult.PASS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LINKED);
    }
}
package io.github.xen0r_star.droneworks.block;

import io.github.xen0r_star.droneworks.Main;
import io.github.xen0r_star.droneworks.entity.DroneEntity;
import io.github.xen0r_star.droneworks.registry.ModItems;
import io.github.xen0r_star.droneworks.registry.OrientableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
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
                if (stack.isOf(ModItems.DRONE_ITEM)) {
                    if (station.hasLinkedDrone()) {
                        UUID uuid = station.getLinkedDroneUuid();
                        Entity linked = ((ServerWorld) world).getEntity(uuid);

                        if (linked instanceof DroneEntity) {
                            player.sendMessage(
                                    Text.literal("message.drone.already_linked")
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

                    DroneEntity drone = new DroneEntity(Main.DRONE, world);
                    drone.refreshPositionAndAngles(
                            pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                            facing, 0
                    );
                    drone.setPersistent();
                    drone.setLinkedStationPos(station.getPos());
                    world.spawnEntity(drone);

                    station.setLinkedDrone(drone);

                    BlockState newState = state.with(StationBlock.LINKED, true);
                    world.setBlockState(pos, newState, 3);

                    player.sendMessage(
                            Text.literal("message.drone.linked")
                                    .styled(style -> style.withColor(Formatting.GREEN)),
                            true
                    );

                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }

                    return ActionResult.SUCCESS;

                } else {
                    player.openHandledScreen(station);
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
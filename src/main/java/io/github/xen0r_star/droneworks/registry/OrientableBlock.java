package io.github.xen0r_star.droneworks.registry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.Direction;

public class OrientableBlock extends Block {
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;

    public OrientableBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        PlayerEntity player = ctx.getPlayer();
        if (player != null) {
            return this.getDefaultState().with(FACING, player.getHorizontalFacing().getOpposite());
        }
        return this.getDefaultState();
    }
}

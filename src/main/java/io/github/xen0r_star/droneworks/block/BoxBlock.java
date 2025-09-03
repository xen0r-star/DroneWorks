//package io.github.xen0r_star.droneworks.block;
//
//import com.mojang.serialization.MapCodec;
////import io.github.xen0r_star.droneworks.screen.BoxScreenHandler;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.BlockWithEntity;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.screen.NamedScreenHandlerFactory;
//import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//public class BoxBlock extends BlockWithEntity {
//
//    public BoxBlock(Settings settings) {
//        super(settings);
//    }
//
//    @Override
//    protected MapCodec<? extends BlockWithEntity> getCodec() {
//        return createCodec(BoxBlock::new);
//    }
//
//    @Override
//    protected BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//        return new BoxBlockEntity(pos, state);
//    }
//
//    @Override
//    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
//        BlockEntity blockEntity = world.getBlockEntity(pos);
//        if (blockEntity instanceof BoxBlockEntity boxEntity) {
//            return new SimpleNamedScreenHandlerFactory(
//                    (syncId, playerInventory, player) -> new BoxScreenHandler(syncId, playerInventory, boxEntity),
//                    blockEntity.getDisplayName()
//            );
//        }
//        return null;
//    }
//}


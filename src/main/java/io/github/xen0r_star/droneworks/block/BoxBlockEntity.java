//package io.github.xen0r_star.droneworks.block;
//
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.block.BlockState;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.registry.RegistryWrapper;
//import io.github.xen0r_star.droneworks.registry.ModBlockEntities;
//import net.minecraft.world.World;
//
//public class BoxBlockEntity extends BlockEntity {
//
//    private int clicks = 0;
//    private int ticksSinceLast = 0;
//
//    public BoxBlockEntity(BlockPos pos, BlockState state) {
//        super(ModBlockEntities.BOX_BLOCK_ENTITY, pos, state);
//    }
//
//    public int getClicks() {
//        return clicks;
//    }
//
//    public void incrementClicks() {
//        if (ticksSinceLast < 10) return;
//        ticksSinceLast = 0;
//        clicks++;
//        markDirty(); // indique que les données ont changé
//    }
//
//    public static void tick(World world, BlockPos pos, BlockState state, BoxBlockEntity entity) {
//        entity.ticksSinceLast++;
//    }
//
//    @Override
//    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
//        return createNbt(registryLookup);
//    }
//}

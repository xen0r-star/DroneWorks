package io.github.xen0r_star.droneworks.block;

import io.github.xen0r_star.droneworks.Main;
import io.github.xen0r_star.droneworks.entity.DroneEntity;
import io.github.xen0r_star.droneworks.registry.ModItems;
import io.github.xen0r_star.droneworks.registry.OrientableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StationBlock extends OrientableBlock {
    public StationBlock(Block.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) { // Serveur uniquement
            Hand hand = player.getActiveHand();
            ItemStack stack = player.getStackInHand(hand);

            // Vérifie si le joueur tient le drone
            if (stack.isOf(ModItems.DRONE_ITEM)) {
                // Crée l'entité drone
//                DroneEntity drone = new DroneEntity(world);
//                drone.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0);

                // Spawn dans le monde
//                world.spawnEntity(drone);

                // Retire l'item si le joueur n'est pas en mode créatif
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }

                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}


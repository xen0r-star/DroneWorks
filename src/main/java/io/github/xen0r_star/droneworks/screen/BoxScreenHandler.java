//package io.github.xen0r_star.droneworks.screen;
//
//import io.github.xen0r_star.droneworks.registry.ModScreenHandlers;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.screen.ScreenHandler;
//import net.minecraft.screen.ScreenHandlerType;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.Inventory;
//import net.minecraft.inventory.SimpleInventory;
//import net.minecraft.util.Identifier;
//
//public class BoxScreenHandler extends ScreenHandler {
//
//    private static Inventory inventory;
//
//    public BoxScreenHandler(int syncId, PlayerInventory playerInventory) {
//        super(ModScreenHandlers.BOX_SCREEN_HANDLER, syncId);
////        this(syncId, playerInventory, new SimpleInventory(9)); // exemple : 9 slots
//    }
//
//    public BoxScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
//        super(ModScreenHandlers.BOX_SCREEN_HANDLER, syncId);
//        this.inventory = inventory;
//        // ajouter ici les slots
//    }
//
//    @Override
//    public ItemStack quickMove(PlayerEntity player, int slot) {
//        return null;
//    }
//
//    @Override
//    public boolean canUse(net.minecraft.entity.player.PlayerEntity player) {
//        return true;
//    }
//}

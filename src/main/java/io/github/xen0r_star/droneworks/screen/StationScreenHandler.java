package io.github.xen0r_star.droneworks.screen;

import io.github.xen0r_star.droneworks.registry.ModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;


public class StationScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public StationScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(18));
    }

    public StationScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.STATION_SCREEN_HANDLER, syncId);
        this.inventory = inventory;
        checkSize(inventory, 18);
        inventory.onOpen(playerInventory.player);

        addBlockInventorySlots();
        addPlayerInventorySlots(playerInventory);
    }

    

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    private void addBlockInventorySlots() {
        int x=80;
        int y=17;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 5; col++) {
                int slotIndex = col + row * 5;
                int slotX = x + col * 18;
                int slotY = y + row * 18;
                this.addSlot(new Slot(inventory, slotIndex, slotX, slotY));
            }
        }

        this.addSlot(new Slot(inventory, 15, 8, 17));
        this.addSlot(new Slot(inventory, 16, 8, 35));
        this.addSlot(new Slot(inventory, 17, 8, 53));
    }

    private void addPlayerInventorySlots(PlayerInventory playerInventory) {
        // Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int slotIndex = col + row * 9 + 9;
                int slotX = 8 + col * 18;
                int slotY = 84 + row * 18;
                this.addSlot(new Slot(playerInventory, slotIndex, slotX, slotY));
            }
        }

        // Hotbar
        for (int col = 0; col < 9; col++) {
            int slotX = 8 + col * 18;
            int slotY = 84 + 58;
            this.addSlot(new Slot(playerInventory, col, slotX, slotY));
        }
    }
}

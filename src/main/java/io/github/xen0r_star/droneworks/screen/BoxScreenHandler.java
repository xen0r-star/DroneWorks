package io.github.xen0r_star.droneworks.screen;

import io.github.xen0r_star.droneworks.registry.ModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class BoxScreenHandler extends ScreenHandler {
    private final Inventory boxInventory;

    public BoxScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ModScreenHandlers.BOX_SCREEN_HANDLER, syncId);
        this.boxInventory = new SimpleInventory(7);

        addBlockInventorySlots();
        addPlayerInventorySlots(playerInventory, 8, 101);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    private void addBlockInventorySlots() {
        this.addSlot(new Slot(boxInventory, 0, 116, 22));
        this.addSlot(new Slot(boxInventory, 1, 94,  44));
        this.addSlot(new Slot(boxInventory, 2, 116, 44));
        this.addSlot(new Slot(boxInventory, 3, 138, 44));
        this.addSlot(new Slot(boxInventory, 4, 94,  66));
        this.addSlot(new Slot(boxInventory, 5, 116, 66));
        this.addSlot(new Slot(boxInventory, 6, 138, 66));
    }

    private void addPlayerInventorySlots(PlayerInventory playerInventory, int x, int y) {
        // Les 3 rangées principales (3*9 slots)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int slotIndex = col + row * 9 + 9;
                int slotX = x + col * 18;
                int slotY = y + row * 18;
                this.addSlot(new Slot(playerInventory, slotIndex, slotX, slotY));
            }
        }

        // La hotbar
        for (int col = 0; col < 9; col++) {
            int slotX = x + col * 18;
            int slotY = y + 58;
            this.addSlot(new Slot(playerInventory, col, slotX, slotY));
        }
    }
}

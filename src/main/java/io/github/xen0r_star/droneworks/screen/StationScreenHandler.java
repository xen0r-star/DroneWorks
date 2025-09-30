package io.github.xen0r_star.droneworks.screen;

import io.github.xen0r_star.droneworks.registry.ModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;


public class StationScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final BlockPos pos;
    private final boolean isPlaying;

    public StationScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(21), BlockPos.ORIGIN, true);
    }

    public StationScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, new SimpleInventory(21), buf.readBlockPos(), buf.readBoolean());
    }

    public StationScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, BlockPos pos, boolean isPlaying) {
        super(ModScreenHandlers.STATION_SCREEN_HANDLER, syncId);
        this.inventory = inventory;
        this.pos = pos;
        this.isPlaying = isPlaying;

        checkSize(inventory, 21);
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
        int x=44;
        int y=17;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 7; col++) {
                int slotIndex = col + row * 7;
                int slotX = x + col * 18;
                int slotY = y + row * 18;
                this.addSlot(new Slot(inventory, slotIndex, slotX, slotY));
            }
        }
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

    public BlockPos getBlockPos() {
        return pos;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}

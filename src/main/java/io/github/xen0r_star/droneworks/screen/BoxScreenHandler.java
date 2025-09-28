package io.github.xen0r_star.droneworks.screen;

import io.github.xen0r_star.droneworks.block.BoxBlockEntity;
import io.github.xen0r_star.droneworks.registry.ModItems;
import io.github.xen0r_star.droneworks.registry.ModScreenHandlers;
import io.github.xen0r_star.droneworks.registry.ModTags;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;


public class BoxScreenHandler extends ScreenHandler {
    private final Inventory boxInventory;

    public BoxScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ModScreenHandlers.BOX_SCREEN_HANDLER, syncId);
        this.boxInventory = new SimpleInventory(7);

        addBlockInventorySlots();
        addPlayerInventorySlots(playerInventory);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.boxInventory.canPlayerUse(player);
    }

    @Override
    public void onContentChanged(Inventory inv) {
        super.onContentChanged(inv);
        if (inv instanceof BlockEntity inventory) {
            inventory.markDirty();
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            int containerSlots = 7;

            if (index < containerSlots) {
                if (!this.insertItem(originalStack, containerSlots, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.insertItem(originalStack, 0, containerSlots, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    private void addBlockInventorySlots() {
        this.addSlot(new Slot(boxInventory, 0, 116, 22) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.ANTENNA_ITEM);
            }
        });

        this.addSlot(new Slot(boxInventory, 1, 94,  44) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.PROPELLER_ITEM);
            }
        });

        this.addSlot(new Slot(boxInventory, 2, 116, 44) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.BODY_ITEM);
            }
        });

        this.addSlot(new Slot(boxInventory, 3, 138, 44) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.PROPELLER_ITEM);
            }
        });

        this.addSlot(new Slot(boxInventory, 4, 94,  66) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.BATTERY_ITEM);
            }
        });

        this.addSlot(new Slot(boxInventory, 5, 116, 66) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.CORE_ITEM);
            }
        });

        this.addSlot(new Slot(boxInventory, 6, 138, 66) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.SPRAY_PAINTS);
            }
        });
    }

    private void addPlayerInventorySlots(PlayerInventory playerInventory) {
        // Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int slotIndex = col + row * 9 + 9;
                int slotX = 8 + col * 18;
                int slotY = 101 + row * 18;
                this.addSlot(new Slot(playerInventory, slotIndex, slotX, slotY));
            }
        }

        // Hotbar
        for (int col = 0; col < 9; col++) {
            int slotX = 8 + col * 18;
            int slotY = 101 + 58;
            this.addSlot(new Slot(playerInventory, col, slotX, slotY));
        }
    }
}

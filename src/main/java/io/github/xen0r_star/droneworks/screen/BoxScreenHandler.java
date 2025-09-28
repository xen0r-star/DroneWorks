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
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;


public class BoxScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public BoxScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(7));
    }

    public BoxScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.BOX_SCREEN_HANDLER, syncId);
        this.inventory = inventory;
        checkSize(inventory, 7);
        inventory.onOpen(playerInventory.player);

        addBlockInventorySlots();
        addPlayerInventorySlots(playerInventory);
    }


    public boolean isCraftButtonActive() {
        System.out.println("Ready111");
        if (this.inventory instanceof BoxBlockEntity box) {
            System.out.println("dddddddd");
            return !box.getCrafting() && !box.getReady() && box.allSlotsFilled();
        }
        return false;
    }

    public boolean isRetrieveButtonActive() {
        if (this.inventory instanceof BoxBlockEntity station) {
            return station.canRetrieve();
        }
        return false;
    }

//    public void startCraftingServerSide() {
//        blockEntity.startCrafting();
//    }
//
//    public void retrieveServerSide(PlayerEntity player) {
//        blockEntity.retrieve(player);
//    }
//
//    public BoxBlockEntity getBlockEntity() {
//        return this.blockEntity;
//    }



    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
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
        this.addSlot(new Slot(inventory, 0, 116, 22) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.ANTENNA_ITEM);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        this.addSlot(new Slot(inventory, 1, 94,  44) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.PROPELLER_ITEM);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        this.addSlot(new Slot(inventory, 2, 116, 44) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.CASING_ITEM);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        this.addSlot(new Slot(inventory, 3, 138, 44) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.PROPELLER_ITEM);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        this.addSlot(new Slot(inventory, 4, 94,  66) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.BATTERY_ITEM);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        this.addSlot(new Slot(inventory, 5, 116, 66) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.CORE_ITEM);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        this.addSlot(new Slot(inventory, 6, 138, 66) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.SPRAY_PAINTS);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
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

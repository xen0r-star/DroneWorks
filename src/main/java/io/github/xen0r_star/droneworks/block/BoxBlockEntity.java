package io.github.xen0r_star.droneworks.block;

import io.github.xen0r_star.droneworks.registry.ModItems;
import io.github.xen0r_star.droneworks.screen.BoxScreenHandler;
import io.github.xen0r_star.droneworks.registry.ModBlockEntities;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;


public class BoxBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, Inventory  {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(7, ItemStack.EMPTY);

    private int progress = 0;
    private boolean crafting = false;
    private boolean ready = false;
    private final int TIME_CRAFTING = 600; // 30 sec * 20 ticks/sec


    public BoxBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BOX_BLOCK_ENTITY, pos, state);
    }

    public boolean allSlotsFilled() {
        return items.stream().noneMatch(ItemStack::isEmpty);
    }

    public void startCrafting() {
        if (allSlotsFilled()) {
            crafting = true;
            progress = 0;
            ready = false;
            markDirty();
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, BoxBlockEntity be) {
        if (!world.isClient) {
            be.tick();
        }
    }

    public void tick() {
        if (crafting) {
            progress++;
            if (progress >= TIME_CRAFTING) {
                crafting = false;
                ready = true;
                progress = TIME_CRAFTING;
                markDirty();
            }
        }
    }

    public boolean canRetrieve() {
        return ready;
    }

    public void retrieve(PlayerEntity player) {
        if (ready) {
            ItemStack drone = new ItemStack(ModItems.DRONE_DEFAULT_ITEM);
            if (!player.getInventory().insertStack(drone)) {
                player.dropItem(drone, false);
            }
            ready = false;

            items.clear();
            markDirty();
        }
    }

    public int getProgress() {
        return progress;
    }

    public int getProgressMax() {
        return TIME_CRAFTING;
    }

    public boolean getCrafting() {
        return crafting;
    }

    public boolean getReady() {
        return ready;
    }


    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStack(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(items, slot);
    }

    @Override
    public ItemStack removeStack(int slot, int count) {
        return Inventories.splitStack(items, slot, count);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        items.set(slot, stack);
        markDirty();
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, items);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, items);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return pos.isWithinDistance(player.getPos(), 8.0);
    }

    @Override public void clear() {
        items.clear();
        markDirty();
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Boîte Droneworks");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BoxScreenHandler(syncId, playerInventory, this);
    }
}

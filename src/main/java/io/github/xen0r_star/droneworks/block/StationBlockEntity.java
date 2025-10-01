package io.github.xen0r_star.droneworks.block;

import io.github.xen0r_star.droneworks.entity.DroneEntity;
import io.github.xen0r_star.droneworks.registry.ModBlockEntities;
import io.github.xen0r_star.droneworks.screen.StationScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Uuids;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;


public class StationBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, Inventory {
    private UUID linkedDroneUuid;
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(27, ItemStack.EMPTY);
    private boolean isPlaying = true;


    public StationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STATION_BLOCK_ENTITY, pos, state);
    }

    public void setLinkedDrone(DroneEntity drone) {
        this.linkedDroneUuid = drone.getUuid();
        markDirty();
    }

    public UUID getLinkedDroneUuid() {
        return linkedDroneUuid;
    }

    public boolean hasLinkedDrone() {
        return linkedDroneUuid != null;
    }

    public void clearLinkedDrone() {
        if (world != null && !world.isClient) {
            this.linkedDroneUuid = null;

            BlockState state = world.getBlockState(this.pos);
            if (state.contains(StationBlock.LINKED)) {
                world.setBlockState(pos, state.with(StationBlock.LINKED, false), 3);
            }
        }

        markDirty();
    }


    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        if (linkedDroneUuid != null) {
            view.put("LinkedDrone", Uuids.INT_STREAM_CODEC, linkedDroneUuid);
        }
        view.putBoolean("IsPlaying", this.isPlaying);
        Inventories.writeData(view, items);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        view.read("LinkedDrone", Uuids.INT_STREAM_CODEC)
            .map(uuid -> {
                linkedDroneUuid = uuid;
                return linkedDroneUuid;
            });
        this.isPlaying = view.getBoolean("IsPlaying", true);
        Inventories.readData(view, items);
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
    public boolean canPlayerUse(PlayerEntity player) {
        return pos.isWithinDistance(player.getPos(), 8.0);
    }

    @Override public void clear() {
        items.clear();
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Boîte Droneworks");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new StationScreenHandler(syncId, playerInventory, this);
    }
}
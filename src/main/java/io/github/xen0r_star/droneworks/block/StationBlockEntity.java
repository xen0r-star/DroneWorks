package io.github.xen0r_star.droneworks.block;

import io.github.xen0r_star.droneworks.entity.DroneEntity;
import io.github.xen0r_star.droneworks.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.Uuids;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;


public class StationBlockEntity extends BlockEntity {
    private UUID linkedDroneUuid;

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
            // System.out.println("[StationBE] writeData UUID=" + linkedDroneUuid);
        }
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        view.read("LinkedDrone", Uuids.INT_STREAM_CODEC)
                .map(uuid -> {
                    linkedDroneUuid = uuid;
                    // System.out.println("[StationBE] readData UUID=" + linkedDroneUuid);
                    return linkedDroneUuid;
                });
    }

}
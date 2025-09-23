package io.github.xen0r_star.droneworks.registry;

import io.github.xen0r_star.droneworks.Main;
import io.github.xen0r_star.droneworks.block.BoxBlockEntity;
import io.github.xen0r_star.droneworks.block.StationBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;


public class ModBlockEntities {
    public static BlockEntityType<BoxBlockEntity> BOX_BLOCK_ENTITY;
    public static BlockEntityType<StationBlockEntity> STATION_BLOCK_ENTITY;

    public static void register() {
        BOX_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Main.MOD_ID, "box_block_entity"),
            FabricBlockEntityTypeBuilder.create(BoxBlockEntity::new, ModBlocks.BOX_BLOCK).build()
        );

        STATION_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Main.MOD_ID, "station_block_entity"),
            FabricBlockEntityTypeBuilder.create(StationBlockEntity::new, ModBlocks.STATION_BLOCK).build()
        );
    }
}

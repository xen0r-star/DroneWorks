package io.github.xen0r_star.droneworks;

import io.github.xen0r_star.droneworks.entity.DroneEntity;
//import io.github.xen0r_star.droneworks.registry.ModBlockEntities;
//import io.github.xen0r_star.droneworks.registry.ModBlockEntities;
import io.github.xen0r_star.droneworks.registry.ModBlocks;
import io.github.xen0r_star.droneworks.registry.ModItemGroups;
import io.github.xen0r_star.droneworks.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class Main implements ModInitializer {
    public static final String MOD_ID = "droneworks";

    public static final RegistryKey<EntityType<?>> DRONE_KEY =
        RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MOD_ID, "drone"));

    public static final EntityType<DroneEntity> DRONE = EntityType.Builder.create(
        DroneEntity::new, SpawnGroup.CREATURE
    ).dimensions(0.5f, 0.5f)
    .build(DRONE_KEY);


    @Override
    public void onInitialize() {
        Registry.register(Registries.ENTITY_TYPE, DRONE_KEY.getValue(), DRONE);
        FabricDefaultAttributeRegistry.register(DRONE, DroneEntity.createMobAttributes());

        ModBlocks.init();
        //ModBlockEntities.register();
        ModItems.init();
        ModItemGroups.init();

        System.out.println("[Droneworks] Mod initialisé");
    }
}


package io.github.xen0r_star.droneworks.registry;

import io.github.xen0r_star.droneworks.Main;
import io.github.xen0r_star.droneworks.entity.DroneEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final RegistryKey<EntityType<?>> DRONE_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Main.MOD_ID, "drone"));

    public static final EntityType<DroneEntity> DRONE = EntityType.Builder.create(
                    DroneEntity::new, SpawnGroup.CREATURE
            ).dimensions(0.5f, 0.5f)
            .build(DRONE_KEY);

    public static void init() {
        Registry.register(Registries.ENTITY_TYPE, DRONE_KEY.getValue(), DRONE);
        FabricDefaultAttributeRegistry.register(DRONE, DroneEntity.createMobAttributes());

        System.out.println("[Droneworks] ModEntities initialisés");
    }
}

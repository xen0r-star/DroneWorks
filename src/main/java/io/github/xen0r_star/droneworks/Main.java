package io.github.xen0r_star.droneworks;

import io.github.xen0r_star.droneworks.entity.DroneEntity;
import io.github.xen0r_star.droneworks.registry.ModBlocks;
import io.github.xen0r_star.droneworks.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main implements ModInitializer {
	public static final String MOD_ID = "droneworks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final EntityType<DroneEntity> DRONE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "drone"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DroneEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 0.75f))
                    .build()
    );

	@Override
	public void onInitialize() {
        ModBlocks.init();
        ModItems.init();

        FabricDefaultAttributeRegistry.register(DRONE, DroneEntity.createMobAttributes());

        System.out.println("[Droneworks] Mod initialisé");
	}
}

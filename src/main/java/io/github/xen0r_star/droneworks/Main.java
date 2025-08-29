package io.github.xen0r_star.droneworks;

import io.github.xen0r_star.droneworks.entity.custom.DroneEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final String MOD_ID = "droneworks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EntityType<DroneEntity> DRONE_ENTITY = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(MOD_ID, "drone"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DroneEntity::new)
					.dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(DRONE_ENTITY, DroneEntity.createAttributes());
	}
}

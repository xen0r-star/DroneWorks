package io.github.xen0r_star.droneworks.registry;

import io.github.xen0r_star.droneworks.Main;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public final class ModItems {
    public static final Item BOX_ITEM = Items.register(
        ModBlocks.BOX_BLOCK,
        new Item.Settings()
    );

    public static final Item STATION_BLOCK = Items.register(
        ModBlocks.STATION_BLOCK,
        new Item.Settings()
    );

    static RegistryKey<Item> DRONE_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "drone_item"));
    public static Item DRONE_ITEM = Registry.register(
        Registries.ITEM,
        DRONE_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(1)
            .registryKey(DRONE_ITEM_KEY)
        )
    );

    static RegistryKey<Item> BATTERY_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "battery_item"));
    public static Item BATTERY_ITEM = Registry.register(
        Registries.ITEM,
        BATTERY_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(BATTERY_ITEM_KEY)
        )
    );

    static RegistryKey<Item> ANTENNA_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "antenna_item"));
    public static Item ANTENNA_ITEM = Registry.register(
        Registries.ITEM,
        ANTENNA_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(ANTENNA_ITEM_KEY)
        )
    );

    private ModItems() { }

    public static void init() {
        System.out.println("[Droneworks] ModItems initialisés");
    }
}
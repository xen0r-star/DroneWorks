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

    static RegistryKey<Item> BODY_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "body_item"));
    public static Item BODY_ITEM = Registry.register(
        Registries.ITEM,
        BODY_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(BODY_ITEM_KEY)
        )
    );

    static RegistryKey<Item> PROPELLER_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "propeller_item"));
    public static Item PROPELLER_ITEM = Registry.register(
        Registries.ITEM,
        PROPELLER_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(PROPELLER_ITEM_KEY)
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

    static RegistryKey<Item> CORE_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "core_item"));
    public static Item CORE_ITEM = Registry.register(
        Registries.ITEM,
        CORE_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(CORE_ITEM_KEY)
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



    static RegistryKey<Item> SPRAY_PAINT_BLACK_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_black_item"));
    public static Item SPRAY_PAINT_BLACK_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_BLACK_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_BLACK_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_BLUE_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_blue_item"));
    public static Item SPRAY_PAINT_BLUE_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_BLUE_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_BLUE_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_BROWN_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_brown_item"));
    public static Item SPRAY_PAINT_BROWN_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_BROWN_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_BROWN_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_CYAN_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_cyan_item"));
    public static Item SPRAY_PAINT_CYAN_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_CYAN_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_CYAN_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_GRAY_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_gray_item"));
    public static Item SPRAY_PAINT_GRAY_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_GRAY_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_GRAY_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_GREEN_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_green_item"));
    public static Item SPRAY_PAINT_GREEN_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_GREEN_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_GREEN_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_LIGHT_BLUE_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_light_blue_item"));
    public static Item SPRAY_PAINT_LIGHT_BLUE_ITEM = Registry.register(
            Registries.ITEM,
            SPRAY_PAINT_LIGHT_BLUE_ITEM_KEY,
            new Item(new Item.Settings()
                    .maxCount(64)
                    .registryKey(SPRAY_PAINT_LIGHT_BLUE_ITEM_KEY)
            )
    );


    static RegistryKey<Item> SPRAY_PAINT_LIGHT_GRAY_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_light_gray_item"));
    public static Item SPRAY_PAINT_LIGHT_GRAY_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_LIGHT_GRAY_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_LIGHT_GRAY_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_LIME_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_lime_item"));
    public static Item SPRAY_PAINT_LIME_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_LIME_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_LIME_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_MAGENTA_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_magenta_item"));
    public static Item SPRAY_PAINT_MAGENTA_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_MAGENTA_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_MAGENTA_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_ORANGE_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_orange_item"));
    public static Item SPRAY_PAINT_ORANGE_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_ORANGE_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_ORANGE_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_PINK_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_pink_item"));
    public static Item SPRAY_PAINT_PINK_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_PINK_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_PINK_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_PURPLE_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_purple_item"));
    public static Item SPRAY_PAINT_PURPLE_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_PURPLE_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_PURPLE_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_RED_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_red_item"));
    public static Item SPRAY_PAINT_RED_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_RED_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_RED_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_WHITE_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_white_item"));
    public static Item SPRAY_PAINT_WHITE_ITEM = Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_WHITE_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_WHITE_ITEM_KEY)
        )
    );


    static RegistryKey<Item> SPRAY_PAINT_YELLOW_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Main.MOD_ID, "spray_paint_yellow_item"));
    public static Item SPRAY_PAINT_YELLOW_ITEM= Registry.register(
        Registries.ITEM,
        SPRAY_PAINT_YELLOW_ITEM_KEY,
        new Item(new Item.Settings()
            .maxCount(64)
            .registryKey(SPRAY_PAINT_YELLOW_ITEM_KEY)
        )
    );



    private ModItems() { }

    public static void init() {
        System.out.println("[Droneworks] ModItems initialisés");
    }
}
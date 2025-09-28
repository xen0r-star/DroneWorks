package io.github.xen0r_star.droneworks.registry;

import io.github.xen0r_star.droneworks.Main;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {
    public static final ItemGroup DRONEWORKS_GROUP = Registry.register(
        Registries.ITEM_GROUP,
        Identifier.of(Main.MOD_ID, "droneworks"),
        ItemGroup.create(null, -1)
            .displayName(Text.translatable("itemGroup.droneworks"))
            .icon(() -> new ItemStack(ModItems.BOX_ITEM))
            .entries((context, entries) -> {
                entries.add(ModItems.BOX_ITEM);
                entries.add(ModItems.STATION_BLOCK);
                entries.add(ModItems.DRONE_ITEM);
                entries.add(ModItems.BATTERY_ITEM);
                entries.add(ModItems.ANTENNA_ITEM);
                entries.add(ModItems.CORE_ITEM);

                entries.add(ModItems.SPRAY_PAINT_BLACK_ITEM);
                entries.add(ModItems.SPRAY_PAINT_BLUE_ITEM);
                entries.add(ModItems.SPRAY_PAINT_BROWN_ITEM);
                entries.add(ModItems.SPRAY_PAINT_CYAN_ITEM);
                entries.add(ModItems.SPRAY_PAINT_GRAY_ITEM);
                entries.add(ModItems.SPRAY_PAINT_GREEN_ITEM);
                entries.add(ModItems.SPRAY_PAINT_LIGHT_BLUE_ITEM);
                entries.add(ModItems.SPRAY_PAINT_LIGHT_GRAY_ITEM);
                entries.add(ModItems.SPRAY_PAINT_LIME_ITEM);
                entries.add(ModItems.SPRAY_PAINT_MAGENTA_ITEM);
                entries.add(ModItems.SPRAY_PAINT_ORANGE_ITEM);
                entries.add(ModItems.SPRAY_PAINT_PINK_ITEM);
                entries.add(ModItems.SPRAY_PAINT_PURPLE_ITEM);
                entries.add(ModItems.SPRAY_PAINT_RED_ITEM);
                entries.add(ModItems.SPRAY_PAINT_WHITE_ITEM);
                entries.add(ModItems.SPRAY_PAINT_YELLOW_ITEM);
            })
            .build()
    );

    public static void init() {
        System.out.println("[Droneworks] ModItemGroups initialisés");
    }
}

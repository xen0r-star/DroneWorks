package io.github.xen0r_star.droneworks.client.renderer;

import io.github.xen0r_star.droneworks.registry.ModItems;
import net.minecraft.item.Item;

public enum DRONE_COLOR {
    DEFAULT(0, null),
    WHITE(1, ModItems.SPRAY_PAINT_WHITE_ITEM),
    LIGHT_GRAY(2, ModItems.SPRAY_PAINT_LIGHT_GRAY_ITEM),
    GRAY(3, ModItems.SPRAY_PAINT_GRAY_ITEM),
    BLACK(4, ModItems.SPRAY_PAINT_BLACK_ITEM),
    BROWN(5, ModItems.SPRAY_PAINT_BROWN_ITEM),
    RED(6, ModItems.SPRAY_PAINT_RED_ITEM),
    ORANGE(7, ModItems.SPRAY_PAINT_ORANGE_ITEM),
    YELLOW(8, ModItems.SPRAY_PAINT_YELLOW_ITEM),
    LIME(9, ModItems.SPRAY_PAINT_LIME_ITEM),
    GREEN(10, ModItems.SPRAY_PAINT_GREEN_ITEM),
    CYAN(11, ModItems.SPRAY_PAINT_CYAN_ITEM),
    LIGHT_BLUE(12, ModItems.SPRAY_PAINT_LIGHT_BLUE_ITEM),
    BLUE(13, ModItems.SPRAY_PAINT_BLUE_ITEM),
    PURPLE(14, ModItems.SPRAY_PAINT_PURPLE_ITEM),
    MAGENTA(15, ModItems.SPRAY_PAINT_MAGENTA_ITEM),
    PINK(16, ModItems.SPRAY_PAINT_PINK_ITEM);

    private final int value;
    private final Item item;

    DRONE_COLOR(int value, Item item) {
        this.value = value;
        this.item = item;
    }

    public int getValue() {
        return value;
    }

    public Item getItem() {
        return item;
    }

    public static DRONE_COLOR fromValue(int value) {
        for (DRONE_COLOR color : values()) {
            if (color.value == value) {
                return color;
            }
        }
        return null;
    }

    public static DRONE_COLOR fromItem(Item item) {
        for (DRONE_COLOR color : values()) {
            if (color.item == item) {
                return color;
            }
        }
        return null;
    }
}

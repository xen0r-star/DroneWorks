package io.github.xen0r_star.droneworks.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

public final class ModItems {
    public static final Item BOX_ITEM = Items.register(
        ModBlocks.BOX_BLOCK,
        new Item.Settings()
    );

    private ModItems() {}

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
            .register(entries -> entries.add(BOX_ITEM));

        System.out.println("[Droneworks] ModItems initialisés");
    }
}
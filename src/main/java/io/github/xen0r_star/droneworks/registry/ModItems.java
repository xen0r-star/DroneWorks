package io.github.xen0r_star.droneworks.registry;

import io.github.xen0r_star.droneworks.Main;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;

public final class ModItems {
    // Déclaration de ton BlockItem
    public static final Item BOX_ITEM = Items.register(
            ModBlocks.BOX_BLOCK,
            new Item.Settings()
    );

    private ModItems() {}

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(entries -> entries.add(BOX_ITEM));

        System.out.println("ModItems initialisés");
    }
}

package io.github.xen0r_star.droneworks.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Item> SPRAY_PAINTS =
        TagKey.of(
            Registries.ITEM.getKey(),
            Identifier.of("droneworks", "spray_paints")
        );
}

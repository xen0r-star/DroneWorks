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
                        entries.add(ModItems.DRONE_ITEM);
                    })
                    .build()
    );

    public static void init() {
        System.out.println("[Droneworks] ModItemGroups initialisés");
    }
}

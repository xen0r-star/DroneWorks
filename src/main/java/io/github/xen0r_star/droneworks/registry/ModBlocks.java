package io.github.xen0r_star.droneworks.registry;

import io.github.xen0r_star.droneworks.Main;
import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public final class ModBlocks {
    public static final Block BOX_BLOCK = register(
            "box_block",
            Block::new,
            Block.Settings.create().strength(1.5f).requiresTool()
    );

    private ModBlocks() {}

    private static Block register(String name, java.util.function.Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Identifier id = Identifier.of(Main.MOD_ID, name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);

        return Blocks.register(key, factory, settings);
    }

    public static void init() {
        System.out.println("[Droneworks] ModBlocks initialisés");
    }
}

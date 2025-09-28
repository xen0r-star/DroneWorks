package io.github.xen0r_star.droneworks;

import io.github.xen0r_star.droneworks.registry.*;
import net.fabricmc.api.ModInitializer;


public class Main implements ModInitializer {
    public static final String MOD_ID = "droneworks";


    @Override
    public void onInitialize() {
        ModBlocks.init();
        ModBlockEntities.register();
        ModScreenHandlers.init();
        ModItems.init();
        ModItemGroups.init();
        ModEntities.init();
        ModCriteria.init();

        System.out.println("[Droneworks] Mod initialisé");
    }
}


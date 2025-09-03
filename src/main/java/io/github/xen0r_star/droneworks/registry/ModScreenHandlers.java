//package io.github.xen0r_star.droneworks.registry;
//
//import io.github.xen0r_star.droneworks.Main;
//import io.github.xen0r_star.droneworks.screen.BoxScreenHandler;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.registry.Registry;
//import net.minecraft.registry.Registries;
//import net.minecraft.screen.ScreenHandlerType;
//import net.minecraft.util.Identifier;
//
//public class ModScreenHandlers {
//    public static ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER;
//
//    public static void register() {
//        BOX_SCREEN_HANDLER = Registry.register(
//                Registries.SCREEN_HANDLER,
//                Identifier.of(Main.MOD_ID, "box"),
//                new ScreenHandlerType<>((syncId, inv) -> new BoxScreenHandler(syncId, inv))
//        );
//    }
//}

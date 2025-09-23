package io.github.xen0r_star.droneworks.registry;
import io.github.xen0r_star.droneworks.Main;
import io.github.xen0r_star.droneworks.screen.BoxScreenHandler;
import io.github.xen0r_star.droneworks.screen.StationScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;


public final class ModScreenHandlers {
    public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER =
        Registry.register(
            Registries.SCREEN_HANDLER,
            Identifier.of(Main.MOD_ID, "box"),
            new ScreenHandlerType<>(BoxScreenHandler::new, FeatureSet.empty())
        );

    public static final ScreenHandlerType<StationScreenHandler> STATION_SCREEN_HANDLER =
        Registry.register(
            Registries.SCREEN_HANDLER,
            Identifier.of(Main.MOD_ID, "station"),
            new ScreenHandlerType<>(StationScreenHandler::new, FeatureSet.empty())
        );


    private ModScreenHandlers() { }

    public static void init() { }
}
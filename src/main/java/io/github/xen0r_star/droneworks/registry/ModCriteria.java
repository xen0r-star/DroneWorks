package io.github.xen0r_star.droneworks.registry;

import io.github.xen0r_star.droneworks.advancements.DroneLinkedCriterion;
import net.minecraft.advancement.criterion.Criteria;

public class ModCriteria {
    public static final DroneLinkedCriterion DRONE_LINKED = Criteria.register("droneworks:drone_linked_to_station",new DroneLinkedCriterion());

    public static void init() {
        System.out.println("[Droneworks] ModCriteria initialisés");
    }
}

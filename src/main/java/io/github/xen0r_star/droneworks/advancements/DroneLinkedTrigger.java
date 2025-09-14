//package io.github.xen0r_star.droneworks.advancements;
//
//import com.google.gson.JsonObject;
//import net.minecraft.advancement.criterion.*;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.predicate.entity.EntityPredicate;
//import net.minecraft.server.network.ServerPlayerEntity;
//import net.minecraft.util.Identifier;
//
//public class DroneLinkedTrigger extends SimpleCriterionTrigger<DroneLinkedTrigger.Instance> {
//    private static final Identifier ID = Identifier.of("droneworks", "drone_linked_to_station");
//
//    @Override
//    public Identifier getId() {
//        return ID;
//    }
//
//    @Override
//    protected Instance fromJson(JsonObject json, EntityPredicate.Extended playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
//        return new Instance(playerPredicate);
//    }
//
//    public void trigger(ServerPlayerEntity player) {
//        this.test(player, instance -> true); // déclenche l’avancement pour ce joueur
//    }
//
//    public static class Instance extends AbstractCriterionInstance {
//        public Instance(EntityPredicate.Extended playerPredicate) {
//            super(DroneLinkedTrigger.ID, playerPredicate);
//        }
//    }
//}

//package io.github.xen0r_star.droneworks.advancements;
//
//import com.google.gson.JsonObject;
//import com.mojang.serialization.Codec;
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import net.minecraft.advancement.criterion.*;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.predicate.entity.EntityPredicate;
//import net.minecraft.server.network.ServerPlayerEntity;
//import net.minecraft.util.Identifier;
//
//public class DroneLinkedTrigger extends SimpleCriterionTrigger<DroneLinkedTrigger.Instance> {
//
//    private static final Identifier ID =
//            new Identifier("droneworks", "drone_linked_to_station");
//
//    @Override
//    public Identifier getId() {
//        return ID;
//    }
//
//    @Override
//    public Codec<Instance> codec() {
//        return Instance.CODEC;
//    }
//
//    /** Appel depuis votre code quand le joueur lie un drone à la station */
//    public void trigger(ServerPlayerEntity player) {
//        // Ici, on ne teste rien de plus – on valide le critère pour ce joueur
//        super.trigger(player, inst -> true);
//    }
//    public static record Instance(Optional<ContextAwarePredicate> player)
//            implements SimpleCriterionTrigger.SimpleInstance {
//        public static final Codec<Instance> CODEC =
//                RecordCodecBuilder.create(inst ->
//                        inst.group(
//                                EntityPredicate.ADVANCEMENT_CODEC
//                                        .optionalFieldOf("player")
//                                        .forGetter(Instance::player)
//                        ).apply(inst, Instance::new)
//                );
//    }
//}
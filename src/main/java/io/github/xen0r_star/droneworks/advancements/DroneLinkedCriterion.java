package io.github.xen0r_star.droneworks.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;

import io.github.xen0r_star.droneworks.registry.ModCriteria;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.entity.Entity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.entity.LootContextPredicateValidator;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class DroneLinkedCriterion extends AbstractCriterion<DroneLinkedCriterion.Conditions> {
    public static final Identifier ID = Identifier.of("droneworks", "drone_linked_to_station");

    @Override
    public Codec<Conditions> getConditionsCodec() {
        return Conditions.CODEC;
    }


    public void trigger(ServerPlayerEntity player, @Nullable Entity drone) {
        LootContext playerContext = EntityPredicate.createAdvancementEntityLootContext(player, player);
        LootContext droneContext = drone != null ? EntityPredicate.createAdvancementEntityLootContext(player, drone) : null;

        this.trigger(player, (conditions) -> conditions.matches(playerContext, droneContext));
    }


    public record Conditions(Optional<LootContextPredicate> player, Optional<LootContextPredicate> drone) implements AbstractCriterion.Conditions {
        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(Conditions::player),
                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("drone").forGetter(Conditions::drone)
        ).apply(instance, Conditions::new));

        public static AdvancementCriterion<Conditions> any() {
            return ModCriteria.DRONE_LINKED.create(new Conditions(Optional.empty(), Optional.empty()));
        }

        public boolean matches(LootContext playerContext, @Nullable LootContext droneContext) {
            if (player.isPresent() && !player.get().test(playerContext)) return false;
            return drone.isEmpty() || (droneContext != null && drone.get().test(droneContext));
        }

        @Override
        public void validate(LootContextPredicateValidator validator) {
            validator.validateEntityPredicate(this.player, "player");
            validator.validateEntityPredicate(this.drone, "drone");
        }
    }
}

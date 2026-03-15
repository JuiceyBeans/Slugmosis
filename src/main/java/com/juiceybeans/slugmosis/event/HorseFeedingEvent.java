package com.juiceybeans.slugmosis.event;

import com.juiceybeans.slugmosis.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

public class HorseFeedingEvent {
    private static final UUID HORSE_SLUGMO_MODIFIER_UUID = UUID.fromString("57CA8086-4438-4426-A56B-25376144A04D");
    private static final SimpleWeightedRandomList<Object> weightedProbability = new SimpleWeightedRandomList.Builder<>()
            .add(1.0, 4)
            .add(2.0, 3)
            .add(3.0, 2)
            .add(4.0, 1)
            .build();

    @SubscribeEvent
    public static void onHorseFed(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof AbstractHorse horse) {
            var item = event.getItemStack();

            if (item.is(ModItems.SHIMMERING_SLUGMO_BEANS.get())) {

                var level = event.getLevel();
                var rand = level.getRandom();
                var i = weightedProbability.getRandomValue(rand).orElse(9);

                if (!horse.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED)) return;

                var speed = horse.getAttribute(Attributes.MOVEMENT_SPEED);
                var speedModifier = new AttributeModifier(HORSE_SLUGMO_MODIFIER_UUID, "Slugmo Bean bonus",
                        (double) i /10, AttributeModifier.Operation.MULTIPLY_BASE);

                if (!speed.hasModifier(speedModifier)) {
                    speed.addPermanentModifier(speedModifier);

                    for (int x = 0; x < 7; ++x) {
                        double d0 = rand.nextGaussian() * 0.02D;
                        double d1 = rand.nextGaussian() * 0.02D;
                        double d2 = rand.nextGaussian() * 0.02D;
                        level.addParticle(
                                ParticleTypes.HAPPY_VILLAGER,
                                horse.getRandomX(1.0D), horse.getRandomY() + 0.5D, horse.getRandomZ(1.0D),
                                d0, d1, d2
                        );
                    }

                    horse.eat(level, item);

                    var result = level.isClientSide() ? InteractionResult.CONSUME : InteractionResult.SUCCESS;
                    event.setCancellationResult(result);
                }
           }
        }
    }
}

package com.juiceybeans.slugmo.mixin;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import snownee.jade.addon.vanilla.HorseStatsProvider;

@Mixin(HorseStatsProvider.class)
public abstract class JadeHorseMixin {
    // todo remove this if https://github.com/Snownee/Jade/pull/670 is merged
    @Redirect(
            method = "appendTooltip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/horse/AbstractHorse;getAttributeBaseValue(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D",
                    ordinal = 0
            ))
    private double jumpStrengthValue(AbstractHorse horse, Attribute attribute) {
        return horse.getAttributeValue(Attributes.JUMP_STRENGTH);
    }

    @Redirect(
            method = "appendTooltip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/horse/AbstractHorse;getAttributeBaseValue(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D",
                    ordinal = 1
            ))
    private double movementSpeedValue(AbstractHorse horse, Attribute attribute) {
        return horse.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }
}

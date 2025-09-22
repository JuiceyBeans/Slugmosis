package com.juiceybeans.slugmo.capability;

import com.juiceybeans.slugmo.Slugmo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModCapabilities {
    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(PlayerRageAttacher.RageCapabilityProvider.IDENTIFIER, new PlayerRageAttacher.RageCapabilityProvider());
            Slugmo.LOGGER.debug("Attached capability to player {}", event.getObject());
        }
    }
}

package com.juiceybeans.slugmo.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class PlayerRage {
    public static final Capability<IPlayerRage> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    private PlayerRage() {
    }
}

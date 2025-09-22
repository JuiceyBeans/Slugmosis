package com.juiceybeans.slugmo.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface IPlayerRage extends INBTSerializable<CompoundTag> {

    int getValue();

    void setRage(int rage);
}
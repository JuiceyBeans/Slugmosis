package com.juiceybeans.slugmo.capability;

import net.minecraft.nbt.CompoundTag;

public class PlayerRageImpl implements IPlayerRage {
    public static final String NBT_KEY_RAGE = "rage";

    private int rage;

    @Override
    public int getValue() {
        return this.rage;
    }

    @Override
    public void setRage(int rage) {
        this.rage = rage;
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putDouble(NBT_KEY_RAGE, this.rage);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.rage = nbt.getInt(NBT_KEY_RAGE);
    }
}

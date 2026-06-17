package com.juiceybeans.slugmosis.integration.jade;

import com.juiceybeans.slugmosis.Slugmosis;
import com.juiceybeans.slugmosis.block.LightningAgitatorBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum LightningAgitatorComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if (blockAccessor.getBlockState().getValue(LightningAgitatorBlock.SHORT_CIRCUITED)) {
            tooltip.add(Component.translatable("slugmosis.jade.lightning_agitator.short_circuited"));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return Slugmosis.id("lightning_agitator_info");
    }
}

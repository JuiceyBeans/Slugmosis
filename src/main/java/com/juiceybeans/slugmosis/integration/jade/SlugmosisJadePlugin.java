package com.juiceybeans.slugmosis.integration.jade;

import com.juiceybeans.slugmosis.block.LightningAgitatorBlock;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class SlugmosisJadePlugin implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(LightningAgitatorComponentProvider.INSTANCE, LightningAgitatorBlock.class);
    }
}

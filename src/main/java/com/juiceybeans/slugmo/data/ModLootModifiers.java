package com.juiceybeans.slugmo.data;

import com.juiceybeans.slugmo.Slugmo;
import com.juiceybeans.slugmo.data.loot_modifiers.InjectItemModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Slugmo.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> INJECT_ITEM =
            LOOT_MODIFIER_SERIALIZER.register("inject_item", InjectItemModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZER.register(eventBus);
    }
}

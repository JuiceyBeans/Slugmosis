package com.juiceybeans.slugmo;

import com.juiceybeans.slugmo.block.ModBlocks;
import com.juiceybeans.slugmo.data.loot_modifiers.InjectItemModifier;
import com.juiceybeans.slugmo.data.loot_modifiers.ModLootModifiers;
import com.juiceybeans.slugmo.event.HorseFeedingEvent;
import com.juiceybeans.slugmo.item.ModItems;
import com.juiceybeans.slugmo.tab.ModTabs;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;

@Mod(Slugmo.MOD_ID)
public class Slugmo {

    public static final String MOD_ID = "slugmo";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public Slugmo() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTabs.register(eventBus);
        ModLootModifiers.register(eventBus);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(HorseFeedingEvent.class);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }

    /**
     * @return if the FML environment is a client
     */
    public static boolean isClientSide() {
        return FMLEnvironment.dist.isClient();
    }

    @SubscribeEvent
    public static void registerModifiers(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                codecRegisterHelper -> codecRegisterHelper.register(Slugmo.id("sniffer_digging"),
                        InjectItemModifier.DIRECT_CODEC));
    }
}

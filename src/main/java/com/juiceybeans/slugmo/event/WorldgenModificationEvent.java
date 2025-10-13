package com.juiceybeans.slugmo.event;

import com.juiceybeans.slugmo.block.GlowshroomStemBlock;
import com.juiceybeans.slugmo.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class WorldgenModificationEvent {

    /**
     * Datapack registries are loaded by this point
     * So we can inject our custom worldgen stuff here to maintain compat with vanilla and other mods
     */
    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        var confFeatureReg = event.getServer().registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE);

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("minecraft", "crimson_forest_vegetation");
        ConfiguredFeature<?, ?> feature = confFeatureReg.get(id);

        // Inject into configured features list
        if (feature.config() instanceof BlockPileConfiguration config) {
            if (config.stateProvider instanceof WeightedStateProvider weightedProvider) {
                List<WeightedEntry.Wrapper<BlockState>> blocks = new ArrayList<>(weightedProvider.weightedList.unwrap());

                WeightedEntry.Wrapper<BlockState> glowshroomAge0 = WeightedEntry.wrap(ModBlocks.GLOWSHROOM_STEM.get().defaultBlockState().setValue(GlowshroomStemBlock.AGE, 0), 2);
                WeightedEntry.Wrapper<BlockState> glowshroomAge1 = WeightedEntry.wrap(ModBlocks.GLOWSHROOM_STEM.get().defaultBlockState().setValue(GlowshroomStemBlock.AGE, 1), 3);
                WeightedEntry.Wrapper<BlockState> glowshroomAge2 = WeightedEntry.wrap(ModBlocks.GLOWSHROOM_STEM.get().defaultBlockState().setValue(GlowshroomStemBlock.AGE, 2), 2);
                WeightedEntry.Wrapper<BlockState> glowshroomAge3 = WeightedEntry.wrap(ModBlocks.GLOWSHROOM_STEM.get().defaultBlockState().setValue(GlowshroomStemBlock.AGE, 3), 1);

                blocks.add(glowshroomAge0);
                blocks.add(glowshroomAge1);
                blocks.add(glowshroomAge2);
                blocks.add(glowshroomAge3);

                weightedProvider.weightedList = new SimpleWeightedRandomList<>(blocks);
            }
        }
    }
}

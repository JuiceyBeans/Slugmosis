package com.juiceybeans.slugmo.data.datagen;

import com.juiceybeans.slugmo.Slugmo;
import com.juiceybeans.slugmo.block.ModBlocks;
import com.juiceybeans.slugmo.block.SlugmoBeanBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Slugmo.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.stageBlock(ModBlocks.SLUGMO_BEANS.get(), SlugmoBeanBlock.AGE);
    }

    private String getBlockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    // Shoutout Farmer's Delight for this method
    public void stageBlock(Block block, IntegerProperty ageProperty, Property<?>... ignored) {
        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    int ageSuffix = state.getValue(ageProperty);
                    String stageName = getBlockName(block) + "_stage" + ageSuffix;
                    return ConfiguredModel.builder().modelFile(models()
                            .crop(stageName, Slugmo.id("block/" + stageName))
                            .renderType("cutout"))
                            .build();
                }, ignored);
    }
}

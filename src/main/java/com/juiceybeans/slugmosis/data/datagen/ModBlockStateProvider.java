package com.juiceybeans.slugmosis.data.datagen;

import com.juiceybeans.slugmosis.Slugmosis;
import com.juiceybeans.slugmosis.block.GlowshroomStemBlock;
import com.juiceybeans.slugmosis.block.LightningAgitatorBlock;
import com.juiceybeans.slugmosis.block.ModBlocks;
import com.juiceybeans.slugmosis.block.SlugmoBeanBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Slugmosis.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.squareStageBlock(ModBlocks.SLUGMO_BEANS.get(), SlugmoBeanBlock.AGE);
        this.crossStageBlock(ModBlocks.GLOWSHROOM_STEM.get(), GlowshroomStemBlock.AGE);
        this.createLightningAgitatorBlock(ModBlocks.LIGHTNING_AGITATOR.get(),
                LightningAgitatorBlock.POWERED, LightningAgitatorBlock.SHORT_CIRCUITED);
    }

    private String getBlockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    // Shoutout Farmer's Delight for this method
    public void squareStageBlock(Block block, IntegerProperty ageProperty, Property<?>... ignored) {
        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    int ageSuffix = state.getValue(ageProperty);
                    String stageName = getBlockName(block) + "_stage" + ageSuffix;
                    return ConfiguredModel.builder().modelFile(models()
                                    .crop(stageName, Slugmosis.id("block/" + stageName))
                                    .renderType("cutout"))
                            .build();
                }, ignored);
    }

    public void crossStageBlock(Block block, IntegerProperty ageProperty, Property<?>... ignored) {
        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    int ageSuffix = state.getValue(ageProperty);
                    String stageName = getBlockName(block) + "_stage" + ageSuffix;
                    return ConfiguredModel.builder().modelFile(models()
                                    .cross(stageName, Slugmosis.id("block/" + stageName))
                                    .renderType("cutout"))
                            .build();
                }, ignored);
    }

    public void createLightningAgitatorBlock(Block block, BooleanProperty powered, BooleanProperty shortCircuited) {
        this.getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    String poweredSuffix = state.getValue(powered) ? "_powered" : "";
                    String topTexureName = getBlockName(block) + "_top" + poweredSuffix;

                    return ConfiguredModel.builder().modelFile(models()
                                    .cubeTop("lightning_agitator",
                                            TextureMapping.getBlockTexture(Blocks.FURNACE).withSuffix("_side"),
                                            Slugmosis.id("block/" + topTexureName)))
                            .build();
                }, shortCircuited);

        simpleBlockItem(block, models().getExistingFile(Slugmosis.id("block/lightning_agitator")));
    }
}

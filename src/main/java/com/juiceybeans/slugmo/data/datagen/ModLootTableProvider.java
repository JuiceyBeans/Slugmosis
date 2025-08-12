package com.juiceybeans.slugmo.data.datagen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.juiceybeans.slugmo.Slugmo;
import com.juiceybeans.slugmo.block.ModBlocks;
import com.juiceybeans.slugmo.block.SlugmoBeanBlock;
import com.juiceybeans.slugmo.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.stream.Collectors;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput pOutput) {
        super(pOutput, Collections.emptySet(),
                ImmutableList.of(new LootTableProvider.SubProviderEntry(BlockProvider::new,
                        LootContextParamSets.BLOCK)));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {}

    private static class BlockProvider extends BlockLootSubProvider
    {
        protected BlockProvider()
        {
            super(ImmutableSet.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate()
        {
            LootItemCondition.Builder slugmoBeansLootItemCondition = LootItemBlockStatePropertyCondition
                    .hasBlockStateProperties(ModBlocks.SLUGMO_BEANS.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties()
                            .hasProperty(SlugmoBeanBlock.AGE, 3));

            this.add(ModBlocks.SLUGMO_BEANS.get(), this.applyExplosionDecay(ModBlocks.SLUGMO_BEANS.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.SLUGMO_BEANS.get())))
                    .withPool(LootPool.lootPool().when(slugmoBeansLootItemCondition)
                            .add(LootItem.lootTableItem(ModItems.SLUGMO_BEANS.get())
                                    .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE,
                                            0.5714286F, 3))))
                    .withPool(LootPool.lootPool().when(slugmoBeansLootItemCondition)
                            .add(LootItem.lootTableItem(ModItems.SHIMMERING_SLUGMO_BEANS.get())
                                    .when(LootItemRandomChanceCondition.randomChance(0.01F))))));

        }

        @Override
        protected Iterable<Block> getKnownBlocks()
        {
            return ForgeRegistries.BLOCKS.getValues().stream().filter(block ->
                    Slugmo.MOD_ID.equals(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getNamespace()))
                    .collect(Collectors.toSet());
        }
    }
}

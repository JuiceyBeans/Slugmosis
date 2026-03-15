package com.juiceybeans.slugmosis.data.datagen;

import com.juiceybeans.slugmosis.block.ModBlocks;
import com.juiceybeans.slugmosis.item.ModItems;
import com.juiceybeans.slugmosis.util.IngredientUtils;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> provider) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VESPERTINE_SWORD.get())
                .pattern("V")
                .pattern("V")
                .pattern("s")
                .define('V', ModItems.VESPERTINE.get())
                .define('s', Items.STICK)
                .unlockedBy(getHasName(ModItems.VESPERTINE.get()), has(ModItems.VESPERTINE.get()))
                .save(provider);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.LIGHTNING_AGITATOR.get())
                .pattern("CRC")
                .pattern("CTC")
                .pattern("SSS")
                .define('C', Tags.Items.COBBLESTONE)
                .define('R', Blocks.LIGHTNING_ROD)
                .define('T', IngredientUtils.enchantedIngredient(Items.TRIDENT, List.of(Pair.of("minecraft:channeling", 1))))
                .define('S', Blocks.SMOOTH_STONE)
                .unlockedBy(getHasName(Blocks.LIGHTNING_ROD), has(Blocks.LIGHTNING_ROD))
                .save(provider);
    }
}

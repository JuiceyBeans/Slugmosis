package com.juiceybeans.slugmo.data.datagen;

import com.juiceybeans.slugmo.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

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
            }
}

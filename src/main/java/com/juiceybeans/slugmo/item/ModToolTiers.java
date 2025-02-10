package com.juiceybeans.slugmo.item;

import com.juiceybeans.slugmo.Slugmo;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

import static net.minecraftforge.common.Tags.Blocks.NEEDS_NETHERITE_TOOL;

public class ModToolTiers {
    public static final Tier VESPERTINE = TierSortingRegistry.registerTier(
            new ForgeTier(4, 2671, 1.5F, 4.0F, 25,
                    NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.VESPERTINE.get())),
            Slugmo.id("vespertine"), List.of(Tiers.DIAMOND), List.of());
}

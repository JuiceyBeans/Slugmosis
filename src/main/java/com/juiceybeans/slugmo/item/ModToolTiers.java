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
    // Vespertine - Lower durability, higher enchantability
    public static final Tier VESPERTINE = TierSortingRegistry.registerTier(
            new ForgeTier(4, 1561, 8.0F, 4.0F, 22,
                    NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.VESPERTINE.get())),
            Slugmo.id("vespertine"), List.of(Tiers.DIAMOND), List.of());
}

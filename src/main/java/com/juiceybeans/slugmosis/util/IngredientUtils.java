package com.juiceybeans.slugmosis.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.crafting.PartialNBTIngredient;

import java.util.List;

public class IngredientUtils {
    public static PartialNBTIngredient enchantedIngredient(Item item, List<Pair<String, Integer>> enchants) {
        return PartialNBTIngredient.of(item, createEnchantments(enchants));
    }

    public static CompoundTag createEnchantments(List<Pair<String, Integer>> enchants) {
        CompoundTag nbt = new CompoundTag();
        ListTag enchantments = new ListTag();

        for (Pair<String, Integer> pair : enchants) {
            CompoundTag enchant = new CompoundTag();
            enchant.putString("id", pair.getFirst());
            enchant.putInt("lvl", pair.getSecond());

            enchantments.add(enchant);
            nbt.put("Enchantments", enchantments);
        }

        return nbt;
    }
}

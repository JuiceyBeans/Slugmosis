package com.juiceybeans.slugmo.data.datagen;

import com.juiceybeans.slugmo.Slugmo;
import com.juiceybeans.slugmo.data.loot_modifiers.InjectItemModifier;
import com.juiceybeans.slugmo.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGLMProvider extends GlobalLootModifierProvider {
    public ModGLMProvider(PackOutput output) {
        super(output, Slugmo.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("slugmo_beans_from_sniffer_digging", new InjectItemModifier(
                new LootItemCondition[] {
                        new LootTableIdCondition.Builder(
                                new ResourceLocation("gameplay/sniffer_digging")
                        ).build()
                },
                ModItems.SLUGMO_BEANS.get()
        ));
    }
}
    
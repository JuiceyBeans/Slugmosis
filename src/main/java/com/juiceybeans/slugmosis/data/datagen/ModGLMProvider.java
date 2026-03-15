package com.juiceybeans.slugmosis.data.datagen;

import com.juiceybeans.slugmosis.Slugmosis;
import com.juiceybeans.slugmosis.data.loot_modifiers.InjectItemModifier;
import com.juiceybeans.slugmosis.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGLMProvider extends GlobalLootModifierProvider {
    public ModGLMProvider(PackOutput output) {
        super(output, Slugmosis.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("slugmo_beans_from_sniffer_digging", new InjectItemModifier(
                new LootItemCondition[] {
                        new LootTableIdCondition.Builder(
                                ResourceLocation.parse("gameplay/sniffer_digging")
                        ).build()
                },
                ModItems.SLUGMO_BEANS.get()
        ));
    }
}
    
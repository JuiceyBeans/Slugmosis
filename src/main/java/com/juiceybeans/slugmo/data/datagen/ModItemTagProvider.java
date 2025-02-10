package com.juiceybeans.slugmo.data.datagen;

import com.juiceybeans.slugmo.Slugmo;
import com.juiceybeans.slugmo.data.ModTags;
import com.juiceybeans.slugmo.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pParentProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pParentProvider, Slugmo.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.TEST_ITEM_TAG)
                .add(ModItems.VESPERTINE.get());
    }
}

package com.juiceybeans.slugmo.data.datagen;

import com.juiceybeans.slugmo.Slugmo;
import com.juiceybeans.slugmo.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Slugmo.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.VESPERTINE);
        simpleItem(ModItems.VESPERTINE_AXE);
        simpleItem(ModItems.VESPERTINE_HOE);
        simpleItem(ModItems.VESPERTINE_PICKAXE);
        simpleItem(ModItems.VESPERTINE_SHOVEL);
        simpleItem(ModItems.VESPERTINE_SWORD);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> itemRegistryObject) {
        return withExistingParent(itemRegistryObject.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Slugmo.MOD_ID, "item/" + itemRegistryObject.getId().getPath()));
    }
}

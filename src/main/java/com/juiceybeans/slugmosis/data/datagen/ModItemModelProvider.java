package com.juiceybeans.slugmosis.data.datagen;

import com.juiceybeans.slugmosis.Slugmosis;
import com.juiceybeans.slugmosis.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Slugmosis.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(ModItems.SKYREAVER);
        handheldItem(ModItems.VESPERTINE_AXE);
        handheldItem(ModItems.VESPERTINE_HOE);
        handheldItem(ModItems.VESPERTINE_PICKAXE);
        handheldItem(ModItems.VESPERTINE_SHOVEL);
        handheldItem(ModItems.VESPERTINE_SWORD);
        simpleItem(ModItems.VESPERTINE);
        simpleItem(ModItems.VESPERTINE_ARMOR_HELMET);
        simpleItem(ModItems.VESPERTINE_ARMOR_CHESTPLATE);
        simpleItem(ModItems.VESPERTINE_ARMOR_LEGGINGS);
        simpleItem(ModItems.VESPERTINE_ARMOR_BOOTS);
        simpleItem(ModItems.SLUGMO_BEANS);
        simpleItem(ModItems.SHIMMERING_SLUGMO_BEANS);
        simpleItem(ModItems.GLOWSHROOM_STEM);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> itemRegistryObject) {
        return withExistingParent(itemRegistryObject.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                Slugmosis.id("item/" + itemRegistryObject.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> itemRegistryObject) {
        return withExistingParent(itemRegistryObject.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                Slugmosis.id("item/" + itemRegistryObject.getId().getPath()));
    }
}

package com.juiceybeans.slugmo.item;

import com.juiceybeans.slugmo.Slugmo;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Slugmo.MOD_ID);

    // Materials
    public static final RegistryObject<Item> VESPERTINE = ITEMS.register("vespertine", () -> new Item(
            new Item.Properties().rarity(Rarity.UNCOMMON)));

    // Weapons
    public static final RegistryObject<Item> SKYREAVER = ITEMS.register("skyreaver",
            SkyreaverItem::new);

    // Vespertine
    public static final RegistryObject<Item> VESPERTINE_SWORD = ITEMS.register("vespertine_sword",
            () -> new SwordItem(ModToolTiers.VESPERTINE, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> VESPERTINE_AXE = ITEMS.register("vespertine_axe",
            () -> new AxeItem(ModToolTiers.VESPERTINE, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> VESPERTINE_PICKAXE = ITEMS.register("vespertine_pickaxe",
            () -> new PickaxeItem(ModToolTiers.VESPERTINE, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> VESPERTINE_SHOVEL = ITEMS.register("vespertine_shovel",
            () -> new ShovelItem(ModToolTiers.VESPERTINE, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> VESPERTINE_HOE = ITEMS.register("vespertine_hoe",
            () -> new HoeItem(ModToolTiers.VESPERTINE, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> VESPERTINE_ARMOR_HELMET = ITEMS.register("vespertine_helmet",
            () -> new VespertineArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> VESPERTINE_ARMOR_CHESTPLATE = ITEMS.register("vespertine_chestplate",
            () -> new VespertineArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> VESPERTINE_ARMOR_LEGGINGS = ITEMS.register("vespertine_leggings",
            () -> new VespertineArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> VESPERTINE_ARMOR_BOOTS = ITEMS.register("vespertine_boots",
            () -> new VespertineArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}


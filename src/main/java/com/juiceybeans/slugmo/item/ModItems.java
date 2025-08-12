package com.juiceybeans.slugmo.item;

import com.juiceybeans.slugmo.Slugmo;
import com.juiceybeans.slugmo.block.ModBlocks;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Slugmo.MOD_ID);

    // Items
    public static final RegistryObject<Item> SLUGMO_BEANS = ITEMS.register("slugmo_beans", () -> new ItemNameBlockItem(
            ModBlocks.SLUGMO_BEANS.get(), new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationMod(0.5f)
                            .build())));

    public static final RegistryObject<Item> SHIMMERING_SLUGMO_BEANS = ITEMS.register("shimmering_slugmo_beans", () -> new Item(
            new Item.Properties().rarity(Rarity.UNCOMMON).food(
                    new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(1f)
                            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60), 1)
                            .build())
    ));

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


package com.juiceybeans.slugmo.item;

import com.juiceybeans.slugmo.Slugmo;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Slugmo.MOD_ID);


    public static final RegistryObject<Item> VESPERTINE = ITEMS.register("vespertine", () -> new Item(
            new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> VESPERTINE_SWORD = ITEMS.register("vespertine_sword",
            SkyreaverItem::new);
    public static final RegistryObject<Item> VESPERTINE_AXE = ITEMS.register("vespertine_axe",
            () -> new SwordItem(ModToolTiers.VESPERTINE, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> VESPERTINE_PICKAXE = ITEMS.register("vespertine_pickaxe",
            () -> new SwordItem(ModToolTiers.VESPERTINE, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> VESPERTINE_SHOVEL = ITEMS.register("vespertine_shovel",
            () -> new SwordItem(ModToolTiers.VESPERTINE, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> VESPERTINE_HOE = ITEMS.register("vespertine_hoe",
            () -> new SwordItem(ModToolTiers.VESPERTINE, 4, 2, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}


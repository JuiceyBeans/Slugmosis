package com.juiceybeans.slugmo.item;

import com.juiceybeans.slugmo.Slugmo;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.juiceybeans.slugmo.Slugmo.SLUGMO_REGISTRATE;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Slugmo.MOD_ID);

    public static final ItemEntry<Item> VESPERTINE = SLUGMO_REGISTRATE.item("vespertine", Item::new)
            .lang("Vespertine")
            .tab(CreativeModeTabs.INGREDIENTS)
            .register();
    public static final ItemEntry<Item> TEST_SWORD = SLUGMO_REGISTRATE.item("test_sword", Item::new)
            .lang("Test Sword")
            .tab(CreativeModeTabs.COMBAT)
            .register();


    public static final RegistryObject<Item> VESPERTINE_SWORD = ITEMS.register("vespertine_sword",
            () -> new SwordItem(ModToolTiers.VESPERTINE, 4, 2, new Item.Properties()));
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


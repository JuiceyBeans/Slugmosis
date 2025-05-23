package com.juiceybeans.slugmo.tab;

import com.juiceybeans.slugmo.Slugmo;
import com.juiceybeans.slugmo.block.ModBlocks;
import com.juiceybeans.slugmo.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Slugmo.MOD_ID);

    public static final Supplier<CreativeModeTab> SLUGMO_TAB = TABS.register("slugmo_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + Slugmo.MOD_ID + ".slugmo_tab"))
            .icon(() -> new ItemStack(ModItems.VESPERTINE.get()))
            .displayItems((params, output) -> {
                addItems(output);
                addBlocks(output);
            })
            .build()
    );

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }

    private static void addItems(CreativeModeTab.Output tabOutput) {
        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
            tabOutput.accept(item.get());
        }
    }

    private static void addBlocks(CreativeModeTab.Output tabOutput) {
        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()){
            tabOutput.accept(block.get());
        }
    }
}

package com.juiceybeans.slugmosis.block;

import com.juiceybeans.slugmosis.Slugmosis;
import com.juiceybeans.slugmosis.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Slugmosis.MOD_ID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static final RegistryObject<Block> GLOWSHROOM_STEM = BLOCKS.register("glowshroom_stem", () ->
            new GlowshroomStemBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.CROP)
                            .pushReaction(PushReaction.DESTROY)));
  
    public static final RegistryObject<Block> SLUGMO_BEANS = BLOCKS.register("slugmo_beans", () ->
            new SlugmoBeanBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.CROP)
                            .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> LIGHTNING_AGITATOR = registerBlock("lightning_agitator", () ->
            new LightningAgitatorBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .lightLevel(agitatorEmission())
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresCorrectToolForDrops()
                            .strength(3.5F)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static ToIntFunction<BlockState> agitatorEmission() {
        return (state) -> state.getValue(LightningAgitatorBlock.POWERED) &&
                !state.getValue(LightningAgitatorBlock.SHORT_CIRCUITED) ? 15 : 0;
    }
}

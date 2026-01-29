package com.juiceybeans.slugmo.block;

import com.juiceybeans.slugmo.Slugmo;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Slugmo.MOD_ID);

    public static final RegistryObject<BlockEntityType<AllayFountainBE>> ALLAY_FOUNTAIN_BE = BLOCK_ENTITIY_TYPES.register("allay_fountain_be", () ->
            BlockEntityType.Builder.of(AllayFountainBE::new, ModBlocks.ALLAY_FOUNTAIN.get())
                    .build(null));
}

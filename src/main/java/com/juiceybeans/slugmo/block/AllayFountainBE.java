package com.juiceybeans.slugmo.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AllayFountainBE extends BlockEntity {
    public AllayFountainBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLAY_FOUNTAIN_BE.get(), pos, state);
    }
}

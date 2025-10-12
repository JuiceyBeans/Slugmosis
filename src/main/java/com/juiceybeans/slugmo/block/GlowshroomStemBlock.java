package com.juiceybeans.slugmo.block;

import com.juiceybeans.slugmo.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.AGE_3;

public class GlowshroomStemBlock extends CropBlock {
    public static final IntegerProperty AGE = AGE_3;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(5.0D, 0.0D, 5.0D, 11.0D, 11.0D, 11.0D),
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 13.0D, 13.0D),
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 13.0D, 13.0D),
            Block.box(2.0D, 0.0D, 2.0D, 16.0D, 13.0D, 16.0D)
    };

    public GlowshroomStemBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0));
    }

    protected ItemLike getBaseSeedId() {
        return ModItems.GLOWSHROOM_STEM.get();
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[this.getAge(pState)];
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    protected int getBonemealAgeIncrease(Level pLevel) {
        return Mth.nextInt(pLevel.random, 1, 2);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
        return switch (state.getValue(AGE)) {
            case 2 -> 4;
            case 3 -> 9;
            default -> 0;
        };
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
        return state.is(BlockTags.NYLIUM);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return level.getBlockState(blockpos).canSustainPlant(level, blockpos, Direction.UP, this);

        return level.dimensionType().hasCeiling() && this.mayPlaceOn(level.getBlockState(blockpos), level, blockpos);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return this.mayPlaceOn(state, world, pos);
    }

    @Override
    public SoundType getSoundType(BlockState state) {
        return SoundType.ROOTS;
    }
}

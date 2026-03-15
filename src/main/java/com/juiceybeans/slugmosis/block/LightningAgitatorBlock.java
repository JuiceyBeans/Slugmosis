package com.juiceybeans.slugmosis.block;

import com.google.common.collect.Lists;
import com.juiceybeans.slugmosis.util.MobUtils;
import com.sun.jna.platform.win32.WinDef;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Supplier;

public class LightningAgitatorBlock extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty SHORT_CIRCUITED = BooleanProperty.create("short_circuited");
    private static final Map<BlockGetter, List<LightningAgitatorBlock.Toggle>> RECENT_TOGGLES = new WeakHashMap<>();
    public static final int RECENT_TOGGLE_TIMER = 60;
    public static final int MAX_RECENT_TOGGLES = 4;
    private static final int TOGGLE_DELAY = 4;

    public LightningAgitatorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, Boolean.FALSE).setValue(SHORT_CIRCUITED, Boolean.FALSE));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        return this.defaultBlockState().setValue(POWERED, placeContext.getLevel().hasNeighborSignal(placeContext.getClickedPos()));
    }

    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborPos, boolean movedByPiston) {
        if (!level.isClientSide) {
            boolean isShortCircuited = state.getValue(SHORT_CIRCUITED);
            if (isShortCircuited) return;

            boolean isPowered = state.getValue(POWERED);

            if (!isPowered && level.hasNeighborSignal(pos)) {
                level.scheduleTick(pos, this, TOGGLE_DELAY);
                level.setBlock(pos, state.cycle(POWERED), 2);
            } else if (isPowered && !level.hasNeighborSignal(pos)) {
                level.setBlock(pos, state.cycle(POWERED), 2);
            }
        }
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        List<LightningAgitatorBlock.Toggle> toggles = RECENT_TOGGLES.get(level);

        while (toggles != null && !toggles.isEmpty() && level.getGameTime() - (toggles.get(0)).when > (long) RECENT_TOGGLE_TIMER) {
            toggles.remove(0);
        }

        if (state.getValue(POWERED)) {
            if (!level.hasNeighborSignal(pos)) {
                level.setBlock(pos, state.cycle(POWERED), 2);
            }

            if (isToggledTooFrequently(level, pos, true)) {
                level.setBlock(pos, state.setValue(SHORT_CIRCUITED, true), 2);
            }

            if (state.getValue(POWERED) && !state.getValue(SHORT_CIRCUITED) && !isToggledTooFrequently(level, pos, false)) {
                MobUtils.spawnEntityAtPosition(new LightningBolt(EntityType.LIGHTNING_BOLT, level), level, pos.above());
            }
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED, SHORT_CIRCUITED);
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Supplier<Vec3> supplier = () -> new Vec3(
                Mth.nextDouble(random, -0.005F, 0.005F),
                Mth.nextDouble(random, -0.005F, 0.005F),
                Mth.nextDouble(random, -0.005F, 0.005F));

        if (state.getValue(POWERED) && level.random.nextInt(200) <= level.getGameTime() % 200L &&
                pos.getY() == level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()) - 1) {
            ParticleUtils.spawnParticlesOnBlockFace(level, pos, ParticleTypes.ELECTRIC_SPARK,
                    UniformInt.of(1, 2), Direction.UP, supplier, 0.65D);
        }
    }

    private static boolean isToggledTooFrequently(Level level, BlockPos pos, boolean logToggle) {
        List<LightningAgitatorBlock.Toggle> list = RECENT_TOGGLES.computeIfAbsent(level, (blockGetter) -> Lists.newArrayList());

        if (logToggle) {
            list.add(new LightningAgitatorBlock.Toggle(pos.immutable(), level.getGameTime()));
        }

        int i = 0;

        for (Toggle toggle : list) {
            if (toggle.pos.equals(pos)) {
                ++i;
                if (i >= MAX_RECENT_TOGGLES) {
                    return true;
                }
            }
        }

        return false;
    }

    public static class Toggle {
        final BlockPos pos;
        final long when;

        public Toggle(BlockPos pPos, long pWhen) {
            this.pos = pPos;
            this.when = pWhen;
        }
    }
}

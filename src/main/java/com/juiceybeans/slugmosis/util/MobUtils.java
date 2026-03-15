package com.juiceybeans.slugmosis.util;

import com.juiceybeans.slugmosis.Slugmosis;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MobUtils {
    public static void spawnEntityAtPosition(Entity entity, Level level, Vec3 pos) {
        EntityType<?> type = entity.getType();
        Entity newEntity = type.create(level);

        if (newEntity != null) {
            newEntity.moveTo(pos.get(Direction.Axis.X), pos.get(Direction.Axis.Y), pos.get(Direction.Axis.Z));
            level.addFreshEntity(newEntity);
            Slugmosis.LOGGER.debug("Spawned {} at {}", entity.getType(), pos);
        }
    }

    public static void spawnEntityAtPosition(Entity entity, Level level, double x, double y, double z) {
        spawnEntityAtPosition(entity, level, new Vec3(x, y, z));
    }

    public static void spawnEntityAtPosition(Entity entity, Level level, BlockPos pos) {
        spawnEntityAtPosition(entity, level, pos.getCenter());
    }
}

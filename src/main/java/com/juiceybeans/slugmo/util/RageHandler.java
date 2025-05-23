package com.juiceybeans.slugmo.util;

import com.juiceybeans.slugmo.Config;
import com.juiceybeans.slugmo.ModKeybinds;
import com.juiceybeans.slugmo.Slugmo;
import com.juiceybeans.slugmo.capability.IPlayerRage;
import com.juiceybeans.slugmo.capability.PlayerRage;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.EntityGetter;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

import java.util.List;

public class RageHandler {
    private static int ticksSinceLastRageUpdate;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (player.hurtMarked) {
            increaseRage(player, 1);
        }

        int rageMeter = getRage(player);

        // Only run rage cooldown if player already has some
        if (rageMeter > 0) {
            if (event.phase != TickEvent.Phase.END || event.side != LogicalSide.SERVER) {
                return;
            }

            if (ticksSinceLastRageUpdate / (20 * Config.rageCooldownInterval) >= 1) {
                Slugmo.LOGGER.debug("Cooldown of {} has passed, decreasing rage from {} to {}", Config.rageCooldownInterval,
                        rageMeter, rageMeter - Config.rageCooldownLoss);
                decreaseRage(player, Config.rageCooldownLoss);
            }
        }

        ticksSinceLastRageUpdate++;
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            setRage(player, 0);
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {

            Minecraft mc = Minecraft.getInstance();
            var player = mc.player;

            if (ModKeybinds.UNLEASH_RAGE_MAPPING.get().consumeClick() && player != null) {
                Slugmo.LOGGER.debug("Keybind pressed");
                unleashRage(player);
            }
        }
    }

    public static IPlayerRage getRageCap(Player player) {
        return player.getCapability(PlayerRage.INSTANCE).resolve().orElse(null);
    }

    public static int getRage(Player player) {
        var cap = getRageCap(player);

        if (cap == null) {
            return 0;
        } else {
            var rageVal = cap.getValue();
            return rageVal;
        }
    }

    public static void setRage(Player player, int amount) {
        getRageCap(player).setRage(amount);
        ticksSinceLastRageUpdate = 0;
    }

    public static void increaseRage(Player player, int amount) {
        var rageVal = getRage(player);

        if (rageVal >= Config.rageCap) {
            Slugmo.LOGGER.debug("Rage is already maxed out, did not increase");
            return;
        }

        setRage(player, Math.min(Config.rageCap, rageVal + amount));
        Slugmo.LOGGER.debug("Attempted to increase rage from {} to {}", rageVal - amount, rageVal);
    }

    public static void decreaseRage(Player player, int amount) {
        var rageVal = getRage(player);

        if (rageVal <= 0) {
            Slugmo.LOGGER.debug("Rage is already zero, did not decrease");
            return;
        }

        setRage(player, Math.max(rageVal - amount, 0));
        Slugmo.LOGGER.debug("Attempted to decrease rage from {} to {}", rageVal, rageVal - amount);
    }

    public static void unleashRage(Player player) {
        if (getRage(player) < 30) {
            player.displayClientMessage(Component.literal("Rage simmers within you, but it has yet to reach breaking point.")
                    .withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            return;
        }

        Vec3 pos = player.position();
        Slugmo.LOGGER.debug("Attempting to unleash rage for player {} at {}", player, pos);
        player.displayClientMessage(Component.literal("Paint the world red.")
                .withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
        player.playSound(SoundEvents.WITHER_SPAWN);
        decreaseRage(player, 30);

        List<Entity> targets = getEntitiesInRadius(player.level(), pos, 8, player);

        for (Entity target : targets) {
            if (!target.isAttackable()) {
                return;
            } else {
                target.hurt(target.damageSources().generic(), 8);
                target.setSecondsOnFire(10 * 20);
            }
        }
    }

    private static List<Entity> getEntitiesInRadius(EntityGetter level, Vec3 centerPos, double radius, Entity filter) {
        AABB area = new AABB(
                centerPos.x - radius, centerPos.y - radius, centerPos.z - radius,
                centerPos.x + radius, centerPos.y + radius, centerPos.z + radius
        );

        Slugmo.LOGGER.debug("Found entities in radius {} of {}: {}", radius, centerPos, level.getEntities(null, area));

        return level.getEntities(filter, area);
    }

    private static List<Entity> getEntitiesInRadius(EntityGetter level, Vec3 centerPos, double radius) {
        return getEntitiesInRadius(level, centerPos, radius, null);
    }
}

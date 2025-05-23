package com.juiceybeans.slugmo;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Slugmo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<Integer> RAGE_CAP =
            BUILDER.comment("How much damage it takes to max out the Rage meter (default: 55)")
                    .define("rage_cap", 55);

    private static final ForgeConfigSpec.ConfigValue<Integer> RAGE_COOLDOWN_INTERVAL =
            BUILDER.comment("Interval of time after which Rage reduces in seconds (default: 30)")
                    .define("rage_cooldown_interval", 30);

    private static final ForgeConfigSpec.ConfigValue<Integer> RAGE_COOLDOWN_LOSS =
            BUILDER.comment("How much Rage reduces after the cooldown interval (default: 5)")
                    .define("rage_cooldown_value", 5);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int rageCap;
    public static int rageCooldownInterval;
    public static int rageCooldownLoss;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        rageCap = RAGE_CAP.get();
        rageCooldownInterval = RAGE_COOLDOWN_INTERVAL.get();
        rageCooldownLoss = RAGE_COOLDOWN_LOSS.get();
    }
}
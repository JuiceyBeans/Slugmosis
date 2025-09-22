package com.juiceybeans.slugmo.event;

import com.juiceybeans.slugmo.Slugmo;
import com.juiceybeans.slugmo.util.RageHandler;
import com.juiceybeans.slugmo.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.loading.FMLLoader;

@OnlyIn(Dist.CLIENT)
public class RenderRageOverlayEvent {
    private static final ResourceLocation rageBarTexture = Slugmo.id("textures/gui/rage.png");
    private static final ResourceLocation rageFillTexture = Slugmo.id("textures/gui/rage_fill.png");

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        assert player != null;

        Font font = mc.font;
        GuiGraphics gui = event.getGuiGraphics();

        if (!FMLLoader.isProduction()) RenderUtils.drawText(gui, font, "Slugmo Dev Environment", 4, 4, 0xFFFFF);

        var rage = RageHandler.getRage(player);
        if (rage == 0) return;
        // yPos = size of the rage bar texture + yPos of rage bar - dead space in rage bar texture - rage int
        RenderUtils.drawTexture(gui, rageFillTexture, 4, (64 + 16 - 4 - rage), 16, rage);
        // Slugmo.LOGGER.debug("Rage is currently {}, rage texture height is {}px", rage, 64 + 16 - rage);
        RenderUtils.drawTexture(gui, rageBarTexture, 4, 16, 16, 64);
    }
}
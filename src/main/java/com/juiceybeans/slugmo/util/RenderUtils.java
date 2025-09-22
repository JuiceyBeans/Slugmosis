package com.juiceybeans.slugmo.util;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class RenderUtils {
    public static void drawTexture(GuiGraphics gui, ResourceLocation textureLocation, int xPos, int yPos, int width, int height) {
        gui.blit(textureLocation, xPos, yPos, 0, 0, width, height, width, height);
    }

    public static void drawText(GuiGraphics gui, Font font, String text, int xPos, int yPos) {
        drawText(gui, font, text, xPos, yPos, 0xFFFFFF);
    }

    public static void drawText(GuiGraphics gui, Font font, String text, int xPos, int yPos, int color) {
        gui.drawString(font, Component.literal(text), xPos, yPos, color, true);
    }
}

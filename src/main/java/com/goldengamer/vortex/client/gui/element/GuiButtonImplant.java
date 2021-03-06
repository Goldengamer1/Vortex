package com.goldengamer.vortex.client.gui.element;

import com.goldengamer.vortex.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by golde on 06/10/2015.
 */

//This Class renders the Button after activated by the GuiEvent class
public class GuiButtonImplant extends GuiButton
{
    private static final ResourceLocation texture = new ResourceLocation("vortex:textures/gui/container/implantinventory.png");

    public GuiButtonImplant(int buttonId, int x, int y, int width, int height, String buttonText)
    {
        super(buttonId, x, y, width, height, buttonText);
    }

    public void drawButton(Minecraft mc, int xx, int yy)
    {
        if (this.visible)
        {
            FontRenderer fontRenderer = mc.fontRenderer;

            //Binds texture for the button
            mc.getTextureManager().bindTexture(texture);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = xx >= this.xPosition && yy >= this.yPosition && xx < this.xPosition + this.width && yy < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            // if the button is being hovered over it will turn from gray to gold and Text will show
            if (k == 1)
            {
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 177, 0, 10, 10);
            } else {
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 187, 0, 10, 10);
                this.drawCenteredString(fontRenderer, this.displayString, this.xPosition + 27, this.yPosition + 1, 0xffffff);
            }
            this.mouseDragged(mc, xx, yy);
        }
    }
}

package com.goldengamer.vortex.levelHandler.Hud;

import com.goldengamer.vortex.handler.ConfigurationHandler;
import com.goldengamer.vortex.levelHandler.ExtendedPlayer;
import com.goldengamer.vortex.reference.Textures;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

/**
 * Created by golde on 18/10/2015.
 */
public class GuiLevelBar extends Event
{
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderGameOverlay(RenderGameOverlayEvent event)
    {

    }
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void RenderLevelBar(RenderGameOverlayEvent event)
    {
        if (!event.isCancelable() && event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE)
        {
            Minecraft mc = Minecraft.getMinecraft();
            ExtendedPlayer props = ExtendedPlayer.get(mc.thePlayer);

            if (!(props == null) || !(props.getMaxRage() == 0))
            {
                int xPos = ConfigurationHandler.ClassLevelBarXPos();
                int yPos = ConfigurationHandler.ClassLevelBarYPos();
                if (xPos == 0 && yPos == 0)
                {
                    if(mc.thePlayer.capabilities.isCreativeMode)
                    {
                        xPos = event.resolution.getScaledWidth() / 2 - 91;
                        yPos = event.resolution.getScaledHeight() - 38;
                    }
                    else
                    {
                        xPos = event.resolution.getScaledWidth() / 2 - 91;
                        yPos = event.resolution.getScaledHeight() - 65;
                    }
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);

                mc.renderEngine.bindTexture(Textures.Gui.ABILITYBAR);
                mc.ingameGUI.drawTexturedModalRect(xPos, yPos, 0, 249, 182, 7);

                int levelbarwidth = (int) (((float) props.getWarriorXP() / props.getXPToNextLevel()) * 182);
                //TODO this is off
                mc.ingameGUI.drawTexturedModalRect(xPos, yPos, 0, 241, levelbarwidth, 7);

                //=================================
                //This draws the level text
                //=================================
                String level = (new StringBuilder()).append("").append(props.getWarriorLV()).toString();
                int xPosLvStr = xPos + 91;
                int yPosLvStr = yPos;
                //TODO change color
                int color = 0xFFFFFF;
                if (ConfigurationHandler.ClassLevelText() == 1)
                {
                    //top
                    xPosLvStr -= mc.fontRenderer.getStringWidth(level) / 2;
                    yPosLvStr = yPosLvStr - 9;
                }
                else if (ConfigurationHandler.ClassLevelText() == 2)
                {
                    //left
                    xPosLvStr = xPos - mc.fontRenderer.getStringWidth(level) - 1;
                }
                else if (ConfigurationHandler.ClassLevelText() == 3)
                {
                    //right
                    xPosLvStr = event.resolution.getScaledWidth() / 2 + 91 + 1;
                }
                else if (ConfigurationHandler.ClassLevelText() == 4)
                {
                    //bottom
                    xPosLvStr -= mc.fontRenderer.getStringWidth(level) / 2;
                    yPosLvStr = yPosLvStr + 9;
                }
                mc.fontRenderer.drawStringWithShadow(level, xPosLvStr, yPosLvStr, color);
            }
        }
    }
}

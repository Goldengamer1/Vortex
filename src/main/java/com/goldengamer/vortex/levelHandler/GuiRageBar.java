package com.goldengamer.vortex.levelHandler;

import com.goldengamer.vortex.handler.ConfigurationHandler;
import com.goldengamer.vortex.reference.Textures;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

/**
 * Created by golde on 16/10/2015.
 */
public class GuiRageBar extends Event
{
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderGameOverlay(RenderGameOverlayEvent event)
    {
        if (!event.isCancelable() && event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE)
        {
            Minecraft mc = Minecraft.getMinecraft();
            ExtendedPlayer props = ExtendedPlayer.get(mc.thePlayer);

            if (!(props == null) || !(props.getMaxRage() == 0))
            {

                int xPos = ConfigurationHandler.ClassResourceBarXPos();
                int yPos = ConfigurationHandler.ClassResourceBarYPos();
                if (xPos == 0 && yPos == 0)
                {
                    if(mc.thePlayer.capabilities.isCreativeMode)
                    {
                        xPos = event.resolution.getScaledWidth() / 2 - 91;
                        yPos = event.resolution.getScaledHeight() - 30;
                    }
                    else
                    {
                        xPos = event.resolution.getScaledWidth() / 2 - 91;
                        yPos = event.resolution.getScaledHeight() - 57;
                    }
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);

                mc.renderEngine.bindTexture(Textures.Gui.ABILITYBAR);
                mc.ingameGUI.drawTexturedModalRect(xPos, yPos, 0, 233, 182, 7);

                //this find out much rage to pixel there is e.g 500 rage / 1000 = 0.5 * 182 = 91 rage per pixel
                int ragebarwidth = (int) (((float) props.getCurrentRage() / props.getMaxRage()) * 182);
                //LogHelper.info("[GUI Rage] Current rage bar width: " + ragebarwidth);

                //TODO  this is off
                mc.ingameGUI.drawTexturedModalRect(xPos, yPos, 0, 225, ragebarwidth, 7);
            }
        }
    }
}

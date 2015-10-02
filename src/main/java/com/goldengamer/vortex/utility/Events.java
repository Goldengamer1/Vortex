package com.goldengamer.vortex.utility;

import com.goldengamer.vortex.init.ModItems;
import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/**
 * Created by golde on 02/10/2015.
 */
public class Events
{


    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (ModItems.HARD_TO_FINDIUM_SWORD.isSeleceted) {
            if (!event.isCancelable() && event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
                Minecraft mc = Minecraft.getMinecraft();

                // if (!mc.thePlayer.capabilities.isCreativeMode) {
                int posX = event.resolution.getScaledWidth() / 2 - 50;
                int posY = event.resolution.getScaledHeight() / event.resolution.getScaledHeight();

                //TODO MOVE THIS TO Textures
                mc.renderEngine.bindTexture(new ResourceLocation("vortex:textures/gui/abilityBar.png"));

                mc.ingameGUI.drawTexturedModalRect(posX, posY, 0, 0, 102, 22);


                // }
            }
        }
    }
}

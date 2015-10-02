package com.goldengamer.vortex.utility;

import com.goldengamer.vortex.init.ModItems;
import com.goldengamer.vortex.reference.Reference;
import com.goldengamer.vortex.reference.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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

        Minecraft minecraft = FMLClientHandler.instance().getClient();
        EntityPlayer entityPlayer = minecraft.thePlayer;
        ItemStack currentItemStack = entityPlayer.inventory.getCurrentItem();

        //this checks if the item is a instanceof IHudOverlay
        if (currentItemStack != null && currentItemStack.getItem() instanceof IHudOverlay) {

            if (!event.isCancelable() && event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
                Minecraft mc = Minecraft.getMinecraft();

                int posX = event.resolution.getScaledWidth() / 2 - 50;
                int posY = event.resolution.getScaledHeight() / event.resolution.getScaledHeight();
                //Texture
                mc.renderEngine.bindTexture(Textures.Gui.ABILITYBAR);
                mc.ingameGUI.drawTexturedModalRect(posX, posY, 0, 0, 102, 22);

            }
        }
    }
}

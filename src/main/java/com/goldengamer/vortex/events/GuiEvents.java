package com.goldengamer.vortex.events;

import com.goldengamer.vortex.client.gui.element.GuiButtonImplant;
import com.goldengamer.vortex.network.bidirectional.MessageOpenImplantGui;
import com.goldengamer.vortex.network.PacketHandler;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.lang.reflect.Method;


/**
 * Created by golde on 06/10/2015.
 */
public class GuiEvents
{
    public static int[] implantButtonPos;

    @SideOnly(value = Side.CLIENT)
    @SubscribeEvent
    public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event)
    {
        //|| event.gui instanceof GuiPlayerImplantExpanded
        if (event.gui instanceof GuiInventory)
        {
            int xSize = 176;
            int ySize = 166;

            int guiLeft = (event.gui.width - xSize) / 2;
            int guiTop = (event.gui.height - ySize) / 2;

            if (!event.gui.mc.thePlayer.getActivePotionEffects().isEmpty() && isNeiHidden())
            {
                guiLeft = 160 + (event.gui.width - xSize - 200) / 2;
            }
            implantButtonPos = new  int[]{27,9};
            event.buttonList.add(new GuiButtonImplant(136, guiLeft + implantButtonPos[0], guiTop + implantButtonPos[1], 10, 10, I18n.format((event.gui instanceof GuiInventory)?"button.vortex:implant":"button.vortex:normal", new Object[0])));
        }
    }

    @SideOnly(value = Side.CLIENT)
    @SubscribeEvent
    public void guiPostAction(GuiScreenEvent.ActionPerformedEvent.Post event)
    {
        if (event.gui instanceof GuiInventory && event.button.getClass().equals(GuiButtonImplant.class))
        {
            PacketHandler.INSTANCE.sendToServer(new MessageOpenImplantGui(event.gui.mc.thePlayer,1));
        }
    }

    static Method isNEIHidden;
    boolean isNeiHidden() {
        boolean hidden=true;
        try {
            if (isNEIHidden==null) {
                Class fake = Class.forName("codechicken.nei.NEIClientConfig");
                isNEIHidden = fake.getMethod("isHidden");
            }
            hidden = (Boolean) isNEIHidden.invoke(null);
        } catch(Exception ex) { }
        return hidden;
    }
}

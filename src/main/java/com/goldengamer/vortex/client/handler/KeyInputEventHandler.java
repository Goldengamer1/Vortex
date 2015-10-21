package com.goldengamer.vortex.client.handler;

import com.goldengamer.vortex.client.settings.Keybindings;
import com.goldengamer.vortex.network.bidirectional.MessageOpenImplantGui;
import com.goldengamer.vortex.network.PacketHandler;
import com.goldengamer.vortex.reference.Key;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by golde on 15/09/2015.
 */
public class KeyInputEventHandler
{

    @SubscribeEvent
    private static Key onKeyPress()
    {
        if(Keybindings.ability1.isPressed()) {return Key.ABILITY1;}
        if(Keybindings.ability2.isPressed()) {return Key.ABILITY2;}
        if(Keybindings.ability3.isPressed()) {return Key.ABILITY3;}
        if(Keybindings.ability4.isPressed()) {return Key.ABILITY4;}
        if(Keybindings.ability5.isPressed()) {return Key.ABILITY5;}
        if(Keybindings.guiButton.isPressed()) {return Key.GUIBUTTON;}

        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        //Debug tool
        //LogHelper.info(onKeyPress());
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (event.side == Side.SERVER) return;
        if (event.phase == TickEvent.Phase.START ) {
            if (Keybindings.guiButton.getIsKeyPressed() && FMLClientHandler.instance().getClient().inGameHasFocus)
            {
                PacketHandler.INSTANCE.sendToServer(new MessageOpenImplantGui(Minecraft.getMinecraft().thePlayer, 1));
            }
        }
    }

}

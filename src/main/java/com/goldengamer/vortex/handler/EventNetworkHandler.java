package com.goldengamer.vortex.handler;

import com.goldengamer.vortex.utility.PlayerHelper;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by golde on 07/10/2015.
 */
public class EventNetworkHandler
{
    @SubscribeEvent
    public void playerLoggedInEvent (PlayerEvent.PlayerLoggedInEvent event)    {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER)        {
            syncImplants(event.player);
        }
    }

    public static void syncImplants(EntityPlayer player) {
        for (int a = 0; a < 4; a++) {
            PlayerHelper.getPlayerImplants(player).syncSlotToClients(a);
        }
    }
}

package com.goldengamer.vortex.proxy;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by golde on 14/09/2015.
 */
public class CommonProxy
{
    //@Override
    public void registerKeyBindings()
    {
        //NOOP (noting)
    }

    public EntityPlayer getPlayerEntity(MessageContext ctx)
    {
        return  ctx.getServerHandler().playerEntity;
    }
}
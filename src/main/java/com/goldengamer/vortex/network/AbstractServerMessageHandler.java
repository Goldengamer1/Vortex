package com.goldengamer.vortex.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by golde on 16/09/2015.
 */

/**
 *
 * This is just a convenience class that will prevent the client-side message handling
 * method from appearing in our server message handler classes.
 *
 */
public abstract class AbstractServerMessageHandler<T extends IMessage> extends AbstractMessageHandler<T>
{
    public final IMessage handleClientMessage(EntityPlayer player, T message, MessageContext ctx)
    {
        return null;
    }
}

package com.goldengamer.vortex.network;

import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by golde on 17/09/2015.
 */
public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.LOWERCASE_MOD_ID);

    public static void init()
    {
        //INSTANCE.registerMessage(MessageRightClick.Handler.class, MessageRightClick.class, 0, Side.SERVER);
    }
}

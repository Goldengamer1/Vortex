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
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init()
    {
        INSTANCE.registerMessage(MessageOpenImplantGui.HandlerServer.class, MessageOpenImplantGui.class, 0, Side.SERVER);
        INSTANCE.registerMessage(MessageOpenImplantGui.HandlerClient.class, MessageOpenImplantGui.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(MessageImplantSlotsSync.class, MessageImplantSlotsSync.class, 2, Side.SERVER);

    }
}

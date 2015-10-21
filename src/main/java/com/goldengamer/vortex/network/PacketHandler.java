package com.goldengamer.vortex.network;

import com.goldengamer.vortex.network.bidirectional.MessageImplantSlotsSync;
import com.goldengamer.vortex.network.bidirectional.MessageOpenImplantGui;
import com.goldengamer.vortex.network.client.SyncPlayerPropsMessage;
import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by golde on 17/09/2015.
 */

/**
 *
 * This class will house the SimpleNetworkWrapper instance, which I will name 'dispatcher',
 * as well as give us a logical place from which to register our packets. These two things
 * could be done anywhere, however, even in your Main class, but I will be adding other
 * functionality (see below) that gives this class a bit more utility.
 *
 * While unnecessary, I'm going to turn this class into a 'wrapper' for SimpleNetworkWrapper
 * so that instead of writing "PacketDispatcher.dispatcher.{method}" I can simply write
 * "PacketDispatcher.{method}" All this does is make it quicker to type and slightly shorter;
 * if you do not care about that, then make the 'dispatcher' field public instead of private,
 * or, if you do not want to add a new class just for one field and one static method that
 * you could put anywhere, feel free to put them wherever.
 *
 * For further convenience, I have also added two extra sendToAllAround methods: one which
 * takes an EntityPlayer and one which takes coordinates.
 *
 * inspired by coolAlias
 */

public class PacketHandler
{
    private static byte packetId = 0;

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static final void registerPackets()
    {
        // Packets handled on CLIENT
        //registerMessage(SyncPlayerPropsMessage.class);

        // Packets handled on SERVER


        // Bidirectional packets:


    }

    public static void init()
    {
        //TODO redo theres to use the new system iv put in
        INSTANCE.registerMessage(MessageOpenImplantGui.HandlerServer.class, MessageOpenImplantGui.class, packetId++, Side.SERVER);
        INSTANCE.registerMessage(MessageOpenImplantGui.HandlerClient.class, MessageOpenImplantGui.class, packetId++, Side.CLIENT);
        INSTANCE.registerMessage(MessageImplantSlotsSync.class, MessageImplantSlotsSync.class, packetId++, Side.SERVER);

        INSTANCE.registerMessage(SyncPlayerPropsMessage.class, SyncPlayerPropsMessage.class, packetId++ ,Side.CLIENT);

    }

    private static final <T extends AbstractMessage<T> & IMessageHandler<T, IMessage>> void registerMessage(Class<T> clazz) {
        // We can tell by the message class which side to register it on by using #isAssignableFrom (google it)

        // Also, one can see the convenience of using a static counter 'packetId' to keep
        // track of the current index, rather than hard-coding them all, plus it's one less
        // parameter to pass.
        if (AbstractMessage.AbstractClientMessage.class.isAssignableFrom(clazz)) {
            PacketHandler.INSTANCE.registerMessage(clazz, clazz, packetId++, Side.CLIENT);
        } else if (AbstractMessage.AbstractServerMessage.class.isAssignableFrom(clazz)) {
            PacketHandler.INSTANCE.registerMessage(clazz, clazz, packetId++, Side.SERVER);
        } else {
            // hopefully you didn't forget to extend the right class, or you will get registered on both sides
            PacketHandler.INSTANCE.registerMessage(clazz, clazz, packetId, Side.CLIENT);
            PacketHandler.INSTANCE.registerMessage(clazz, clazz, packetId++, Side.SERVER);
        }
    }

    //========================================================//
    // The following methods are the 'wrapper' methods; again,
    // this just makes sending a message slightly more compact
    // and is purely a matter of stylistic preference
    //========================================================//

    /**
     * Send this message to the specified player's client-side counterpart.
     * See {@link SimpleNetworkWrapper# sendTo(IMessage, EntityPlayerMP)}
     */
    public static final void sendTo(IMessage message, EntityPlayerMP player) {
        PacketHandler.INSTANCE.sendTo(message, player);
    }

    /**
     * Send this message to everyone.
     * See {@link SimpleNetworkWrapper#sendToAll(IMessage)}
     */
    public static void sendToAll(IMessage message) {
        PacketHandler.INSTANCE.sendToAll(message);
    }

    /**
     * Send this message to everyone within a certain range of a point.
     * See {@link SimpleNetworkWrapper#sendToAllAround(IMessage, NetworkRegistry.TargetPoint)}
     */
    public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
        PacketHandler.INSTANCE.sendToAllAround(message, point);
    }

    /**
     * Sends a message to everyone within a certain range of the coordinates in the same dimension.
     * Shortcut to {@link SimpleNetworkWrapper#sendToAllAround(IMessage, NetworkRegistry.TargetPoint)}
     */
    public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range) {
        PacketHandler.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
    }

    /**
     * Sends a message to everyone within a certain range of the player provided.
     * Shortcut to {@link SimpleNetworkWrapper#sendToAllAround(IMessage, NetworkRegistry.TargetPoint)}
     */
    public static final void sendToAllAround(IMessage message, EntityPlayer player, double range) {
        PacketHandler.sendToAllAround(message, player.worldObj.provider.dimensionId, player.posX, player.posY, player.posZ, range);
    }

    /**
     * Send this message to everyone within the supplied dimension.
     * See {@link SimpleNetworkWrapper#sendToDimension(IMessage, int)}
     */
    public static final void sendToDimension(IMessage message, int dimensionId) {
        PacketHandler.INSTANCE.sendToDimension(message, dimensionId);
    }

    /**
     * Send this message to the server.
     * See {@link SimpleNetworkWrapper#sendToServer(IMessage)}
     */
    public static final void sendToServer(IMessage message) {
        PacketHandler.INSTANCE.sendToServer(message);
    }

    public static void sendPacketToPlayersAround(IMessage message, Entity e) {
        PacketHandler.INSTANCE.sendToAllAround(message, new NetworkRegistry.TargetPoint(e.dimension, e.posX, e.posY, e.posZ, 100));
    }
}

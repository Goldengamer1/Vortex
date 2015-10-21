package com.goldengamer.vortex.network.bidirectional;

import com.goldengamer.vortex.Vortex;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by golde on 06/10/2015.
 */
public class MessageOpenImplantGui implements IMessage{

    int dim;
    int playerid;
    int guiid;

    public MessageOpenImplantGui() {}
    public MessageOpenImplantGui(EntityPlayer player, int guiid)
    {
        this.dim = player.worldObj.provider.dimensionId;
        this.playerid = player.getEntityId();
        this.guiid = guiid;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(dim);
        buf.writeInt(playerid);
        buf.writeInt(guiid);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.dim = buf.readInt();
        this.playerid = buf.readInt();
        this.guiid = buf.readInt();
    }

    public static class HandlerClient implements IMessageHandler<MessageOpenImplantGui, IMessage>
    {
        @Override
        public IMessage onMessage(MessageOpenImplantGui message, MessageContext ctx)
        {
            Minecraft.getMinecraft().thePlayer.openGui(Vortex.instance, message.guiid, Minecraft.getMinecraft().thePlayer.worldObj, MathHelper.floor_double(Minecraft.getMinecraft().thePlayer.posX), MathHelper.floor_double(Minecraft.getMinecraft().thePlayer.posY), MathHelper.floor_double(Minecraft.getMinecraft().thePlayer.posZ));
            return null;
        }
    }

    public static class HandlerServer implements IMessageHandler<MessageOpenImplantGui, IMessage>
    {
        @Override
        public IMessage onMessage(MessageOpenImplantGui message, MessageContext ctx)
        {
            World world = DimensionManager.getWorld(message.dim);
            if (world == null)
                    return null;
            Entity ent = ctx.getServerHandler().playerEntity;
            if (!(ent instanceof EntityPlayer))
                    return null;
            EntityPlayer player = (EntityPlayer) ent;

            //TODO do sync
            //PacketHandler.sendToAll(new MessageNBTSync(player));

            boolean hasServerGui = Vortex.proxy.getServerGuiElement(message.guiid, player, world, (int) player.posX, (int) player.posY, (int) player.posZ)!=null;;
            player.openGui(Vortex.instance, message.guiid, player.worldObj, MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ));
            return hasServerGui?null: new MessageOpenImplantGui(player,message.guiid);
        }
    }
}

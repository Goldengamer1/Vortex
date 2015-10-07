package com.goldengamer.vortex.network;

import com.goldengamer.vortex.Vortex;
import com.goldengamer.vortex.utility.PlayerHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;;

/**
 * Created by golde on 07/10/2015.
 */
public class MessageImplantSlotsSync implements IMessage, IMessageHandler<MessageImplantSlotsSync, IMessage>
{

    int slot;
    int playerId;
    ItemStack implant = null;

    public MessageImplantSlotsSync() {}

    public MessageImplantSlotsSync(EntityPlayer player, int slot)
    {
        this.slot = slot;
        this.implant = PlayerHelper.getPlayerImplants(player).getStackInSlot(slot);
        this.playerId = player.getEntityId();
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeByte(slot);
        buffer.writeInt(playerId);
        PacketBuffer pb = new PacketBuffer(buffer);
        try {
            pb.writeItemStackToBuffer(implant);
        } catch (IOException e) {
        }
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        slot = buffer.readByte();
        playerId = buffer.readInt();
        PacketBuffer pb = new PacketBuffer(buffer);
        try {
            implant = pb.readItemStackFromBuffer();
        } catch (IOException e) {
        }
    }

    @Override
    public IMessage onMessage(MessageImplantSlotsSync message, MessageContext ctx)
    {
        World world = Vortex.proxy.getClientWorld();
        if (world == null) return null;
        Entity p = world.getEntityByID(message.playerId);
        if (p != null && p instanceof EntityPlayer) {
            PlayerHelper.getPlayerImplants((EntityPlayer) p).stackList[message.slot] = message.implant;
        }
        return null;
    }

}
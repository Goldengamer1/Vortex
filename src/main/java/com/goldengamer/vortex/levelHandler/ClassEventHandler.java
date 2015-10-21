package com.goldengamer.vortex.levelHandler;

import com.goldengamer.vortex.network.PacketHandler;
import com.goldengamer.vortex.network.client.SyncPlayerPropsMessage;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * Created by golde on 16/10/2015.
 */
public class ClassEventHandler
{

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
        {
            ExtendedPlayer.register((EntityPlayer) event.entity);
        }

        if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME) == null)
        {
            event.entity.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer((EntityPlayer) event.entity));
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            //ExtendedPlayer.get((EntityPlayer) event.entity).sync();
            PacketHandler.sendTo(new SyncPlayerPropsMessage((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
        }
    }

    @SubscribeEvent
    public void onClonePlayer(PlayerEvent.Clone event)
    {
	    ExtendedPlayer.get(event.entityPlayer).copy(ExtendedPlayer.get(event.original));
    }
}

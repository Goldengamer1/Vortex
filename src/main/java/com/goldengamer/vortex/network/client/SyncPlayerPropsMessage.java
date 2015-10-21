package com.goldengamer.vortex.network.client;

import com.goldengamer.vortex.levelHandler.ExtendedPlayer;
import com.goldengamer.vortex.network.AbstractMessage;
import com.goldengamer.vortex.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

import java.io.IOException;

/**
 * Created by golde on 16/10/2015.
 */
public class SyncPlayerPropsMessage extends AbstractMessage.AbstractClientMessage<SyncPlayerPropsMessage>
{
    // this will store our ExtendedPlayer data, allowing us to easily read and write
    private NBTTagCompound data;

    // The basic, no-argument constructor MUST be included to use the new automated handling
    public SyncPlayerPropsMessage() {}

    // We need to initialize our data, so provide a suitable constructor:
    public SyncPlayerPropsMessage(EntityPlayer player) {
        // create a new tag compound
        data = new NBTTagCompound();
        // and save our player's data into it
        ExtendedPlayer.get(player).saveNBTData(data);
    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {

        data = buffer.readNBTTagCompoundFromBuffer();
    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {

        buffer.writeNBTTagCompoundToBuffer(data);
    }

    @Override
    public void process(EntityPlayer player, Side side) {

        LogHelper.info("Synchronizing extended properties data on CLIENT");
        ExtendedPlayer.get(player).loadNBTData(data);
    }
}

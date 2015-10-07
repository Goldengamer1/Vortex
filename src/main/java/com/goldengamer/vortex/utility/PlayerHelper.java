package com.goldengamer.vortex.utility;

import com.goldengamer.vortex.container.base.InventoryImplants;
import com.goldengamer.vortex.events.ItemBindableEvent;
import com.google.common.io.Files;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by golde on 03/10/2015.
 */
public class PlayerHelper
{
    //Gets player name
    public static String getUsername(EntityPlayer player)
    {
        return player.getCommandSenderName();
    }

    public static EntityPlayer getPlayerForUsername(String string)
    {
        if (MinecraftServer.getServer() == null)
        {
            return null;
        }
        return MinecraftServer.getServer().getConfigurationManager().func_152612_a(string);
    }

    //Checks if player is fake
    public static boolean isFakePlayer(World world, EntityPlayer player)
    {
        return !world.isRemote && isFakePlayer(player);
    }

    private static final Pattern FAKE_PLAYER_PATTERN = Pattern.compile("^(?:\\[.*\\])|(?:ComputerCraft)$");
    public static boolean isFakePlayer(EntityPlayer player)
    {
        return player instanceof FakePlayer || FAKE_PLAYER_PATTERN.matcher(getUsername(player)).matches();
    }

    //checks if item is bound if not sets it
    public static void checkAndSetItemOwner(ItemStack itemStack, EntityPlayer player)
    {
        checkAndSetItemPlayer(itemStack, player);
    }

    public static boolean checkAndSetItemPlayer(ItemStack itemStack, EntityPlayer player)
    {
        if (itemStack.hasTagCompound() && !itemStack.getTagCompound().getString("ownerName").equals(""))return true;

        ItemBindableEvent event = new ItemBindableEvent(player, PlayerHelper.getUsername(player), itemStack);

        if (!MinecraftForge.EVENT_BUS.post(event))
        {
            if (!itemStack.hasTagCompound())
            {
                itemStack.setTagCompound(new NBTTagCompound());
            }
            itemStack.getTagCompound().setString("ownerName", event.key);
            return true;
        }
        return false;
    }

    //
    //
    //

    private static HashMap<String, InventoryImplants> playerImplants = new HashMap<String, InventoryImplants>();

    public static void clearPlayerImplants(EntityPlayer player)
    {
        playerImplants.remove(player.getCommandSenderName());
    }

    public static InventoryImplants getPlayerImplants(EntityPlayer player)
    {
        if (!playerImplants.containsKey(player.getCommandSenderName()))
        {
            InventoryImplants inventory = new InventoryImplants(player);
            playerImplants.put(player.getCommandSenderName(), inventory);
        }
        return playerImplants.get(player.getCommandSenderName());
    }

    public static void setPlayerImplants(EntityPlayer player,InventoryImplants inventory)
    {
        playerImplants.put(player.getCommandSenderName(), inventory);
    }

    public static void loadPlayerImplants(EntityPlayer player, File file1, File file2)
    {
        if (player != null && !player.worldObj.isRemote)
        {
            try {
                NBTTagCompound data = null;
                boolean save = false;
                if (file1 != null && file1.exists())
                {
                    try {
                        FileInputStream fileinputstream = new FileInputStream(file1);
                        data = CompressedStreamTools.readCompressed(fileinputstream);
                        fileinputstream.close();
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                if (file1 == null || !file1.exists() || data == null || data.hasNoTags())
                {
                    LogHelper.warn("Data not found for " + player.getCommandSenderName() + ". Trying to load backup data.");
                    if (file2 != null && file2.exists())
                    {
                        try {
                            FileInputStream fileinputstream = new FileInputStream(file2);
                            data = CompressedStreamTools.readCompressed(fileinputstream);
                            fileinputstream.close();
                            save = true;
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }

                if (data != null)
                {
                    InventoryImplants inventory = new InventoryImplants(player);
                    inventory.readNBT(data);
                    playerImplants.put(player.getCommandSenderName(), inventory);
                    if (save)
                        savePlayerImplants(player, file1, file2);
                }
            } catch (Exception exception1)
            {
                LogHelper.fatal("Error loading implant inventory");
                exception1.printStackTrace();
            }
        }
    }

    public static void savePlayerImplants(EntityPlayer player, File file1, File file2)
    {
        if (player != null && !player.worldObj.isRemote)
        {
            try {
                if (file1 != null && file1.exists())
                {
                    try {
                        Files.copy(file1, file2);
                    } catch (Exception e)
                    {
                        LogHelper.error("Could not backup old implants file for player " + player.getCommandSenderName());
                    }
                }

                try {
                    if (file1 != null)
                    {
                        InventoryImplants inventory = getPlayerImplants(player);
                        NBTTagCompound data = new NBTTagCompound();
                        inventory.saveNBT(data);

                        FileOutputStream fileoutputstream = new FileOutputStream(file1);
                        CompressedStreamTools.writeCompressed(data,fileoutputstream);
                        fileoutputstream.close();

                    }
                } catch (Exception e)
                {
                    LogHelper.error("Could not save implants file for player " + player.getCommandSenderName());
                    e.printStackTrace();
                    if (file1.exists())
                    {
                        try {
                            file1.delete();
                        } catch (Exception e2)
                        {
                        }
                    }
                }
            } catch (Exception exception1)
            {
                LogHelper.fatal("Error saving implants inventory");
                exception1.printStackTrace();
            }
        }
    }
}

package com.goldengamer.vortex.utility;

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
}

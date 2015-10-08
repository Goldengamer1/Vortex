package com.goldengamer.vortex.events;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.goldengamer.vortex.container.base.InventoryImplants;
import com.goldengamer.vortex.handler.ConfigurationHandler;
import com.goldengamer.vortex.handler.EventNetworkHandler;
import com.goldengamer.vortex.utility.LogHelper;
import com.goldengamer.vortex.utility.PlayerHelper;
import com.goldengamer.vortex.utility.interfaces.IImplant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import com.google.common.io.Files;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/**
 * Created by golde on 07/10/2015.
 */
public class EventHandlerEntity
{
    // player directory
    private File playerDirectory;

    // hash containing game mode of all players
    private Map<String,Boolean> playerModes = new HashMap<String,Boolean>();

        @SubscribeEvent
        public void playerTick(PlayerEvent.LivingUpdateEvent event) {

            // player events
            if (event.entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.entity;

                if (ConfigurationHandler.isSplitSurvivalCreative()) {
                    // detect game mode changes
                    if (playerModes.containsKey(player.getCommandSenderName()) && (playerDirectory != null))
                    {
                        Boolean mode = playerModes.get(player.getCommandSenderName());
                        if (mode && !player.capabilities.isCreativeMode)
                        {
                            playerSaveDo(player, playerDirectory, true);
                            playerLoadDo(player, playerDirectory, false);
                        }
                        else if (!mode && player.capabilities.isCreativeMode)
                        {
                            playerSaveDo(player, playerDirectory, false);
                            playerLoadDo(player, playerDirectory, true);
                        }
                    }
                    playerModes.put(player.getCommandSenderName(), player.capabilities.isCreativeMode);
                }

                InventoryImplants implants = PlayerHelper.getPlayerImplants(player);
                for (int a = 0; a < implants.getSizeInventory(); a++) {
                    if (implants.getStackInSlot(a) != null
                            && implants.getStackInSlot(a).getItem() instanceof IImplant) {
                        ((IImplant) implants.getStackInSlot(a).getItem()).onWornTick(
                                implants.getStackInSlot(a), player);
                    }
                }

            }

        }
        @SubscribeEvent
        public void playerDeath(PlayerDropsEvent event) {
            //IF i want implant to drop on death enable the if

            //if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote && !event.entity.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
            //    PlayerHelper.getPlayerImplants(event.entityPlayer).dropItemsAt(event.drops, event.entityPlayer);
            //}

        }

        @SubscribeEvent
        public void playerLoad(PlayerEvent.LoadFromFile event) {
            playerLoadDo(event.entityPlayer, event.playerDirectory, event.entityPlayer.capabilities.isCreativeMode);
            playerDirectory = event.playerDirectory;
        }

        private void playerLoadDo(EntityPlayer player, File directory, Boolean gamemode) {
            PlayerHelper.clearPlayerImplants(player);

            File file1, file2;
            String fileName, fileNameBackup;
            if (gamemode || !ConfigurationHandler.isSplitSurvivalCreative()) {
                fileName = "impl";
                fileNameBackup = "implback";
            }
            else {
                fileName = "impls";
                fileNameBackup = "implsback";
            }

            // look for normal files first
            file1 = getPlayerFile(fileName, directory, player.getCommandSenderName());
            file2 = getPlayerFile(fileNameBackup, directory, player.getCommandSenderName());

            // look for uuid files when normal file missing
            if (!file1.exists()) {
                File filep = getPlayerFileUUID(fileName, directory, player.getGameProfile().getId().toString());
                if (filep.exists()) {
                    try {
                        Files.copy(filep, file1);
                        LogHelper.info("Using and converting UUID Implant savefile for " + player.getCommandSenderName());
                        filep.delete();
                        File fb = getPlayerFileUUID(fileNameBackup, directory, player.getGameProfile().getId().toString());
                        if (fb.exists()) fb.delete();
                    } catch (IOException e) {}
                } else {
                    File filet = getLegacyFileFromPlayer(player);
                    if (filet.exists()) {
                        try {
                            Files.copy(filet, file1);
                            LogHelper.info("Using pre MC 1.7.10 Implant savefile for " + player.getCommandSenderName());
                        } catch (IOException e) {}
                    }
                }
            }

            PlayerHelper.loadPlayerImplants(player, file1, file2);
            EventNetworkHandler.syncImplants(player);
        }

        public File getPlayerFile(String suffix, File playerDirectory, String playername)
        {
            if ("dat".equals(suffix)) throw new IllegalArgumentException("The suffix 'dat' is reserved");
            return new File(playerDirectory, playername+"."+suffix);
        }

        public File getPlayerFileUUID(String suffix, File playerDirectory, String playerUUID)
        {
            if ("dat".equals(suffix)) throw new IllegalArgumentException("The suffix 'dat' is reserved");
            return new File(playerDirectory, playerUUID+"."+suffix);
        }

        public static File getLegacyFileFromPlayer(EntityPlayer player)
        {
            try {
                File playersDirectory = new File(player.worldObj.getSaveHandler().getWorldDirectory(), "players");
                return new File(playersDirectory, player.getCommandSenderName() + ".impl");
            } catch (Exception e) { e.printStackTrace(); }
            return null;
        }

        @SubscribeEvent
        public void playerSave(PlayerEvent.SaveToFile event) {
            playerSaveDo(event.entityPlayer, event.playerDirectory, event.entityPlayer.capabilities.isCreativeMode);
        }

        private void playerSaveDo(EntityPlayer player, File directory, Boolean gamemode) {
            if (gamemode || !ConfigurationHandler.isSplitSurvivalCreative()) {
                PlayerHelper.savePlayerImplants(player, getPlayerFile("impl", directory, player.getCommandSenderName()), getPlayerFile("implback", directory, player.getCommandSenderName()));
            }
            else {
                PlayerHelper.savePlayerImplants(player, getPlayerFile("impls", directory, player.getCommandSenderName()), getPlayerFile("implsback", directory, player.getCommandSenderName()));
            }
        }

}

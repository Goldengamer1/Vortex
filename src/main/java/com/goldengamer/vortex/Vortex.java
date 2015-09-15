package com.goldengamer.vortex;

import com.goldengamer.vortex.client.handler.KeyInputEventHandler;
import com.goldengamer.vortex.handler.ConfigurationHandler;
import com.goldengamer.vortex.init.ModBlocks;
import com.goldengamer.vortex.init.ModItems;
import com.goldengamer.vortex.init.Recipes;
import com.goldengamer.vortex.proxy.IProxy;
import com.goldengamer.vortex.reference.Reference;
import com.goldengamer.vortex.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by gold on 14/09/2015.
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)

public class Vortex
{

    @Mod.Instance("vortex")
    public static Vortex instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

/**
* EVENT HANDLERS
*/

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)        /** e.g items ,blocks */
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        proxy.registerKeyBindings();

        ModItems.init();
        ModBlocks.init();

        LogHelper.info(("PreInit Complete!"));
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)        /** e.g guis , tile entitys ,recpies */
    {
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());

        Recipes.init();
        LogHelper.info(("Init Complete!"));
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)        /** thing that run after other mods */
    {



        LogHelper.info(("PostInit Complete!"));
    }




}

package com.goldengamer.vortex;

import com.goldengamer.vortex.client.handler.KeyInputEventHandler;
import com.goldengamer.vortex.handler.ConfigurationHandler;
import com.goldengamer.vortex.handler.CraftingHandler;
import com.goldengamer.vortex.handler.FuelHandler;
import com.goldengamer.vortex.init.*;
import com.goldengamer.vortex.network.PacketHandler;
import com.goldengamer.vortex.proxy.CommonProxy;
import com.goldengamer.vortex.reference.Reference;
import com.goldengamer.vortex.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by gold on 14/09/2015.
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)

public class Vortex
{

    @Mod.Instance(Reference.MOD_ID)
    public static Vortex instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

/**
* EVENT HANDLERS
*/

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)        /** e.g items ,blocks */
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        FMLCommonHandler.instance().bus().register(new CraftingHandler());

        PacketHandler.init();

        proxy.registerKeyBindings();

        ModItems.init();
        ModBlocks.init();

        LogHelper.info(("PreInit Complete!"));
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)        /** e.g guis , tile entitys ,recpies */
    {
        //TODO Comment the lines
        // Register the GUI Handler
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());

        Events.init();
        Recipes.init();
        proxy.registerRenderThings();
        TileEntities.init();

        GameRegistry.registerFuelHandler(new FuelHandler());
        LogHelper.info(("Init Complete!"));
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)        /** thing that run after other mods */
    {


        LogHelper.info(("PostInit Complete!"));
    }




}

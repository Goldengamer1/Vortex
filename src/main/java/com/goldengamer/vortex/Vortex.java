package com.goldengamer.vortex;

import com.goldengamer.vortex.proxy.IProxy;
import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by gold on 14/09/2015.
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)

public class Vortex {

    @Mod.Instance("vortex")
    public static Vortex instance;

    @SidedProxy(clientSide = "com.goldengamer.vortex.proxy.ClientProxy", serverSide = "com.goldengamer.vortex.proxy.ServerProxy")
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
    //e.g items ,blocks

    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
    //e.g guis , tile entitys ,recpies

    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
    //thing that run after other mods

    }




}

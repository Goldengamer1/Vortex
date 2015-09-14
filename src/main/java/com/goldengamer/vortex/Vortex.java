package com.goldengamer.vortex;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by gold on 14/09/2015.
 */

@Mod(modid = "vortex", name = "Vortexcraft", version = "1.7.10-0.01")

public class Vortex {

    @Mod.Instance("vortex")
    public static Vortex instance;

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

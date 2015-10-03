package com.goldengamer.vortex.init;

import com.goldengamer.events.HUDEvents;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by golde on 02/10/2015.
 */
public class Events
{

    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new HUDEvents());
    }
}

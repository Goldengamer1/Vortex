package com.goldengamer.vortex.init;

import com.goldengamer.vortex.events.HUDEvents;
import com.goldengamer.vortex.events.GuiEvents;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by golde on 02/10/2015.
 */
public class Events
{

    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new HUDEvents());
        MinecraftForge.EVENT_BUS.register(new GuiEvents());
    }
}

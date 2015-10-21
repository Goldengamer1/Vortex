package com.goldengamer.vortex.init;

import com.goldengamer.vortex.events.EventHandlerEntityImpl;
import com.goldengamer.vortex.events.HUDEvents;
import com.goldengamer.vortex.events.GuiEvents;
import com.goldengamer.vortex.levelHandler.ClassEventHandler;
import com.goldengamer.vortex.levelHandler.GuiLevelBar;
import com.goldengamer.vortex.levelHandler.GuiRageBar;
import net.minecraft.client.Minecraft;
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
        MinecraftForge.EVENT_BUS.register(new EventHandlerEntityImpl());

        MinecraftForge.EVENT_BUS.register(new ClassEventHandler());
        MinecraftForge.EVENT_BUS.register(new GuiRageBar());
        MinecraftForge.EVENT_BUS.register(new GuiLevelBar());

    }
}

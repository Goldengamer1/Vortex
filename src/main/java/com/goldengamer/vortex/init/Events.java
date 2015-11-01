package com.goldengamer.vortex.init;

import com.goldengamer.vortex.events.EventHandlerEntityImpl;
import com.goldengamer.vortex.levelHandler.Hud.HUDAbilitybar;
import com.goldengamer.vortex.events.GuiEvents;
import com.goldengamer.vortex.levelHandler.ClassEventHandler;
import com.goldengamer.vortex.levelHandler.Hud.GuiLevelBar;
import com.goldengamer.vortex.levelHandler.Hud.GuiRageBar;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by golde on 02/10/2015.
 */
public class Events
{

    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new HUDAbilitybar());
        MinecraftForge.EVENT_BUS.register(new GuiEvents());
        MinecraftForge.EVENT_BUS.register(new EventHandlerEntityImpl());

        MinecraftForge.EVENT_BUS.register(new ClassEventHandler());
        MinecraftForge.EVENT_BUS.register(new GuiRageBar());
        MinecraftForge.EVENT_BUS.register(new GuiLevelBar());

    }
}

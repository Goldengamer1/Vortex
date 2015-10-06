package com.goldengamer.vortex.client.handler;

import com.goldengamer.vortex.client.settings.Keybindings;
import com.goldengamer.vortex.reference.Key;
import com.goldengamer.vortex.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

/**
 * Created by golde on 15/09/2015.
 */
public class KeyInputEventHandler
{
    @SubscribeEvent
    private static Key onKeyPress()
    {
        if(Keybindings.ability1.isPressed()) {return Key.ABILITY1;}
        if(Keybindings.ability2.isPressed()) {return Key.ABILITY2;}
        if(Keybindings.ability3.isPressed()) {return Key.ABILITY3;}
        if(Keybindings.ability4.isPressed()) {return Key.ABILITY4;}
        if(Keybindings.ability5.isPressed()) {return Key.ABILITY5;}
        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        //Debug tool
        //LogHelper.info(onKeyPress());
    }

}

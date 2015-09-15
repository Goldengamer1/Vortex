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
    private static Key getPressedKeybinding()
    {
        if(Keybindings.charge.isPressed())
        {
            return Key.CHARGE;
        }

        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handlKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        //Debug tool
       // LogHelper.info(getPressedKeybinding());
    }

}

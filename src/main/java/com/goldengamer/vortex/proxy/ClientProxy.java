package com.goldengamer.vortex.proxy;

import com.goldengamer.vortex.client.settings.Keybindings;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

/**
 * Created by golde on 14/09/2015.
 */
public class ClientProxy extends CommonProxy
{

    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(Keybindings.charge);
    }

}

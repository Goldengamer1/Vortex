package com.goldengamer.vortex.proxy;

import com.goldengamer.vortex.client.settings.Keybindings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;



/**
 * Created by golde on 14/09/2015.
 */
public class ClientProxy extends CommonProxy
{

    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx)
    {
        return  (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
    }


    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(Keybindings.charge);
    }
}

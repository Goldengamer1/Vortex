package com.goldengamer.vortex.proxy;

import com.goldengamer.vortex.client.renderer.item.ItemRendererHardtofindiumSword;
import com.goldengamer.vortex.client.renderer.tileentity.TileEntityBdRenderer;
import com.goldengamer.vortex.client.settings.Keybindings;
import com.goldengamer.vortex.init.ModItems;
import com.goldengamer.vortex.tileentity.TileEntityBd;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.MinecraftForgeClient;

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

    public void registerRenderThings()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBd.class, new TileEntityBdRenderer());

        MinecraftForgeClient.registerItemRenderer(ModItems.HARD_TO_FINDIUM_SWORD, new ItemRendererHardtofindiumSword());
    }

}

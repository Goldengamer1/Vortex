package com.goldengamer.vortex.proxy;

import com.goldengamer.vortex.client.renderer.item.ItemRendererHardtofindiumSword;
import com.goldengamer.vortex.client.renderer.tileentity.TileEntityBdRenderer;
import com.goldengamer.vortex.client.settings.Keybindings;
import com.goldengamer.vortex.init.ModItems;
import com.goldengamer.vortex.tileentity.TileEntityBd;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created by golde on 14/09/2015.
 */
public class ClientProxy extends CommonProxy
{

    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(Keybindings.ability1);
        ClientRegistry.registerKeyBinding(Keybindings.ability2);
        ClientRegistry.registerKeyBinding(Keybindings.ability3);
        ClientRegistry.registerKeyBinding(Keybindings.ability4);
        ClientRegistry.registerKeyBinding(Keybindings.ability5);

    }

    public void registerRenderThings()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBd.class, new TileEntityBdRenderer());

        MinecraftForgeClient.registerItemRenderer(ModItems.HARD_TO_FINDIUM_SWORD, new ItemRendererHardtofindiumSword());
    }

    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }
}

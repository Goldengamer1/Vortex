package com.goldengamer.vortex.proxy;

import com.goldengamer.vortex.client.gui.GuiImplantInv;
import com.goldengamer.vortex.client.gui.inventory.GuiAbilityChanger;
import com.goldengamer.vortex.client.gui.inventory.GuiSurvivalistFurnace;
import com.goldengamer.vortex.client.renderer.item.ItemRendererHardtofindiumSword;
import com.goldengamer.vortex.client.renderer.tileentity.TileEntityBdRenderer;
import com.goldengamer.vortex.client.settings.Keybindings;
import com.goldengamer.vortex.init.ModItems;
import com.goldengamer.vortex.tileentity.TileEntityAbilityChanger;
import com.goldengamer.vortex.tileentity.TileEntityBd;
import com.goldengamer.vortex.tileentity.TileEntitySurvivalistFurnace;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
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

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case 0:
                return new GuiSurvivalistFurnace(player.inventory, (TileEntitySurvivalistFurnace) world.getTileEntity(x, y, z));
            case 1:
                return new GuiImplantInv(player);
            case 2:
                //return new GuiAbilityChanger(player.inventory, (TileEntityAbilityChanger) world.getTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
    }
}

package com.goldengamer.vortex.proxy;

import com.goldengamer.vortex.container.ContainerAbilityChanger;
import com.goldengamer.vortex.container.ContainerImplantInv;
import com.goldengamer.vortex.container.ContainerSurvivalistFurnace;
import com.goldengamer.vortex.tileentity.TileEntityAbilityChanger;
import com.goldengamer.vortex.tileentity.TileEntitySurvivalistFurnace;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by golde on 14/09/2015.
 */
public class CommonProxy implements IGuiHandler
{
    //@Override
    public void registerKeyBindings()
    {
        //NOOP (noting)
    }

    public void registerRenderThings()
    {
        //NOOP (noting)
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case 0:
                return new ContainerSurvivalistFurnace(player.inventory, (TileEntitySurvivalistFurnace) world.getTileEntity(x, y, z));
            case 1:
                return new ContainerImplantInv(player.inventory, !world.isRemote, player);
            case 2:
                return new ContainerAbilityChanger(player.inventory, world,(TileEntityAbilityChanger) world.getTileEntity(x, y, z), player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    public World getClientWorld() {
        return null;
    }

     // Returns a side-appropriate EntityPlayer for use during message handling
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity;
    }
}
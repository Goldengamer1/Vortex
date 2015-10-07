package com.goldengamer.vortex.proxy;

import com.goldengamer.vortex.container.ContainerImplantInv;
import com.goldengamer.vortex.container.ContainerSurvivalistFurnace;
import com.goldengamer.vortex.tileentity.TileEntitySurvivalistFurnace;
import cpw.mods.fml.common.network.IGuiHandler;
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
}
package com.goldengamer.vortex.handler;

import com.goldengamer.vortex.client.gui.inventory.GuiSurvivalistFurnace;
import com.goldengamer.vortex.inventory.ContainerSurvivalistFurnace;
import com.goldengamer.vortex.tileentity.TileEntitySurvivalistFurnace;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by golde on 24/09/2015.
 */
public class GuiHandler implements IGuiHandler
{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {

        if(ID == 0)
        {
            TileEntitySurvivalistFurnace tileEntitySurvivalistFurnace = (TileEntitySurvivalistFurnace) world.getTileEntity(x, y, z);
            return new ContainerSurvivalistFurnace(player.inventory, tileEntitySurvivalistFurnace);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {

//TODO Chnage id
        if(ID == 0)
        {
            TileEntitySurvivalistFurnace tileEntitySurvivalistFurnace = (TileEntitySurvivalistFurnace) world.getTileEntity(x, y, z);
            return new GuiSurvivalistFurnace(player.inventory, tileEntitySurvivalistFurnace);
        }
        //else if (ID == 1)
        //{
        //}

        return null;
    }
}

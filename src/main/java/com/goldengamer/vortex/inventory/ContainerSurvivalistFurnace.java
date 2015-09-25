package com.goldengamer.vortex.inventory;

import com.goldengamer.vortex.tileentity.TileEntitySurvivalistFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by golde on 24/09/2015.
 */
public class ContainerSurvivalistFurnace extends Container
{
    private TileEntitySurvivalistFurnace survivalistFurnace;

    public ContainerSurvivalistFurnace(InventoryPlayer inventoryPlayer, TileEntitySurvivalistFurnace tileEntity)
    {
        this.survivalistFurnace = tileEntity;
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return false;
    }
}

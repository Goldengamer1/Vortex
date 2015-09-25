package com.goldengamer.vortex.inventory;

import com.goldengamer.vortex.tileentity.TileEntitySurvivalistFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

/**
 * Created by golde on 24/09/2015.
 */
public class ContainerSurvivalistFurnace extends Container
{
    private TileEntitySurvivalistFurnace survivalistFurnace;

    public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastCookTime;

    public ContainerSurvivalistFurnace(InventoryPlayer inventoryPlayer, TileEntitySurvivalistFurnace tileEntity)
    {
        this.survivalistFurnace = tileEntity;

        this.addSlotToContainer(new Slot(tileEntity, 0, 56, 35));
        this.addSlotToContainer(new Slot(tileEntity, 1, 8, 62));
        this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntity, 2, 116, 35));

        for(int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i*9 + 9, 8 + j*18, 94 + i *18));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i*18, 142));
        }

    }

    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.survivalistFurnace.cookTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.survivalistFurnace.burnTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.survivalistFurnace.currentItemBurnTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); i++)
        {
            ICrafting iCrafting = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.survivalistFurnace.cookTime)
            {
                iCrafting.sendProgressBarUpdate(this, 0, this.survivalistFurnace.cookTime);
            }
            if (this.lastBurnTime != this.survivalistFurnace.burnTime)
            {
                iCrafting.sendProgressBarUpdate(this, 1, this.survivalistFurnace.burnTime);
            }
            if (this.lastCurrentItemBurnTime != this.survivalistFurnace.currentItemBurnTime)
            {
                iCrafting.sendProgressBarUpdate(this, 2, this.survivalistFurnace.currentItemBurnTime);
            }
        }
        this.lastCookTime = this.survivalistFurnace.cookTime;
        this.lastBurnTime = this.survivalistFurnace.burnTime;
        this.lastCurrentItemBurnTime = this.survivalistFurnace.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newValue)
    {


    }




    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }
}

package com.goldengamer.vortex.container;

import com.goldengamer.vortex.tileentity.TileEntitySurvivalistFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

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
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
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
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.survivalistFurnace.cookTime = par2;
        }

        if (par1 == 1)
        {
            this.survivalistFurnace.burnTime = par2;
        }

        if (par1 == 2)
        {
            this.survivalistFurnace.currentItemBurnTime = par2;
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (i == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (i != 1 && i != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntitySurvivalistFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (i >= 3 && i < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (i >= 30 && i < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(entityPlayer, itemstack1);
        }

        return itemstack;
    }



    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return this.survivalistFurnace.isUseableByPlayer(var1);
    }
}

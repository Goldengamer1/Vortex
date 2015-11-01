package com.goldengamer.vortex.tileentity;

import com.goldengamer.vortex.reference.Reference;
import com.goldengamer.vortex.utility.interfaces.IBindable;
import com.goldengamer.vortex.utility.interfaces.IImplant;
import com.goldengamer.vortex.utility.interfaces.implantClasses.IWarrior;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by golde on 21/10/2015.
 */
public class TileEntityAbilityChanger extends TileEntity implements IInventory
{
    private ItemStack[] slots = new ItemStack[1];
    private String localizedName;

    public int getSizeInventory()
    {
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int var1)
    {
        return this.slots[var1];
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if(this.slots[var1] != null)
        {
            ItemStack itemStack;

            if (this.slots[var1].stackSize <= var2)
            {
                itemStack = this.slots[var1];
                this.slots[var1] = null;
                return itemStack;
            }else{
                itemStack = this.slots[var1].splitStack(var2);

                if (this.slots[var1].stackSize == 0)
                {
                    this.slots[var1] = null;
                }

                return itemStack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {

        if (this.slots[var1] != null)
        {
            ItemStack itemStack = this.slots[var1];
            this.slots[var1] = null;
            return itemStack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack itemStack) {
        this.slots[var1] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() { return "container.abilityChanger"; }

    @Override
    public boolean hasCustomInventoryName() { return false; }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        NBTTagList list = new NBTTagList();

        for (int i = 0; i < getSizeInventory(); i++)
        {
            ItemStack itemStack = getStackInSlot(i);

            if (itemStack != null)
            {
                NBTTagCompound item = new NBTTagCompound();

                item.setByte("Slot", (byte) i);
                itemStack.writeToNBT(item);
                list.appendTag(item);
            }
        }
        nbt.setTag("AbilityChanger", list);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("AbilityChanger", 10);

        for (int i = 0; i < list.tagCount(); i++)
        {
            NBTTagCompound item = (NBTTagCompound) list.getCompoundTagAt(i);
            int slot = item.getByte("Slot");

            if (slot >= 0 && slot < getSizeInventory())
            {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : p_70300_1_.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {

        return true;
    }
}

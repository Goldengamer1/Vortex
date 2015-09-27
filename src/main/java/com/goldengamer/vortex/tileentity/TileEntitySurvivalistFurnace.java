package com.goldengamer.vortex.tileentity;

import com.goldengamer.vortex.block.BlockSurvivalistFurnace;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by golde on 24/09/2015.
 */
public class TileEntitySurvivalistFurnace extends TileEntity implements ISidedInventory
{
    private String localizedName;

    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_bottom = new int[]{2};
    private static final int[] slots_side = new int[]{1};

    private ItemStack[] slots = new ItemStack[3];

    public int furnaceSpeed = 150;
    public int burnTime;
    public int currentItemBurnTime;
    public int cookTime;

    public void setGuiDisplayName(String displayName)
    {
        this.localizedName = displayName;
    }

    public String getInventoryName()
    {
        //TODO MOVE "container.survivalistFurnace" to lang
        return this.hasCustomInventoryName() ? this.localizedName : "container.survivalistFurnace";

    }

    public boolean hasCustomInventoryName()
    {
        return this.localizedName != null && this.localizedName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord +0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {}
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack)
    {
        return i == 2 ? false : (i == 1 ? isItemFuel(itemStack) : true);
    }

    public static boolean isItemFuel(ItemStack itemStack)
    {
        return getItemBurnTime(itemStack) > 0;
    }

    private static int getItemBurnTime(ItemStack itemStack)
    {
        if (itemStack == null)
        {
            return 0;
        }else {
            Item item = itemStack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);
                if (block == Blocks.sapling) return 100;
                if (block == Blocks.coal_block) return 14400;
            }
            if (item == Items.coal) return 1600;
            if (item == Items.stick) return 100;
            if (item == Items.lava_bucket) return 20000;
            if (item == Items.blaze_rod) return 2400;
            if (item == Items.gunpowder) return 1600;
            //TODO make it so when a gunpowder is used at random make a expload sound and particals

        }
        return GameRegistry.getFuelValue(itemStack);
    }

    public boolean isBurning()
    {
        return this.burnTime > 0;
    }

    public void updateEntity()
    {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;

        if (this.isBurning())
        {
            this.burnTime--;
        }
        if (!this.worldObj.isRemote)
        {
            if(this.burnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[1]);

                if (this.isBurning())
                {
                    flag1 = true;

                    if (this.slots[1] != null)
                    {
                        this.slots[1].stackSize--;

                        if (this.slots[1].stackSize == 0)
                        {
                            this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
                        }
                    }
                }
            }
            if (this.isBurning() && this.canSmelt())
            {
                this.cookTime++;
                if (this.cookTime == this.furnaceSpeed)
                {
                    this.cookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
                    }else{
                    this.cookTime = 0;
                }

                if (flag != this.isBurning())
                {
                    flag1 = true;
                    BlockSurvivalistFurnace.updateSurvivalistFurnaceBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if (flag1){
            this.markDirty();
        }
    }

    public boolean canSmelt()
    {
        if (this.slots[0] == null)
        {
            return false;
        }else {
            ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);

            if (itemStack == null) return false;
            if (this.slots[2] == null) return true;
            if (!this.slots[2].isItemEqual(itemStack)) return false;

            int result = this.slots[2].stackSize + itemStack.stackSize;

            return (result <= getInventoryStackLimit() && result <= itemStack.getMaxStackSize());
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);

        if (this.slots[2] == null)
        {
            this.slots[2] = itemStack.copy();
        }else  if (this.slots[2].isItemEqual(itemStack))
        {
            this.slots[2].stackSize += itemStack.stackSize;
        }
        this.slots[0].stackSize--;

        if (this.slots[0].stackSize <= 0)
        {
            this.slots[0] = null;
            }
        }
    }


    public int getSizeInventory()
    {
        return  this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
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
    public int[] getAccessibleSlotsFromSide(int var1) {
        return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_side);
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, int j) {
        return this.isItemValidForSlot(i, itemStack);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int j) {
        return j != 0 || i != 1 || itemStack.getItem() == Items.bucket;
    }

    public int getBurnTimeRemainingScaled(int i)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = this.furnaceSpeed;
        }
        return this.burnTime * i / this.currentItemBurnTime;
    }

    public int getCookProgressScaled(int i)
    {
        return this.cookTime * i / this.furnaceSpeed;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Item", 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < list.tagCount();  i++)
        {
            NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
            byte b = compound.getByte("Slot");

            if (b >= 0 && b < this.slots.length)
            {
                this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
            }
        }

        this.burnTime = (int)nbt.getShort("BurnTime");
        this.cookTime = (int)nbt.getShort("CookTime");
        this.currentItemBurnTime = (int)nbt.getShort("CurrentItemBurnTime");

        if (nbt.hasKey("CustomName"))
        {
            this.localizedName = nbt.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setShort("BurnTime", (short) this.burnTime);
        nbt.setShort("CookTime", (short)this.cookTime);
        nbt.setShort("CurrentItemBurnTime", (short)this.currentItemBurnTime);

        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.slots.length; i++);
        {
            if (this.slots[i] != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte)i);
                this.slots[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }

        nbt.setTag("Item", list);

        if (this.hasCustomInventoryName())
        {
            nbt.setString("CustomName", this.localizedName);
        }
    }
}

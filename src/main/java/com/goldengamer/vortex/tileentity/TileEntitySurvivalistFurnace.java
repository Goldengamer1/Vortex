package com.goldengamer.vortex.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
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

    public int furnaceSpeed;
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
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
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
        }else{
            Item item = itemStack.getItem();

            if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (item == Items.coal) return 1600;
                if (item == Items.stick) return 100;
                if (item == Items.lava_bucket) return 20000;
                if (item == Items.blaze_rod) return 2400;
                if (item == Items.gunpowder) return 1600;
                if (block == Blocks.sapling) return 100;
                if (block == Blocks.coal_block) return 14400;

                return GameRegistry.getFuelValue(itemStack);

            }
        }
        return 0;
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
                            this.slots[1] == this.slots[1].getItem().getContainerItem(this.slots[1]);
                        }
                    }
                }
            }
        }
    }

    public int getSizeInventory()
    {
        return  this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack itemStack) {

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
}

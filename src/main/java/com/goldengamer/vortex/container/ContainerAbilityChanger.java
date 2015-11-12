package com.goldengamer.vortex.container;

import com.goldengamer.vortex.container.base.InventoryImplants;
import com.goldengamer.vortex.container.base.SlotRestricted;
import com.goldengamer.vortex.reference.ImplantType;
import com.goldengamer.vortex.tileentity.TileEntityAbilityChanger;
import com.goldengamer.vortex.utility.interfaces.IImplant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by golde on 21/10/2015.
 */
public class ContainerAbilityChanger extends Container
{
    private TileEntityAbilityChanger tileAbilityChanger;
    private World worldObj;
    public InventoryPlayer invPlayer;
    private final EntityPlayer thePlayer;


    public ContainerAbilityChanger(InventoryPlayer invPlayer, World world,TileEntityAbilityChanger abilityChanger, EntityPlayer player)
    {
        this.tileAbilityChanger = abilityChanger;
        worldObj = world;
        thePlayer = player;

        int i;
        int j;

        //Implant
        //this.addSlotToContainer(new SlotRestricted(this.implant, 0, 11, 195, thePlayer, ImplantType.CLASS_IMPLANT));

        this.addSlotToContainer(new Slot(abilityChanger, 0, 107, 9));


        //Inventory
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(invPlayer, i, 36 + i * 18, 195));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if ((slot != null) && (slot.getHasStack()))
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < 0)
            {
                if (!mergeItemStack(itemstack1, 0, 0+9, false))
                    return null;
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if(itemstack.getItem() instanceof IImplant && ((IImplant)itemstack.getItem()).getImplantType(itemstack)!=null)
            {
                //checks to see if the item is bound
                if (!(itemstack.getTagCompound() == null))
                {
                    if (!itemstack.getTagCompound().getString("ownerName").equals(""))
                    {
                        if (itemstack.getTagCompound().getString("ownerName").equals(thePlayer.getCommandSenderName()))
                        {
                            IImplant implantItem = (IImplant) itemstack.getItem();
                            if (implantItem.getImplantType(itemstack) == ImplantType.CLASS_IMPLANT && implantItem.canEquip(itemstack, this.thePlayer) && !((Slot) this.inventorySlots.get(0)).getHasStack()) {
                                if (!mergeItemStack(itemstack1, 0, 0 + 1, false))
                                    return null;
                            }
                        }
                    }
                }
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileAbilityChanger.isUseableByPlayer(player);
    }

    //@Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);
/*
        if (!this.worldObj.isRemote)
        {
            for (int i = 0; i < 0; ++i)
            {
               ItemStack itemStack = invPlayer.getStackInSlot(10);

                if (itemStack != null)
                {
                    playerIn.dropPlayerItemWithRandomChoice(itemStack, false);
                }
            }
        }
        */
    }
}

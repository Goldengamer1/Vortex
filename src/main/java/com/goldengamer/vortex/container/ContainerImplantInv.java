package com.goldengamer.vortex.container;

import com.goldengamer.vortex.container.base.InventoryImplants;
import com.goldengamer.vortex.container.base.SlotRestricted;
import com.goldengamer.vortex.reference.ImplantType;
import com.goldengamer.vortex.utility.LogHelper;
import com.goldengamer.vortex.utility.PlayerHelper;
import com.goldengamer.vortex.utility.interfaces.IImplant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import sun.rmi.runtime.Log;

/**
 * Created by golde on 06/10/2015.
 */
public class ContainerImplantInv extends Container
{
    public InventoryImplants implant;

    public boolean isLocalWorld;
    private final EntityPlayer thePlayer;

    public int nonInventorySlots;
    public int playerInventorySlots;
    public int playerHotbarSlots;

    int[] implantSlot={-1};
    int[] vanillaArmor={-1,-1,-1,-1};

    public ContainerImplantInv(InventoryPlayer inventory, boolean par2, EntityPlayer player) {

        this.isLocalWorld = par2;
        this.thePlayer = player;
        implant = new InventoryImplants(player);
        implant.setEventHandler(this);
        if (!player.worldObj.isRemote) {
            implant.stackList = PlayerHelper.getPlayerImplants(player).stackList;
        }
//TODO fix shift clicking
        int i;
        int j;

        //IMPLANT SLOTS
        implantSlot[0]=addSlot(new SlotRestricted(this.implant, 0, 8, 8, thePlayer, ImplantType.CLASS_IMPLANT));
        nonInventorySlots+=(implantSlot[0]>=0?1:0);

        //PLAYER INVENTORY
        playerInventorySlots=0;
        playerHotbarSlots=0;
        for (i = 0; i < 3; ++i)
            for (j = 0; j < 9; ++j)
                if(this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18))>=0)
                    playerInventorySlots++;
        for (i = 0; i < 9; ++i)
            if(this.addSlot(new Slot(inventory, i, 8 + i * 18, 142))>=0)
                playerHotbarSlots++;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public void putStacksInSlots(ItemStack[] p_75131_1_) {
        implant.blockEvents=true;
        super.putStacksInSlots(p_75131_1_);
    }

    int addSlot(Slot slot)
    {
        return addSlotToContainer(slot).slotNumber;
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        if (!player.worldObj.isRemote) {
            PlayerHelper.setPlayerImplants(player, implant);
        }
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if ((slot != null) && (slot.getHasStack()))
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < nonInventorySlots)
            {
                if (!mergeItemStack(itemstack1, nonInventorySlots, nonInventorySlots+playerInventorySlots+playerHotbarSlots, false))
                    return null;
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if(itemstack.getItem() instanceof IImplant && ((IImplant)itemstack.getItem()).getImplantType(itemstack)!=null)
            {
                IImplant implantItem = (IImplant)itemstack.getItem();
                if( implantItem.getImplantType(itemstack)==ImplantType.CLASS_IMPLANT && implantItem.canEquip(itemstack, this.thePlayer) && !((Slot)this.inventorySlots.get(implantSlot[0])).getHasStack() )
                {
                    if (!mergeItemStack(itemstack1, implantSlot[0], implantSlot[0] + 1, false))
                        return null;
                }
            }
            else if((par2 >= nonInventorySlots) && (par2 < nonInventorySlots+playerInventorySlots))
            {
                if (!mergeItemStack(itemstack1, nonInventorySlots+playerInventorySlots, nonInventorySlots+playerInventorySlots+playerHotbarSlots, false))
                    return null;
            }
            else if ((par2 >= nonInventorySlots+playerInventorySlots) && (par2 < nonInventorySlots+playerInventorySlots+playerHotbarSlots))
            {
                if (!mergeItemStack(itemstack1, nonInventorySlots, nonInventorySlots+playerInventorySlots, false))
                    return null;
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

    //private void unequipImplant(ItemStack stack)
    //{
    //    if ((stack.getItem() instanceof IImplant))
    //        ((IImplant)stack.getItem()).onUnequipped(stack, this.thePlayer);
    //}
}

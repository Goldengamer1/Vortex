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
import sun.rmi.runtime.Log;

/**
 * Created by golde on 06/10/2015.
 */
public class ContainerImplantInv extends Container
{
    public InventoryImplants implant;

    public boolean isLocalWorld;
    private final EntityPlayer thePlayer;

    public ContainerImplantInv(InventoryPlayer inventory, boolean par2, EntityPlayer player) {

        this.isLocalWorld = par2;
        this.thePlayer = player;
        implant = new InventoryImplants(player);
        implant.setEventHandler(this);
        if (!player.worldObj.isRemote) {
            implant.stackList = PlayerHelper.getPlayerImplants(player).stackList;
        }
//TODO fix shift clicking
        int o;
        //int j;
        //ARMOR SLOTS
        for (o = 0; o < 4; ++o)
        {
            final int k = o;
            this.addSlotToContainer(new Slot(inventory, inventory.getSizeInventory() - 1 - o, 8, 8 + o * 18)
            {
                @Override
                public int getSlotStackLimit() { return 1; }
                @Override
                public boolean isItemValid(ItemStack par1ItemStack)
                {
                    if (par1ItemStack == null) return false;
                    return par1ItemStack.getItem().isValidArmor(par1ItemStack, k, thePlayer);
                }
            });
        }

        //IMPLANT SLOTS
        addSlot(new SlotRestricted(implant, 0, 80, 8, thePlayer, ImplantType.CLASS_IMPLANT));

        //PLAYER INVENTORY
        for(int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }


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

}

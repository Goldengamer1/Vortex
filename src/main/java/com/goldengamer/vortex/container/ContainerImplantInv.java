package com.goldengamer.vortex.container;

import com.goldengamer.vortex.container.base.InventoryImplants;
import com.goldengamer.vortex.container.base.SlotRestricted;
import com.goldengamer.vortex.reference.ImplantType;
import com.goldengamer.vortex.utility.PlayerHelper;
import com.goldengamer.vortex.utility.interfaces.IImplant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * Created by golde on 06/10/2015.
 */
public class ContainerImplantInv extends Container
{
    public InventoryImplants implant;
    int[] vanillaArmor={-1,-1,-1,-1};
    EntityPlayer player = null;

    public ContainerImplantInv(InventoryPlayer inventory) {

        implant = new InventoryImplants(player);
        implant.setEventHandler(this);
        //if (!player.worldObj.isRemote)
        //{
        //    implant.stackList = PlayerHelper.getPlayerImplants(player).stackList;
        //}

        //ARMOR SLOTS
        vanillaArmor[0] = addSlot(new SlotRestricted(inventory, inventory.getSizeInventory()-1-0,  8, 8, player, ImplantType.VANILLA_HELM));
        vanillaArmor[1] = addSlot(new SlotRestricted(inventory, inventory.getSizeInventory()-1-1,  8, 26, player, ImplantType.VANILLA_CHEST));
        vanillaArmor[2] = addSlot(new SlotRestricted(inventory, inventory.getSizeInventory()-1-2,  8, 44, player, ImplantType.VANILLA_LEGS));
        vanillaArmor[3] = addSlot(new SlotRestricted(inventory, inventory.getSizeInventory()-1-3,  8, 62, player, ImplantType.VANILLA_BOOTS));

        //IMPLANT SLOTS
        addSlot(new SlotRestricted(implant, 0, 80, 8, player, ImplantType.CLASS_IMPLANT));

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
        return addSlotToContainer(slot)/* instanceof SlotNull?-1:slot*/.slotNumber;
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {

        if (!player.worldObj.isRemote) {
            PlayerHelper.setPlayerImplants(player, implant);
        }
    }

}

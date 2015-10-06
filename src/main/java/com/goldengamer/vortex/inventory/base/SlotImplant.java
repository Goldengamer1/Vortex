package com.goldengamer.vortex.inventory.base;

import com.goldengamer.vortex.reference.ImplantType;
import com.goldengamer.vortex.utility.interfaces.IImplant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by golde on 05/10/2015.
 */
public class SlotImplant extends Slot
{
    ImplantType type;

    public SlotImplant(IInventory iInventory, ImplantType type, int i, int j, int k)
    {
        super(iInventory, i, j, k);
        this.type = type;
    }


    //@Override
    //public boolean isItemValid(ItemStack stack)
    //{
    //    return stack!=null && stack.getItem() !=null && stack.getItem() instanceof IImplant && ((IImplant)stack.getItem()).getImplantType(stack)==this.type && ((IImplant)stack.getItem()).canEquip(stack, ((InventoryImplant)this.inventory).player.get());
    //}


    @Override
    public boolean canTakeStack(EntityPlayer player) {
        return this.getStack()!=null &&
                ((IImplant)this.getStack().getItem()).canUnequip(this.getStack(), player);
    }


    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }
}

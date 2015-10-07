package com.goldengamer.vortex.container.base;

import com.goldengamer.vortex.reference.ImplantType;
import com.goldengamer.vortex.utility.interfaces.IImplant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by golde on 06/10/2015.
 */
public class SlotRestricted extends Slot
{
    public int slotLimit = 1;
    public ImplantType type;
    EntityPlayer player;

    public SlotRestricted(IInventory inv, int id, int x, int y, EntityPlayer player, ImplantType type) {
        super(inv, id, x, y);
        this.player = player;
        this.type = type;
    }

    //Add a (case NAME:) to add more
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        if (stack == null || stack.getItem() == null)
            return false;
        switch (this.type)
        {
            case VANILLA_HELM:
            case VANILLA_CHEST:
            case VANILLA_LEGS:
            case VANILLA_BOOTS:
                return stack.getItem().isValidArmor(stack, this.type.ordinal(), player);
            case CLASS_IMPLANT:
                return stack!=null && stack.getItem() !=null && stack.getItem() instanceof IImplant && ((IImplant)stack.getItem()).getImplantType(stack)==this.type && ((IImplant)stack.getItem()).canEquip(stack, ((InventoryImplants)this.inventory).player.get());
            default:
                    return false;
        }
    }

    public boolean isImplantSlot()
    {
        return this.type==ImplantType.CLASS_IMPLANT;
    }

    @Override
    public int getSlotStackLimit()
    {
        return slotLimit;
    }


}

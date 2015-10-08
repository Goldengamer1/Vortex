package com.goldengamer.vortex.item.equipment.implant;

import com.goldengamer.vortex.item.base.ItemImplantVortex;
import com.goldengamer.vortex.reference.ImplantType;
import com.goldengamer.vortex.utility.interfaces.IImplant;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * Created by golde on 08/10/2015.
 */
public class WarriorImplant extends ItemImplantVortex
{
    public WarriorImplant()
    {
        super();
        this.setUnlocalizedName("WARRIOR_IMPLANT");
    }

    @Override
    public ImplantType getImplantType(ItemStack itemstack) {
        return ImplantType.CLASS_IMPLANT;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {

    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {

    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {

    }
}

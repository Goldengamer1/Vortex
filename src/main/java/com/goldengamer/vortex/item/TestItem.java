package com.goldengamer.vortex.item;

import com.goldengamer.vortex.item.base.ItemVortex;
import com.goldengamer.vortex.reference.ImplantType;
import com.goldengamer.vortex.utility.interfaces.IImplant;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * Created by golde on 14/09/2015.
 */
public class TestItem extends ItemVortex implements IImplant
{
    public TestItem()
    {
        super();
        this.setUnlocalizedName("TEST_ITEM");
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

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }
}

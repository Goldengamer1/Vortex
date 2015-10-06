package com.goldengamer.vortex.utility.interfaces;

import com.goldengamer.vortex.reference.ImplantType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * Created by golde on 05/10/2015.
 */
public interface IImplant
{
    public ImplantType getImplantType(ItemStack itemstack);

    public void onWornTick(ItemStack itemstack, EntityLivingBase player);

    public void onEquipped(ItemStack itemstack, EntityLivingBase player);

    public void onUnequipped(ItemStack itemstack, EntityLivingBase player);

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player);

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player);
}

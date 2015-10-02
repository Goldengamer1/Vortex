package com.goldengamer.vortex.item.equipment.tool;

import com.goldengamer.vortex.item.base.ItemSwordVortex;
import com.goldengamer.vortex.reference.Material;
import com.goldengamer.vortex.utility.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by golde on 18/09/2015.
 */
public class HardToFindiumSword extends ItemSwordVortex
{
    public boolean isSeleceted = false;

    public HardToFindiumSword()
    {
        super(Material.Tools.HardToFindIum);
        this.setUnlocalizedName("HARDTOFINDIUM_SWORD");
    }

    //dose stuff if item is in hand
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {
        super.onUpdate(stack, world, entity, par4, par5);
        {
            EntityPlayer player = (EntityPlayer) entity;
            ItemStack equipped = player.getCurrentEquippedItem();
            //TODO FIX IF ITEM IS DROPPED HUD GOSES AWAY
            if (equipped == stack)
            {
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 100, 6));
                isSeleceted = true;
            }else{
                isSeleceted = false;
            }
        }
    }
}

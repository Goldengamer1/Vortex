package com.goldengamer.vortex.item.equipment.tool;

import com.goldengamer.vortex.init.ModItems;
import com.goldengamer.vortex.item.base.ItemBound;
import com.goldengamer.vortex.utility.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by golde on 11/11/2015.
 */
public class PokingStick extends ItemBound
{
    public int cooldown = 0;

    public PokingStick()
    {
        super();
        this.setUnlocalizedName("POKING_STICK");
        this.maxStackSize = 1;
        cooldown = 0;
    }

    @Override
    public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
    {
        super.onUpdate(item, world, entity, par4, par5);

        if (entity instanceof EntityPlayer && cooldown > 0) {
            cooldown--;
        }
    }
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {

        if (entity instanceof EntityPlayer && cooldown <= 0)
        {
            LogHelper.info(entity);
            entity.moveEntity(0D, 30D, 0D);
            ((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100, 11));
            ((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.resistance.getId(), 100, 11));
            cooldown = 1200;
        }
        if (entity instanceof EntityLivingBase && cooldown > 0)
        {
            LogHelper.info(cooldown);
        }

        return true;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {

        if (!(par1ItemStack.getTagCompound() == null))
        {
            if (!par1ItemStack.getTagCompound().getString("ownerName").equals(""))
            {
                par3List.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.vortex:owner") + EnumChatFormatting.GRAY + " " + par1ItemStack.getTagCompound().getString("ownerName"));
                if (cooldown <= 0)
                {
                    par3List.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tooltip.vortex:desc5"));
                    par3List.add(EnumChatFormatting.UNDERLINE + StatCollector.translateToLocal("tooltip.vortex:desc7"));
                }
                else {
                    par3List.add(EnumChatFormatting.RED + StatCollector.translateToLocal("tooltip.vortex:desc6"));
                }
            }else{
                par3List.add(StatCollector.translateToLocal("tooltip.vortex:desc1"));
                par3List.add(StatCollector.translateToLocal("tooltip.vortex:desc2"));
            }
        }
    }

}

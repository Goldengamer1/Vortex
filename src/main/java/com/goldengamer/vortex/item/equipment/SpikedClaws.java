package com.goldengamer.vortex.item.equipment;

import com.goldengamer.vortex.item.base.ItemBound;
import com.goldengamer.vortex.item.base.ItemImplantVortex;
import com.goldengamer.vortex.reference.ImplantType;
import com.goldengamer.vortex.utility.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by golde on 31/10/2015.
 */
public class SpikedClaws extends ItemBound
{
    public boolean isActive;
    public int cooldown = 0;

    public SpikedClaws()
    {
        super();
        this.setUnlocalizedName("SPIKED_CLAWS");
        isActive = false;
        this.maxStackSize = 1;
    }

    @Override
    public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5) {
        super.onUpdate(item, world, entity, par4, par5);

        if (entity instanceof EntityPlayer && cooldown > 0)
        {
            cooldown--;
        }
        if (entity instanceof EntityPlayer && isActive == true) {

            if (entity.isCollidedHorizontally)
            {
                entity.fallDistance = 0.0F;
                if (entity.isSneaking()) {
                    entity.motionY = 0.0D;
                } else {
                    entity.motionY = 0.1176D; //(0.2D - 0.08D) * 0.98D
                }
            }
            else if (!entity.worldObj.isRemote)
            {
                double motionX = entity.posX - entity.lastTickPosX;
                double motionZ = entity.posZ - entity.lastTickPosZ;
                double motionY = entity.posY - entity.lastTickPosY - 0.765D; //serverside motion is weird.
                if (motionY > 0.0D && (motionX == 0D || motionZ == 0D))
                {
                    //most likely climbing.
                    entity.fallDistance = 0.0F;
                }
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        super.onItemRightClick(item, world, player);
        if (isActive == false && cooldown <= 0)
        {
            isActive = true;
            cooldown = 50;
        }
        else if (isActive == true && cooldown <= 0)
        {
            isActive = false;
            cooldown = 50;
        }

        return item;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {

        if (!(par1ItemStack.getTagCompound() == null))
        {
            if (!par1ItemStack.getTagCompound().getString("ownerName").equals(""))
            {
                par3List.add(StatCollector.translateToLocal("tooltip.vortex:owner") + " " + par1ItemStack.getTagCompound().getString("ownerName"));
                if (isActive == true)
                {
                    par3List.add(StatCollector.translateToLocal("tooltip.vortex:desc3"));
                }
                else{
                    par3List.add(StatCollector.translateToLocal("tooltip.vortex:desc4"));
                }
            }else{
                par3List.add(StatCollector.translateToLocal("tooltip.vortex:desc1"));
                par3List.add(StatCollector.translateToLocal("tooltip.vortex:desc2"));
            }
        }
    }

}
package com.goldengamer.vortex.item.equipment;

import com.goldengamer.vortex.item.base.ItemImplantVortex;
import com.goldengamer.vortex.reference.ImplantType;
import com.goldengamer.vortex.utility.LogHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by golde on 31/10/2015.
 */
public class SpikedShoes extends ItemImplantVortex
{

    public SpikedShoes()
    {
        super();
        this.setUnlocalizedName("SPIKED_SHOES");
    }

    @Override
    public ImplantType getImplantType(ItemStack itemstack) {
        return ImplantType.CLASS_IMPLANT;
    }
    //TODO change to something else


    //TODO This isnt working on servers fix it!
    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
        {
            super.onWornTick(itemstack, player);

            if (player instanceof EntityPlayer) {

                if (player.isCollidedHorizontally)
                {
                    player.fallDistance = 0.0F;
                    if (player.isSneaking()) {
                        player.motionY = 0.0D;
                    } else {
                        player.motionY = 0.1176D; //(0.2D - 0.08D) * 0.98D
                    }
                }
                else if (!player.worldObj.isRemote)
                {
                    double motionX = player.posX - player.lastTickPosX;
                    double motionZ = player.posZ - player.lastTickPosZ;
                    double motionY = player.posY - player.lastTickPosY - 0.765D; //serverside motion is weird.
                    if (motionY > 0.0D && (motionX == 0D || motionZ == 0D))
                    {
                        //most likely climbing.
                        player.fallDistance = 0.0F;
                    }
                }
            }
        }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
    }

}

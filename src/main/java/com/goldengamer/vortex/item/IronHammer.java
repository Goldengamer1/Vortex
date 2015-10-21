package com.goldengamer.vortex.item;

import com.goldengamer.vortex.item.base.ItemVortex;
import com.goldengamer.vortex.levelHandler.ExtendedPlayer;
import com.goldengamer.vortex.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by golde on 18/09/2015.
 */
public class IronHammer extends ItemVortex
{
    public IronHammer()
    {
        super();
        this.setUnlocalizedName("IRON_HAMMER");
        this.setMaxStackSize(1);
        this.setMaxDamage(64);
        this.setNoRepair();
    }


    //TODO GET RID
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            ExtendedPlayer props = ExtendedPlayer.get(player);

            props.addXP(10);
            LogHelper.info("[Warrior Lv]" + " = " + props.getWarriorLV());
            LogHelper.info(props.getWarriorXP() + " / " + props.getXPToNextLevel());
        }
        return  itemStack;
    }
}

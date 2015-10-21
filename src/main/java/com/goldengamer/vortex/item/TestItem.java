package com.goldengamer.vortex.item;

import com.goldengamer.vortex.item.base.ItemVortex;
import com.goldengamer.vortex.levelHandler.ExtendedPlayer;
import com.goldengamer.vortex.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by golde on 14/09/2015.
 */
public class TestItem extends ItemVortex
{
    public TestItem()
    {
        super();
        this.setUnlocalizedName("TEST_ITEM");
    }

    //TODO GET RID
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            ExtendedPlayer props = ExtendedPlayer.get(player);

            if (props.consumeRage(100))
            {
                LogHelper.info("[Rage Item] player had enough Rage. do something EPIC!");
                LogHelper.info(props.getCurrentRage());
            }
            else {
                LogHelper.info("[Rage Item] Player ran out of Rage. Sad face.");
                props.replenishRage();
                LogHelper.info(props.getCurrentRage());
            }
        }
        return  itemStack;
    }
}

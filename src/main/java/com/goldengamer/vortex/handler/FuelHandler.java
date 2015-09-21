package com.goldengamer.vortex.handler;

import com.goldengamer.vortex.init.ModItems;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

/**
 * Created by golde on 17/09/2015.
 */
public class FuelHandler implements IFuelHandler
{

    @Override
    public int getBurnTime(ItemStack fuel) {

        if(fuel.getItem() == ModItems.TEST_ITEM) return 800;




        return 0;
    }
}

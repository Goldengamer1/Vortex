package com.goldengamer.vortex.creativetab;

import com.goldengamer.vortex.init.ModItems;
import com.goldengamer.vortex.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by golde on 15/09/2015.
 */
public class CreativeTabVortex
{

    public static final CreativeTabs VORTEX_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        //CreativeTab icon
        public Item getTabIconItem() { return ModItems.TEST_ITEM; }


    };


}

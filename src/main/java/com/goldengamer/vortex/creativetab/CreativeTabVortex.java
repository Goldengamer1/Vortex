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

    public static final CreativeTabs VORTEX_TAB = new CreativeTabs(Reference.MOD_ID)
    {
        @Override
        public Item getTabIconItem()
        {
            //CreativeTab icon
            return ModItems.testItem;
        }

        @Override
        public String getTranslatedTabLabel()
        {
            //CreativeTab name
            return Reference.MOD_NAME;
        }

    };


}
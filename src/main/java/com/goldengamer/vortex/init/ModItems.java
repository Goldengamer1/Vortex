package com.goldengamer.vortex.init;

import com.goldengamer.vortex.item.ItemVortex;
import com.goldengamer.vortex.item.TestItem;
import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by golde on 14/09/2015.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemVortex testItem = new TestItem();



    public static void init()
    {
        GameRegistry.registerItem(testItem, "TestItem");
    }


}

package com.goldengamer.vortex.init;

import com.goldengamer.vortex.item.ItemVortex;
import com.goldengamer.vortex.item.TestItem;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by golde on 14/09/2015.
 */
public class ModItems
{
    public static final ItemVortex testItem = new TestItem();




    public static void init()
    {
        GameRegistry.registerItem(testItem, "TestItem");
    }


}

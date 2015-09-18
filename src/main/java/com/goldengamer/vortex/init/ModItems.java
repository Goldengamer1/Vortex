package com.goldengamer.vortex.init;

import com.goldengamer.vortex.item.equipment.tool.HardToFindiumShovel;
import com.goldengamer.vortex.item.equipment.tool.HardToFindiumSword;
import com.goldengamer.vortex.item.equipment.tool.HardToFindiumAxe;
import com.goldengamer.vortex.item.equipment.tool.HardToFindiumPickaxe;
import com.goldengamer.vortex.item.IronHammer;
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
    public static final IronHammer ironHammer = new IronHammer();
    public static final HardToFindiumPickaxe hardToFindiumPickaxe = new HardToFindiumPickaxe();
    public static final HardToFindiumAxe hardToFindiumAxe = new HardToFindiumAxe();
    public static final HardToFindiumShovel hardToFindiumShovel = new HardToFindiumShovel();
    public static final HardToFindiumSword hardToFindiumSword = new HardToFindiumSword();

    public static void init()
    {
        GameRegistry.registerItem(testItem, "TestItem");
        GameRegistry.registerItem(ironHammer, "IronHammer");
        GameRegistry.registerItem(hardToFindiumPickaxe, "HardToFindiumPickaxe");
        GameRegistry.registerItem(hardToFindiumAxe, "HardToFindiumAxe");
        GameRegistry.registerItem(hardToFindiumShovel, "HardToFindiumShovel");
        GameRegistry.registerItem(hardToFindiumSword, "HardToFindiumSword");
    }


}

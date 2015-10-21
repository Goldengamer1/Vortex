package com.goldengamer.vortex.init;

import com.goldengamer.vortex.item.equipment.implant.WarriorImplant;
import com.goldengamer.vortex.item.equipment.tool.HardToFindiumShovel;
import com.goldengamer.vortex.item.equipment.tool.HardToFindiumWeapon;
import com.goldengamer.vortex.item.equipment.tool.HardToFindiumAxe;
import com.goldengamer.vortex.item.equipment.tool.HardToFindiumPickaxe;
import com.goldengamer.vortex.item.IronHammer;
import com.goldengamer.vortex.item.TestItem;
import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;



/**
 * Created by golde on 14/09/2015.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final TestItem TEST_ITEM = new TestItem();

    //Tools
    public static final IronHammer IRON_HAMMER = new IronHammer();
    public static final HardToFindiumPickaxe HARD_TO_FINDIUM_PICKAXE = new HardToFindiumPickaxe();
    public static final HardToFindiumAxe HARD_TO_FINDIUM_AXE = new HardToFindiumAxe();
    public static final HardToFindiumShovel HARD_TO_FINDIUM_SHOVEL = new HardToFindiumShovel();
    public static final HardToFindiumWeapon HARD_TO_FINDIUM_SWORD = new HardToFindiumWeapon();

    //Implants
    public static final WarriorImplant WARRIOR_IMPLANT = new WarriorImplant();

    public static void init()
    {
        GameRegistry.registerItem(TEST_ITEM, "TestItem");

        //Tools
        GameRegistry.registerItem(IRON_HAMMER, "IronHammer");
        GameRegistry.registerItem(HARD_TO_FINDIUM_PICKAXE, "HardToFindiumPickaxe");
        GameRegistry.registerItem(HARD_TO_FINDIUM_AXE, "HardToFindiumAxe");
        GameRegistry.registerItem(HARD_TO_FINDIUM_SHOVEL, "HardToFindiumShovel");
        GameRegistry.registerItem(HARD_TO_FINDIUM_SWORD, "HardToFindiumWeapon");

        //Implants
        GameRegistry.registerItem(WARRIOR_IMPLANT, "WarriorImplant");
    }


}

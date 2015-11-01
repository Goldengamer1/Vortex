package com.goldengamer.vortex.init;

import com.goldengamer.vortex.item.IronSharpener;
import com.goldengamer.vortex.item.IronSpike;
import com.goldengamer.vortex.item.SpikedBase;
import com.goldengamer.vortex.item.equipment.SpikedClaws;
import com.goldengamer.vortex.item.equipment.implant.WarriorImplant;
import com.goldengamer.vortex.item.equipment.tool.HardToFindiumShovel;
import com.goldengamer.vortex.item.equipment.tool.HardToFindiumWeapon;
import com.goldengamer.vortex.item.equipment.tool.HardToFindiumAxe;
import com.goldengamer.vortex.item.equipment.tool.HardToFindiumPickaxe;
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
    public static final IronSpike IRON_SPIKE = new IronSpike();
    public static final SpikedBase SPIKED_BASE = new SpikedBase();

    //Tools
    public static final IronSharpener IRON_SHARPENER= new IronSharpener();
    public static final HardToFindiumPickaxe HARD_TO_FINDIUM_PICKAXE = new HardToFindiumPickaxe();
    public static final HardToFindiumAxe HARD_TO_FINDIUM_AXE = new HardToFindiumAxe();
    public static final HardToFindiumShovel HARD_TO_FINDIUM_SHOVEL = new HardToFindiumShovel();
    public static final HardToFindiumWeapon HARD_TO_FINDIUM_SWORD = new HardToFindiumWeapon();

    //Implants // Equipment
    public static final WarriorImplant WARRIOR_IMPLANT = new WarriorImplant();
    public static final SpikedClaws SPIKED_CLAWS = new SpikedClaws();

    public static void init()
    {
        GameRegistry.registerItem(TEST_ITEM, "TestItem");
        GameRegistry.registerItem(IRON_SPIKE, "IronSpike");
        GameRegistry.registerItem(SPIKED_BASE, "SpikedBase");

        //Tools
        GameRegistry.registerItem(IRON_SHARPENER, "IronSharpener");
        GameRegistry.registerItem(HARD_TO_FINDIUM_PICKAXE, "HardToFindiumPickaxe");
        GameRegistry.registerItem(HARD_TO_FINDIUM_AXE, "HardToFindiumAxe");
        GameRegistry.registerItem(HARD_TO_FINDIUM_SHOVEL, "HardToFindiumShovel");
        GameRegistry.registerItem(HARD_TO_FINDIUM_SWORD, "HardToFindiumWeapon");

        //Implants // Equipment
        GameRegistry.registerItem(WARRIOR_IMPLANT, "WarriorImplant");
        GameRegistry.registerItem(SPIKED_CLAWS, "SpikedClaws");
    }


}

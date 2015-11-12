package com.goldengamer.vortex.init;

import com.goldengamer.vortex.item.equipment.tool.*;
import com.goldengamer.vortex.item.materials.IronSpike;
import com.goldengamer.vortex.item.materials.SharpenedStick;
import com.goldengamer.vortex.item.materials.SpikedBase;
import com.goldengamer.vortex.item.equipment.implant.WarriorImplant;
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
    public static final SharpenedStick SHARPENED_STICK = new SharpenedStick();

    //Tools
    public static final IronSharpener IRON_SHARPENER= new IronSharpener();
    public static final HardToFindiumPickaxe HARD_TO_FINDIUM_PICKAXE = new HardToFindiumPickaxe();
    public static final HardToFindiumAxe HARD_TO_FINDIUM_AXE = new HardToFindiumAxe();
    public static final HardToFindiumShovel HARD_TO_FINDIUM_SHOVEL = new HardToFindiumShovel();
    public static final HardToFindiumWeapon HARD_TO_FINDIUM_SWORD = new HardToFindiumWeapon();

    //Implants // Equipment
    public static final WarriorImplant WARRIOR_IMPLANT = new WarriorImplant();

    public static final SpikedClaws SPIKED_CLAWS = new SpikedClaws();
    public static final PokingStick POKING_STICK = new PokingStick();

    public static void init()
    {
        GameRegistry.registerItem(TEST_ITEM, "TestItem");
        GameRegistry.registerItem(IRON_SPIKE, "IronSpike");
        GameRegistry.registerItem(SPIKED_BASE, "SpikedBase");
        GameRegistry.registerItem(SHARPENED_STICK, "SharpenedStick");

        //Tools
        GameRegistry.registerItem(IRON_SHARPENER, "IronSharpener");
        GameRegistry.registerItem(HARD_TO_FINDIUM_PICKAXE, "HardToFindiumPickaxe");
        GameRegistry.registerItem(HARD_TO_FINDIUM_AXE, "HardToFindiumAxe");
        GameRegistry.registerItem(HARD_TO_FINDIUM_SHOVEL, "HardToFindiumShovel");
        GameRegistry.registerItem(HARD_TO_FINDIUM_SWORD, "HardToFindiumWeapon");

        //Implants // Equipment
        GameRegistry.registerItem(WARRIOR_IMPLANT, "WarriorImplant");

        GameRegistry.registerItem(SPIKED_CLAWS, "SpikedClaws");
        GameRegistry.registerItem(POKING_STICK, "PokingStick");

    }


}

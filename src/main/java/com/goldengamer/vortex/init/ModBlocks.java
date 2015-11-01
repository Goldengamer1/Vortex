package com.goldengamer.vortex.init;

import com.goldengamer.vortex.block.*;
import com.goldengamer.vortex.creativetab.CreativeTabVortex;
import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;

/**
 * Created by golde on 15/09/2015.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    //Blocks
    public static final BlockTest BLOCK_TEST = new BlockTest();
    public static final BlockBd BLOCK_BD = new BlockBd();

    //Machines
    public static final BlockSurvivalistFurnace BLOCK_SURVIVALIST_FURNACE_IDLE = (BlockSurvivalistFurnace) new BlockSurvivalistFurnace(false).setBlockName("BLOCK_SURVIVALIST_FURNACE").setCreativeTab(CreativeTabVortex.VORTEX_TAB);
    public static final BlockSurvivalistFurnace BLOCK_SURVIVALIST_FURNACE_ACTIVE = (BlockSurvivalistFurnace) new BlockSurvivalistFurnace(true).setBlockName("BLOCK_SURVIVALIST_FURNACE_ACTIVE").setLightLevel(0.625F);

    //Special Blocks
    public static final BlockAbilityChanger BLOCK_ABILITY_CHANGER = new BlockAbilityChanger();

    public static void init()
    {
        //Blocks
        GameRegistry.registerBlock(BLOCK_TEST, "BlockTest");
        GameRegistry.registerBlock(BLOCK_BD, "BlockBd");

        //Machines
        GameRegistry.registerBlock(BLOCK_SURVIVALIST_FURNACE_IDLE, "BlockServivalistFurnaceIdle");
        GameRegistry.registerBlock(BLOCK_SURVIVALIST_FURNACE_ACTIVE, "BlockServivalistFurnaceActive");

        //Special Blocks
        GameRegistry.registerBlock(BLOCK_ABILITY_CHANGER,"BlockAbilityChanger");

    }
}

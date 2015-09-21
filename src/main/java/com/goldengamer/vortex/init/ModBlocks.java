package com.goldengamer.vortex.init;

import com.goldengamer.vortex.block.BlockBd;
import com.goldengamer.vortex.block.BlockTest;
import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by golde on 15/09/2015.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static final BlockTest BLOCK_TEST = new BlockTest();
    public static final BlockBd BLOCK_BD = new BlockBd();



    public static void init()
    {
        GameRegistry.registerBlock(BLOCK_TEST, "BlockTest");
        GameRegistry.registerBlock(BLOCK_BD, "BlockBd");
    }
}

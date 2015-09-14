package com.goldengamer.vortex.init;

import com.goldengamer.vortex.block.BlockTest;
import com.goldengamer.vortex.block.BlockVortex;
import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by golde on 15/09/2015.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static final BlockVortex blockTest = new BlockTest();



    public static void init()
    {
        GameRegistry.registerBlock(blockTest, "BlockTest");
    }
}

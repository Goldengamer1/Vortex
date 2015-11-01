package com.goldengamer.vortex.init;

import com.goldengamer.vortex.tileentity.TileEntityAbilityChanger;
import com.goldengamer.vortex.tileentity.TileEntityBd;
import com.goldengamer.vortex.tileentity.TileEntitySurvivalistFurnace;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by golde on 21/09/2015.
 */
public class TileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityBd.class, "tileEntityBd");
        GameRegistry.registerTileEntity(TileEntitySurvivalistFurnace.class, "survivalistFurnace");
        GameRegistry.registerTileEntity(TileEntityAbilityChanger.class, "tileEntityAbilityChanger");
    }
}

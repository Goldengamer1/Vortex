package com.goldengamer.vortex.block;

import com.goldengamer.vortex.tileentity.TileEntityBd;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by golde on 20/09/2015.
 */
public class BlockBd extends BlockTileEntityVortex
{
    public BlockBd()
    {
        super();
        this.setBlockName("BLOCK_BD");
        //this.setBlockBounds();
    }

    //You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
        return -1;
    }

    //It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    //It's not a normal block, so you need this too.
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityBd();
    }
}

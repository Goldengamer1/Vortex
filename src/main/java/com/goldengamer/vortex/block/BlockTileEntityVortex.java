package com.goldengamer.vortex.block;

import com.goldengamer.vortex.creativetab.CreativeTabVortex;
import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by golde on 20/09/2015.
 */
public class BlockTileEntityVortex extends BlockContainer
{

    public BlockTileEntityVortex(Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTabVortex.VORTEX_TAB);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
    }
    public BlockTileEntityVortex()
    {
        this(Material.rock);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    //@Override
    //@SideOnly(Side.CLIENT)
    //public void registerBlockIcons(IIconRegister iconRegister)
    //{
    //    blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    //}

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}

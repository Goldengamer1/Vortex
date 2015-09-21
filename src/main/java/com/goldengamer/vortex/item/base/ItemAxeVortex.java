package com.goldengamer.vortex.item.base;

import com.goldengamer.vortex.creativetab.CreativeTabVortex;
import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

/**
 * Created by golde on 18/09/2015.
 */
public class ItemAxeVortex extends ItemAxe
{
    public ItemAxeVortex(Item.ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        this.setCreativeTab(CreativeTabVortex.VORTEX_TAB);
        //this.setNoRepair();
        this.maxStackSize = 1;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected  String getUnwappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".")+ 1);
    }

}

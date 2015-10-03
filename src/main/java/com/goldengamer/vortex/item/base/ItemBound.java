package com.goldengamer.vortex.item.base;

import com.goldengamer.vortex.creativetab.CreativeTabVortex;
import com.goldengamer.vortex.reference.Reference;
import com.goldengamer.vortex.utility.PlayerHelper;
import com.goldengamer.vortex.utility.interfaces.IBindable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by golde on 03/10/2015.
 */
public class ItemBound extends Item implements IBindable
{
    public ItemBound(Item.ToolMaterial toolMaterial)
    {
        super();
        this.setCreativeTab(CreativeTabVortex.VORTEX_TAB);
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

    //
    public static boolean checkAndSetItemOwner(ItemStack itemStack, EntityPlayer player)
    {
        return  !PlayerHelper.isFakePlayer(player) && PlayerHelper.checkAndSetItemPlayer(itemStack, player);
    }

    public static void setItemOwner(ItemStack itemStack, String ownerName)
    {
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        itemStack.getTagCompound().setString("ownerName", ownerName);
    }

    public static void checkAndSetItemOwner(ItemStack itemStack, String ownerName)
    {
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        if (itemStack.getTagCompound().getString("ownerName").equals(""))
        {
            itemStack.getTagCompound().setString("ownerName", ownerName);
        }
    }

    public static String getOwnerName(ItemStack itemStack)
    {
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        return itemStack.getTagCompound().getString("ownerName");
    }

}

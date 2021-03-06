package com.goldengamer.vortex.item.base;

import com.goldengamer.vortex.creativetab.CreativeTabVortex;
import com.goldengamer.vortex.reference.Reference;
import com.goldengamer.vortex.utility.PlayerHelper;
import com.goldengamer.vortex.utility.interfaces.IBindable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by golde on 03/10/2015.
 */
public class ItemBound extends Item implements IBindable
{
    public ItemBound()
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

    //Binds item on rightclick
    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        super.onItemRightClick(item, world, player);
        if (!ItemBound.checkAndSetItemOwner(item, player) || player.isSneaking())
        {
            item.getTagCompound().setInteger("worldTimeDeley", (int) (world.getWorldTime() - 1) % 100);
            return item;
        }

        return item;
    }

    //Adds text to the item
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {

        if (!(par1ItemStack.getTagCompound() == null))
        {
            if (!par1ItemStack.getTagCompound().getString("ownerName").equals(""))
            {
                par3List.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.vortex:owner") + EnumChatFormatting.GRAY + " " + par1ItemStack.getTagCompound().getString("ownerName"));
            }else{
                par3List.add(StatCollector.translateToLocal("tooltip.vortex:desc1"));
                par3List.add(StatCollector.translateToLocal("tooltip.vortex:desc2"));
            }
        }
    }


    @Override
    public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
    {
        if (!(entity instanceof EntityPlayer))
        {
            return;
        }

        if (item.getTagCompound() == null)
        {
            item.setTagCompound(new NBTTagCompound());
        }

        //TODO This may or may not cause lag , find out maybe
        if (!(item.getTagCompound() == null)) {
            if (!item.getTagCompound().getString("ownerName").equals("")) {
                if (!item.getTagCompound().getString("ownerName").equals(entity.getCommandSenderName())) {
                    ((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 100, 11));
                    ((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.blindness.getId(), 100, 1));
                    ((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100, 11));
                    ((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.hunger.getId(), 100, 11));
                    ((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.poison.getId(), 100, 11));
                    ((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.weakness.getId(), 100, 11));
                    ((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.wither.getId(), 100, 5));
                    ((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 100, 11));

                }
            }
        }

    }

    // make it so item will despawn in 12hour
    @Override
    public int getEntityLifespan(ItemStack itemStack, World world)
    {
        return 864000;
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        if (!(item.getTagCompound() == null))
        {
            if (!item.getTagCompound().getString("ownerName").equals(""))
            {
                return false;
            }
        }
        return true;
    }
}

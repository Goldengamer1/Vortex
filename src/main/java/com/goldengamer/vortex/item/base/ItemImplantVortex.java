package com.goldengamer.vortex.item.base;

import com.goldengamer.vortex.container.base.InventoryImplants;
import com.goldengamer.vortex.creativetab.CreativeTabVortex;
import com.goldengamer.vortex.reference.ImplantType;
import com.goldengamer.vortex.utility.PlayerHelper;
import com.goldengamer.vortex.utility.interfaces.IBindable;
import com.goldengamer.vortex.utility.interfaces.IImplant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by golde on 08/10/2015.
 */
public class ItemImplantVortex extends ItemVortex implements IImplant, IBindable
{
    public ItemImplantVortex()
    {
        super();
        this.setCreativeTab(CreativeTabVortex.VORTEX_TAB);
        this.maxStackSize = 1;
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
        if(!world.isRemote) {
            InventoryImplants implants = PlayerHelper.getPlayerImplants(player);
            for(int i = 0; i < implants.getSizeInventory(); i++)
                if(implants.getStackInSlot(i) == null && implants.isItemValidForSlot(i, item)) {
                    implants.setInventorySlotContents(i, item.copy());
                    if(!player.capabilities.isCreativeMode){
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                    }
                    onEquipped(item, player);
                    break;
                }
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
                par3List.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.vortex:owner") + EnumChatFormatting.GRAY +  " " + par1ItemStack.getTagCompound().getString("ownerName"));
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
    public ImplantType getImplantType(ItemStack itemstack) {
        return null;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {

    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {

    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {

    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }
}



package com.goldengamer.vortex.container.base;


import java.lang.ref.WeakReference;
import java.util.ArrayList;

import com.goldengamer.vortex.Vortex;
import com.goldengamer.vortex.network.bidirectional.MessageImplantSlotsSync;
import com.goldengamer.vortex.network.PacketHandler;
import com.goldengamer.vortex.reference.ImplantType;
import com.goldengamer.vortex.utility.interfaces.IImplant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;

/**
 * Created by golde on 07/10/2015.
 */
public class InventoryImplants implements IInventory {
    public ItemStack[] stackList;
    private Container eventHandler;
    public WeakReference<EntityPlayer> player;
    public boolean blockEvents=false;

    public InventoryImplants(EntityPlayer player) {
        this.stackList = new ItemStack[4];
        this.player = new WeakReference<EntityPlayer>(player);
    }

    public Container getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(Container eventHandler) {
        this.eventHandler = eventHandler;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return this.stackList.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int par1) {
        return par1 >= this.getSizeInventory() ? null : this.stackList[par1];
    }

    /**
     * Returns the name of the inventory
     */
    @Override
    public String getInventoryName() {
        return "";
    }

    /**
     * Returns if the inventory is named
     */
    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    /**
     * When some containers are closed they call this on each slot, then drop
     * whatever it returns as an EntityItem - like when you close a workbench
     * GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.stackList[par1] != null) {
            ItemStack itemstack = this.stackList[par1];
            this.stackList[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number
     * (second arg) of items and returns them in a new stack.
     */
    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.stackList[par1] != null) {
            ItemStack itemstack;

            if (this.stackList[par1].stackSize <= par2) {
                itemstack = this.stackList[par1];

                if (itemstack != null && itemstack.getItem() instanceof IImplant) {
                    ((IImplant) itemstack.getItem()).onUnequipped(itemstack,
                            player.get());
                }

                this.stackList[par1] = null;

                if (eventHandler != null)
                    this.eventHandler.onCraftMatrixChanged(this);
                syncSlotToClients(par1);
                return itemstack;
            } else {
                itemstack = this.stackList[par1].splitStack(par2);

                if (itemstack != null && itemstack.getItem() instanceof IImplant) {
                    ((IImplant) itemstack.getItem()).onUnequipped(itemstack,
                            player.get());
                }

                if (this.stackList[par1].stackSize == 0) {
                    this.stackList[par1] = null;
                }

                if (eventHandler != null)
                    this.eventHandler.onCraftMatrixChanged(this);
                syncSlotToClients(par1);
                return itemstack;
            }
        } else {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be
     * crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int par1, ItemStack stack) {

        if(!blockEvents && this.stackList[par1] != null) {
            ((IImplant)stackList[par1].getItem()).onUnequipped(stackList[par1], player.get());
        }
        this.stackList[par1] = stack;
        if (!blockEvents && stack != null && stack.getItem() instanceof IImplant) {
            ((IImplant) stack.getItem()).onEquipped(stack, player.get());
        }
        if (eventHandler != null)
            this.eventHandler.onCraftMatrixChanged(this);
        syncSlotToClients(par1);
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    /**
     * For tile entities, ensures the chunk containing the tile entity is saved
     * to disk later - the game won't think it hasn't changed and skip it.
     */
    @Override
    public void markDirty() {
        try {
            player.get().inventory.markDirty();
        } catch (Exception e) {
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes
     * with Container
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring
     * stack size) into the given slot.
     */
    @Override
    public boolean isItemValidForSlot(int i, ItemStack stack)
    {
        if (stack == null || !(stack.getItem() instanceof IImplant) || !((IImplant) stack.getItem()).canEquip(stack, player.get()))
            return false;
        if (i == 0 && ((IImplant) stack.getItem()).getImplantType(stack) == ImplantType.CLASS_IMPLANT)
            return true;
        return false;
    }

    public void saveNBT(EntityPlayer player)
    {
        NBTTagCompound tags = player.getEntityData();
        saveNBT(tags);
    }

    public void saveNBT(NBTTagCompound tags)
    {
        NBTTagList tagList = new NBTTagList();
        NBTTagCompound invSlot;
        for (int i = 0; i < this.stackList.length; ++i)
        {
            if (this.stackList[i] != null) {
                invSlot = new NBTTagCompound();
                invSlot.setByte("Slot", (byte) i);
                this.stackList[i].writeToNBT(invSlot);
                tagList.appendTag(invSlot);
            }
        }
        tags.setTag("Implant.Inventory", tagList);
    }

    public void readNBT(EntityPlayer player)
    {
        NBTTagCompound tags = player.getEntityData();
        readNBT(tags);
    }

    public void readNBT(NBTTagCompound tags)
    {
        NBTTagList tagList = tags.getTagList("Implant.Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound) tagList.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;
            ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);
            if (itemstack != null)
            {
                this.stackList[j] = itemstack;
            }
        }
    }

    public void dropItems(ArrayList<EntityItem> drops)
    {
        for (int i = 0; i < 4; ++i)
        {
            if (this.stackList[i] != null)
            {
                EntityItem ei = new EntityItem(player.get().worldObj,player.get().posX, player.get().posY + player.get().eyeHeight, player.get().posZ,this.stackList[i].copy());
                ei.delayBeforeCanPickup = 40;
                float f1 = player.get().worldObj.rand.nextFloat() * 0.5F;
                float f2 = player.get().worldObj.rand.nextFloat() * (float) Math.PI * 2.0F;
                ei.motionX = (double) (-MathHelper.sin(f2) * f1);
                ei.motionZ = (double) (MathHelper.cos(f2) * f1);
                ei.motionY = 0.20000000298023224D;
                drops.add(ei);
                this.stackList[i] = null;
                syncSlotToClients(i);
            }
        }
    }

    public void dropItemsAt(ArrayList<EntityItem> drops, Entity e)
    {
        for (int i = 0; i < 4; ++i)
        {
            if (this.stackList[i] != null)
            {
                EntityItem ei = new EntityItem(e.worldObj,e.posX, e.posY + e.getEyeHeight(), e.posZ,this.stackList[i].copy());
                ei.delayBeforeCanPickup = 40;
                float f1 = e.worldObj.rand.nextFloat() * 0.5F;
                float f2 = e.worldObj.rand.nextFloat() * (float) Math.PI * 2.0F;
                ei.motionX = (double) (-MathHelper.sin(f2) * f1);
                ei.motionZ = (double) (MathHelper.cos(f2) * f1);
                ei.motionY = 0.20000000298023224D;
                drops.add(ei);
                this.stackList[i] = null;
                syncSlotToClients(i);
            }
        }
    }

    public void syncSlotToClients(int slot)
    {
        try {
            if (Vortex.proxy.getClientWorld() == null)
            {
                PacketHandler.INSTANCE.sendToAll(new MessageImplantSlotsSync(player.get(), slot));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
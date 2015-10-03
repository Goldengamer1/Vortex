package com.goldengamer.events;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by golde on 02/10/2015.
 *
 * Inspired by WayOfTimes ItemBindEvent
 */

@Cancelable
public class ItemBindableEvent extends Event
{

    public final EntityPlayer player;
    public String key;
    public ItemStack itemStack;

    public  ItemBindableEvent(EntityPlayer player, String key, ItemStack itemStack)
    {
        super();
        this.player = player;
        this.key = key;
        this.itemStack = itemStack;
    }
}

package com.goldengamer.vortex.item;

import com.goldengamer.vortex.item.base.ItemVortex;

/**
 * Created by golde on 18/09/2015.
 */
public class IronHammer extends ItemVortex
{
    public IronHammer()
    {
        super();
        this.setUnlocalizedName("IRON_HAMMER");
        this.setMaxStackSize(1);
        this.setMaxDamage(64);
        this.setNoRepair();
    }
}

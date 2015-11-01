package com.goldengamer.vortex.item;

import com.goldengamer.vortex.item.base.ItemVortex;

/**
 * Created by golde on 18/09/2015.
 */
public class IronSharpener extends ItemVortex
{
    public IronSharpener()
    {
        super();
        this.setUnlocalizedName("IRON_SHARPENER");
        this.setMaxStackSize(1);
        this.setMaxDamage(64);
        this.setNoRepair();
    }

}

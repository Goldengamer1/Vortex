package com.goldengamer.vortex.item.equipment.tool;

        import com.goldengamer.vortex.item.base.ItemSwordVortex;
        import com.goldengamer.vortex.reference.Material;
        import com.goldengamer.vortex.utility.interfaces.IHudOverlay;

/**
 * Created by golde on 18/09/2015.
 */
public class HardToFindiumSword extends ItemSwordVortex implements IHudOverlay
{

    public HardToFindiumSword()
    {
        super(Material.Tools.HardToFindIum);
        this.setUnlocalizedName("HARDTOFINDIUM_SWORD");
    }

}

package com.goldengamer.vortex.item.equipment.tool;

        import com.goldengamer.vortex.item.base.ItemBound;
        import com.goldengamer.vortex.item.base.ItemSwordVortex;
        import com.goldengamer.vortex.reference.Material;
        import com.goldengamer.vortex.utility.interfaces.IHudOverlay;
        import net.minecraft.entity.Entity;
        import net.minecraft.entity.player.EntityPlayer;
        import net.minecraft.item.ItemStack;
        import net.minecraft.nbt.NBTTagCompound;
        import net.minecraft.potion.Potion;
        import net.minecraft.potion.PotionEffect;
        import net.minecraft.world.World;

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

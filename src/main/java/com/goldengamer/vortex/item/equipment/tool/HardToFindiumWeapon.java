package com.goldengamer.vortex.item.equipment.tool;

        import com.goldengamer.vortex.item.base.ItemWeaponVortex;
        import com.goldengamer.vortex.utility.LogHelper;
        import com.goldengamer.vortex.utility.Vector3;
        import com.goldengamer.vortex.utility.interfaces.IHudOverlay;
        import com.goldengamer.vortex.utility.interfaces.implantClasses.IWarrior;
        import net.minecraft.entity.Entity;
        import net.minecraft.entity.EntityLivingBase;
        import net.minecraft.entity.player.EntityPlayer;
        import net.minecraft.item.ItemStack;
        import net.minecraft.util.AxisAlignedBB;
        import net.minecraft.util.DamageSource;
        import net.minecraft.util.MathHelper;
        import net.minecraft.world.World;

        import java.util.List;

/**
 * Created by golde on 18/09/2015.
 */
public class HardToFindiumWeapon extends ItemWeaponVortex implements IHudOverlay, IWarrior
{

    public HardToFindiumWeapon()
    {
        super();
        this.setUnlocalizedName("HARDTOFINDIUM_SWORD");
    }

    @Override
    public boolean onLeftClickEntity (ItemStack stack, EntityPlayer player, Entity entity)
    {

        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        //int just for debug
        int a = 0;

        float damage = 5F;



        int yaw = (int)player.rotationYaw;
        if (yaw>360)              //due to the yaw running a -360 to positive 360
            yaw=360;    //not sure why it's that way
       // yaw+=22;     //centers coordinates you may want to drop this line
        //yaw%=360;  //and this one if you want a strict interpretation of the zones

        int facing = yaw/45;   //  360degrees divided by 45 == 8 zones
        LogHelper.info("Yaw is " + yaw + "facing is " + facing);

        if (facing == 0) {
            LogHelper.info("0");
            Vector3 pos = new Vector3(0D, 0D, 0D);
            Vector3 posExpand = new Vector3(1D, 1D, 1D);
            Vector3 posOffset = new Vector3(0D, 0D, 1D);
            int y = 1;
            partical(world, "crit", player.posX, pos.x, posExpand.x, player.posY, y, player.posZ, pos.z, posExpand.z, 0, 0, 0);

            //AxisAlignedBB box = AxisAlignedBB.getBoundingBox(player.posX, player.posY, player.posZ, player.posX + pos.x, player.posY + pos.y, player.posZ + pos.z).expand(posExpand.x, posExpand.y, posExpand.z);
            //AxisAlignedBB box = AxisAlignedBB.getBoundingBox(player.posX + posOffset.x, player.posY, player.posZ + posOffset.z, player.posX + posOffset.x, player.posY, player.posZ + posOffset.z).expand(pos.x, pos.y, pos.z);
            AxisAlignedBB box = AxisAlignedBB.getBoundingBox(player.posX - 1, player.posY, player.posZ , player.posX + 1, player.posY + y, player.posZ + 2);//.expand(pos.x, pos.y, pos.z);
            List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, box);
            for (Object o : list) {
                Entity entity = (Entity) o;
                a++;
                LogHelper.info(a + "<number||mob>" + o);
                //AbilityHelper.onLeftClickEntity(stack, player, (Entity) o, this);
                //entity.setDead();
                entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
            }
        }
        /*
        if (facing == 2) {

            LogHelper.info("2");
            Vector3 pos = new Vector3(0D, 0D, 0D);
            Vector3 posExpand = new Vector3(1D, 1D, 1D);
            Vector3 posOffset = new Vector3(0D, 0D, 1D);
            int y = 1;
            partical(world, "crit", player.posX, pos.x, posExpand.x, player.posY, y, player.posZ, pos.z, posExpand.z, 0, 0, 0);

            //AxisAlignedBB box = AxisAlignedBB.getBoundingBox(player.posX, player.posY, player.posZ, player.posX + pos.x, player.posY + pos.y, player.posZ + pos.z).expand(posExpand.x, posExpand.y, posExpand.z);
            //AxisAlignedBB box = AxisAlignedBB.getBoundingBox(player.posX + posOffset.x, player.posY, player.posZ + posOffset.z, player.posX + posOffset.x, player.posY, player.posZ + posOffset.z).expand(pos.x, pos.y, pos.z);
            AxisAlignedBB box = AxisAlignedBB.getBoundingBox(player.posX - 1, player.posY, player.posZ -1, player.posX - 2, player.posY + y, player.posZ + 1);//.expand(pos.x, pos.y, pos.z);
            List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, box);
            for (Object o : list) {
                Entity entity = (Entity) o;
                a++;
                LogHelper.info(a + "<number||mob>" + o);
                //AbilityHelper.onLeftClickEntity(stack, player, (Entity) o, this);
                //entity.setDead();
                entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
            }
        }
        */

        return item;
    }

    private void partical(World world, String s, double x, double xoff, double xexpan, double y, int yoff, double z, double zoff, double zexpan, double d1, double d2, double d3)
    {
        //world.spawnParticle(s, x + xoff + xexpan, y - yoff, z , d1, d2, d3);
        //world.spawnParticle(s, x + xoff + xexpan, y - yoff, z + zoff + zexpan, d1, d2, d3);
        //world.spawnParticle(s, x + xoff + xexpan, y - yoff, z - zoff - zexpan, d1, d2, d3);

        //world.spawnParticle(s, x - xoff - xexpan, y - yoff, z , d1, d2, d3);
        //world.spawnParticle(s, x - xoff - xexpan, y - yoff, z + zoff + zexpan, d1, d2, d3);
        //world.spawnParticle(s, x - xoff - xexpan, y - yoff, z - zoff - zexpan, d1, d2, d3);

        //world.spawnParticle(s, x, y - yoff, z + zoff + zexpan, d1, d2, d3);
        //world.spawnParticle(s, x, y - yoff, z - zoff - zexpan, d1, d2, d3);
    }
}

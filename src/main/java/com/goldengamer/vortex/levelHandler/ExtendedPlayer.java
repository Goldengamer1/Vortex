package com.goldengamer.vortex.levelHandler;

import com.goldengamer.vortex.handler.ConfigurationHandler;
import com.goldengamer.vortex.network.PacketHandler;
import com.goldengamer.vortex.network.client.SyncPlayerPropsMessage;
import com.goldengamer.vortex.reference.Reference;
import com.goldengamer.vortex.utility.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by golde on 16/10/2015.
 */
public class ExtendedPlayer implements IExtendedEntityProperties
{
    public final static String EXT_PROP_NAME = "ExtendedPlayer";

    //This is the Rage int
    public static final int RAGE_WATCHER = ConfigurationHandler.WarriorDataWatcher();
    //This is the warriors lv and xp
    private int warriorLV;
    private int warriorXP;

    private final EntityPlayer player;
    private EntityLivingBase entity;

    //This is for Max rage and lv;
    private static final int maxRage = Reference.MAX_RAGE;
    private static final int maxLV = Reference.MAX_LEVEL;

    public ExtendedPlayer(EntityPlayer player)
    {
        this.player = player;

        this.player.getDataWatcher().addObject(RAGE_WATCHER,0);
        this.warriorLV = 1;
        this.warriorXP = 0;
    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
    }

    public static final ExtendedPlayer get(EntityPlayer player)
    {
        return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
    }

    // Save any custom data that needs saving here
    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();

        properties.setInteger("CurrentRage", this.player.getDataWatcher().getWatchableObjectInt(RAGE_WATCHER));
        properties.setInteger("WarriorLV", this.warriorLV);
        properties.setInteger("WarriorXP", this.warriorXP);

        compound.setTag(EXT_PROP_NAME, properties);
    }

    // Load whatever data you saved
    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);

        //this.currentRage = properties.getInteger("CurrentRage");
        this.player.getDataWatcher().updateObject(RAGE_WATCHER, properties.getInteger("CurrentRage"));
        this.warriorLV = properties.getInteger("WarriorLV");
        this.warriorXP = properties.getInteger("WarriorXP");

        LogHelper.info("[Vortex PROPS] rage from NBT: " + this.getCurrentRage() + " / " + this.maxRage);
    }

    @Override
    public void init(Entity entity, World world) {}

    //===================================================================================================
    // Level Stuff
    //===================================================================================================
    public int getWarriorLV()
    {
        return this.warriorLV;
    }

    public int getXPToNextLevel()
    {
        if (getWarriorLV() == 1) {return 100;}
        if (getWarriorLV() == 2) {return 200;}
        if (getWarriorLV() == 3) {return 300;}
        if (getWarriorLV() == 4) {return 400;}
        if (getWarriorLV() == 5) {return 500;}
        if (getWarriorLV() == 6) {return 600;}
        if (getWarriorLV() == 7) {return 700;}
        if (getWarriorLV() == 8) {return 800;}
        if (getWarriorLV() == 9) {return 900;}
        if (getWarriorLV() == 10) {return 1000;}
        if (getWarriorLV() == 11) {return 1100;}
        if (getWarriorLV() == 12) {return 1200;}
        if (getWarriorLV() == 13) {return 1300;}
        if (getWarriorLV() == 14) {return 1400;}
        if (getWarriorLV() == 15) {return 1500;}
        if (getWarriorLV() == 16) {return 1600;}
        if (getWarriorLV() == 17) {return 1700;}
        if (getWarriorLV() == 18) {return 1800;}
        if (getWarriorLV() == 19) {return 1900;}
        if (getWarriorLV() == 20) {return 2000;}

        return 100000000;
    }

    public int getWarriorXP()
    {
        return this.warriorXP;
    }

    //This should set the level to 0 or max level if its over or under , then syncs
    public void setWarriorLV(int warriorLevel)
    {
        if (warriorLV < 1) warriorLevel = 1;
        if (warriorLV > maxLV) warriorLevel = maxLV;

        this.warriorLV = warriorLevel;
    }

    /*
    public int addWarriorXP(int amt)
    {
        if (warriorLV == maxLV || !(this.entity instanceof EntityPlayer)) return 0;
        this.warriorXP = this.warriorXP + amt;
        int xpToLevel = getXPToNextLevel();
        if (warriorXP >= xpToLevel)
        {
            warriorXP = 0;
            setWarriorLV(warriorLV + 1);
            if (this.entity instanceof EntityPlayer)
            {
                if (warriorLV <= 5)
                {
                    LogHelper.info("unlock skill 1");
                }
                else if (warriorLV <= 10)
                {
                    LogHelper.info("unlock skill 2");
                }
            }
            //TODO LEVEL UP SOUND
            //((EntityPlayer) this.entity).worldObj.playSoundAtEntity(player, "" , 1, 1);
        }
        LogHelper.info("Level up!" + getWarriorLV() + " / " + maxLV);
        sync();
        return xpToLevel;
    }
    */
    public int addXP(int xp)
    {
        int xpToLevel = getXPToNextLevel();
        this.warriorXP = this.warriorXP + xp;
        if (warriorXP < 0) warriorXP = 0;
        if (warriorXP >= xpToLevel)
        {
            warriorXP = 0;
            setWarriorLV(warriorLV + 1);
            if (this.entity instanceof EntityPlayer)
            {
                if (warriorLV == 5)
                {
                    LogHelper.info("unlock skill 1");
                }
                else if (warriorLV == 10)
                {
                    LogHelper.info("unlock skill 2");
                }

            }
            //TODO LEVEL UP SOUND
            //((EntityPlayer) this.entity).worldObj.playSoundAtEntity(player, "mob.zombie.say" , 1, 1);
            LogHelper.info("Level up!" + getWarriorLV() + " / " + maxLV);
        }
        sync();
        //return this.warriorXP = this.warriorXP + xp;
        return warriorXP;
    }

    //===================================================================================================
    // Rage Stuff
    //===================================================================================================
    public boolean consumeRage(int amount)
    {
        int rage = this.player.getDataWatcher().getWatchableObjectInt(RAGE_WATCHER);
        boolean sufficient = amount <= rage;
        rage -= (amount < rage ? amount : rage);
        // Update the data watcher object with the new value
        this.player.getDataWatcher().updateObject(RAGE_WATCHER, rage);
        // note that we no longer need to call 'sync()' to update the client
        return sufficient;
    }

    //TODO
    //Method sets current Rage to max Rage
    public void replenishRage() {
        this.player.getDataWatcher().updateObject(RAGE_WATCHER, this.maxRage);
        //sync();
    }

    public int getCurrentRage()
    {
        return this.player.getDataWatcher().getWatchableObjectInt(RAGE_WATCHER);
    }

    public int getMaxRage()
    {
        return this.maxRage;
    }

    public void setCurrentRage(int amount)
    {
        this.player.getDataWatcher().updateObject(RAGE_WATCHER, (amount < this.maxRage ? amount : this.maxRage));
        //sync();
    }

    //===================================================================================================
    // Util Stuff
    //===================================================================================================

    public void sync()
    {
        PacketHandler.sendTo(new SyncPlayerPropsMessage(player), (EntityPlayerMP) player);
    }

    public void copy(ExtendedPlayer props) {
        this.player.getDataWatcher().updateObject(RAGE_WATCHER, props.getCurrentRage());
        this.warriorLV = props.warriorLV;
        this.warriorXP = props.warriorXP;
    }

}
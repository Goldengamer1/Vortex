package com.goldengamer.vortex.handler;

import com.goldengamer.vortex.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by golde on 14/09/2015.
 */
public class ConfigurationHandler
{

    public static Configuration configuration;

    // config properties
    public static boolean SurvivalistFurnaceParticals = false;
    private static boolean splitSurvivalCreative = false;

    private static int ClassResourceBarXPos = 2;
    private static int ClassResourceBarYPos = 2;

    private static int ClassLevelBarXPos = 2;
    private static int ClassLevelBarYPos = 2;
    private static int ClassLevelText = 1;
    private static boolean ClassLevelToggle = true;

    private static int WarriorDataWatcher = 20;

    public static void init(File configFile)
    {
        // Create the configuration object from the given configuration file
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        SurvivalistFurnaceParticals = configuration.getBoolean("survivalistFurnaceParticals", Configuration.CATEGORY_GENERAL, true, "Toggle Survivalist Furnace Particals ON/OFF");
        splitSurvivalCreative = configuration.getBoolean("splitSurvivalCreative", Configuration.CATEGORY_GENERAL, false, "Toggle split survival creative implants ON/OFF");
        ClassResourceBarXPos = configuration.getInt("ClassResourceBarXPos", Configuration.CATEGORY_GENERAL, 0, 0, 1000, "Class resource bar [X] Pos");
        ClassResourceBarYPos = configuration.getInt("ClassResourceBarYPos", Configuration.CATEGORY_GENERAL, 0, 0, 1000, "Class resource bar [Y] Pos");
        ClassLevelBarXPos = configuration.getInt("ClassLevelBarXPos", Configuration.CATEGORY_GENERAL, 0, 0, 1000, "Class level Bar [X] Pos");
        ClassLevelBarYPos = configuration.getInt("ClassLevelBarYPos", Configuration.CATEGORY_GENERAL, 0, 0, 1000, "Class level Bar [Y] Pos");
        ClassLevelText = configuration.getInt("ClassLevelText", Configuration.CATEGORY_GENERAL, 1, 1, 4, "Class level text position[1-Top, 2-left, 3-right, 4-bottom]");
        ClassLevelToggle = configuration.getBoolean("ClassLevelToggle", Configuration.CATEGORY_GENERAL, true, "Toggle level bar ON/OFF");
        WarriorDataWatcher = configuration.getInt("WarriorDataWatcher", Configuration.CATEGORY_GENERAL, 20, 1, 100, "Changes datawatcher ID");

        if(configuration.hasChanged())
        {
            configuration.save();
        }
    }


    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }

    public static boolean isSplitSurvivalCreative()
    {
        return splitSurvivalCreative;
    }

    public static int ClassResourceBarXPos()
    {
        return ClassResourceBarXPos;
    }

    public static int ClassResourceBarYPos()
    {
        return ClassResourceBarYPos;
    }

    public static int ClassLevelBarXPos()
    {
        return ClassLevelBarXPos;
    }

    public static int ClassLevelBarYPos()
    {
        return ClassLevelBarYPos;
    }

    public static int ClassLevelText()
    {
        return ClassLevelText;
    }

    public static boolean ClassLevelToggle()
    {
        return ClassLevelToggle;
    }

    public static int WarriorDataWatcher() { return WarriorDataWatcher; }
}

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
        SurvivalistFurnaceParticals = configuration.getBoolean("survivalistFurnaceParticals", Configuration.CATEGORY_GENERAL, true, "Survivalist Furnace Particals ON/OFF");
        splitSurvivalCreative = configuration.getBoolean("splitSurvivalCreative", Configuration.CATEGORY_GENERAL, false, "split Survival Creative Implants ON/OFF");
        ClassResourceBarXPos = configuration.getInt("ClassResourceBarXPos", Configuration.CATEGORY_GENERAL, 0, 0, 1000, "this will move ur Class's resource Bar [X]");
        ClassResourceBarYPos = configuration.getInt("ClassResourceBarYPos", Configuration.CATEGORY_GENERAL, 0, 0, 1000, "this will move ur Class's resource Bar [Y]");
        ClassLevelBarXPos = configuration.getInt("ClassLevelBarXPos", Configuration.CATEGORY_GENERAL, 0, 0, 1000, "this will move ur Class's level Bar [X]");
        ClassLevelBarYPos = configuration.getInt("ClassLevelBarYPos", Configuration.CATEGORY_GENERAL, 0, 0, 1000, "this will move ur Class's level Bar [Y]");
        ClassLevelText = configuration.getInt("ClassLevelText", Configuration.CATEGORY_GENERAL, 1, 1, 4, "this will move ur level Text[1-Top, 2-left, 3-right, 4-bottom]");
        ClassLevelToggle = configuration.getBoolean("ClassLevelToggle", Configuration.CATEGORY_GENERAL, true, "Toggles Level bar ON/OFF");

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
}

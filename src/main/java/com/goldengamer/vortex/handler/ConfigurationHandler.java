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

    public static boolean isSplitSurvivalCreative() {
        return splitSurvivalCreative;
    }
}

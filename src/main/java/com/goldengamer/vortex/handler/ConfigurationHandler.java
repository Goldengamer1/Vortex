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
    public static boolean testValue = false;

    public static void init(File configFile)
    {

        //Create the configuration object from the given configuration file
        if(configuration == null)
        {
            Configuration configuration = new Configuration(configFile);
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();

        }
    }

    public void loadConfiguration()
    {
        testValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, false, "This is n example config value");

        if(configuration.hasChanged())
        {
            configuration.save();
        }
    }
}

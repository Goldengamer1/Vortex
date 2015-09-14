package com.goldengamer.vortex.configuration;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by golde on 14/09/2015.
 */
public class ConfigurationHandler {

    public static void init(File configFile){

            /** Create the configuration object from the given configuration file */
        Configuration configuration = new Configuration(configFile);
        boolean configValue = false;
        try {
            /** Load the configuration file */

            configuration.load();

            /** Read in properties from configuration file */
            configValue = configuration.get(Configuration.CATEGORY_GENERAL, "configValue", true, "This is an example config value").getBoolean(true);

        }
        catch (Exception e) {
            /** Log the exception */

        }
        finally {
            /** Save the configuration file */
            configuration.save();
        }

        System.out.println("Config Test " + configValue);
    }

}
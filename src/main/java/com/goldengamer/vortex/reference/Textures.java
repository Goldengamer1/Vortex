package com.goldengamer.vortex.reference;

import com.goldengamer.vortex.utility.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

/**
 * Created by golde on 20/09/2015.
 */
public class Textures
{
    public static final String RESOURCE_PREFIX = Reference.LOWERCASE_MOD_ID + ":";

    public static final class Armor
    {
        private static final String ARMOR_SHEET_LOCATION = "vortex:textures/armor/";

    }

    public static final class Model
    {
        private static final String MODEL_TEXTURE_LOCATION = "vortex:textures/models/";
        public static final ResourceLocation BLOCK_BD = (new ResourceLocation(MODEL_TEXTURE_LOCATION + "BLOCK_BD.png"));
        public static final ResourceLocation BLOCK_TARDIS_TILE = (new ResourceLocation(MODEL_TEXTURE_LOCATION + "BLOCK_TARDIS_TILE.png"));
        public static final ResourceLocation HARDTOFINDIUM_SWORD = (new ResourceLocation(MODEL_TEXTURE_LOCATION + "HARDTOFINDIUM_SWORD.png"));
    }

    public static final class Gui
    {
        protected static final String GUI_TEXTURE_LOCATION = "vortex:textures/gui/container/";
        protected static final String HUD_TEXTURE_LOCATION = "vortex:textures/gui/";
        public static final ResourceLocation SURVIVALIST_FURNACE = (new ResourceLocation(GUI_TEXTURE_LOCATION + "survivalistFurnace.png"));
        public static final ResourceLocation IMPLANT_GUI = (new ResourceLocation(GUI_TEXTURE_LOCATION + "implantinventory.png"));
        public static final ResourceLocation ABILITY_CHANGER = (new ResourceLocation(GUI_TEXTURE_LOCATION + "abilityChanger"));

        //HUD
        public static final ResourceLocation ABILITYBAR = (new ResourceLocation(HUD_TEXTURE_LOCATION + "abilityBar.png"));

    }

    public static final class Elements
    {


    }

    public static final class Effect
    {
        private static final String EFFECTS_LOCATION = "vortex:textures/effects/";

    }
}

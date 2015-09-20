package com.goldengamer.vortex.utility;

import com.goldengamer.vortex.reference.Reference;
import net.minecraft.util.ResourceLocation;

/**
 * Created by golde on 20/09/2015.
 */
public class ResourceLocationHelper
{
    public static ResourceLocation getResourceLocation(String modId, String path)
    {
        return new ResourceLocation(modId, path);
    }

    public static ResourceLocation getResourceLocation(String path)
    {
        return getResourceLocation(Reference.LOWERCASE_MOD_ID, path);
    }
}

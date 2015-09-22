package com.goldengamer.vortex.client.renderer.model;

import com.goldengamer.vortex.reference.Models;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

/**
 * Created by golde on 22/09/2015.
 */

@SideOnly(Side.CLIENT)
public class ModelHardtofindiumSword
{
    private IModelCustom modelHardtofindiumSword;

    public ModelHardtofindiumSword()
    {
        modelHardtofindiumSword = AdvancedModelLoader.loadModel(Models.HARDTOFINDIUMSWORD);
    }

    public void render()
    {
        modelHardtofindiumSword.renderAll();
    }

    public void renderPart(String partName)
    {
        modelHardtofindiumSword.renderPart(partName);
    }
}

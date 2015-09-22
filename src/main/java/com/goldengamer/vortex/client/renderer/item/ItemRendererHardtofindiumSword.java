package com.goldengamer.vortex.client.renderer.item;

import com.goldengamer.vortex.client.renderer.model.ModelHardtofindiumSword;
import com.goldengamer.vortex.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by golde on 22/09/2015.
 */
public class ItemRendererHardtofindiumSword implements IItemRenderer
{

    protected ModelHardtofindiumSword model;

    public ItemRendererHardtofindiumSword()
    {
        model = new ModelHardtofindiumSword();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type)
        {
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
                return true;
            default: return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.LOWERCASE_MOD_ID, "textures/models/HARDTOFINDIUM_SWORD.png"));

                GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                //GL11.glRotatef(180.0F , 1.0F, 0.0F ,0.0F);
                GL11.glRotatef(-45.0F, 0.0F ,0.0F ,1.0F);

                GL11.glTranslatef(-2.6F, -1.4F, -0.05F);

                GL11.glScalef(1, 1, 1);

                model.renderPart("hardtofindiumSword");

                GL11.glPopMatrix();
        default:
            break;
        }
    }
}

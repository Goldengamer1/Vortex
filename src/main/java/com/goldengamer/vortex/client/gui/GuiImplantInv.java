package com.goldengamer.vortex.client.gui;

import com.goldengamer.vortex.container.ContainerImplantInv;
import com.goldengamer.vortex.reference.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by golde on 06/10/2015.
 */
public class GuiImplantInv extends GuiContainer
{
    private float xSizeFloat;
    private float ySizeFloat;


    public GuiImplantInv(EntityPlayer player)
    {
            super(new ContainerImplantInv(player.inventory));
    }

    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);
        this.xSizeFloat = (float)par1;
        this.ySizeFloat = (float)par2;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor3f(1F, 1F, 1F);
        this.mc.getTextureManager().bindTexture(Textures.Gui.IMPLANT_GUI);
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        GL11.glEnable(3042);

        drawPlayerModel(k + 51, l + 75, 30, (float)(k + 51) - this.xSizeFloat, (float)(l + 75 - 50) - this.ySizeFloat, this.mc.thePlayer);
    }

    //
    //DRAWS PLAYER
    //
    public static void drawPlayerModel(int x, int y, int scale, float yaw, float pitch, EntityLivingBase playerdrawn)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, 50.0F);
        GL11.glScalef((float) (-scale), (float) scale, (float) scale);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = playerdrawn.renderYawOffset;
        float f3 = playerdrawn.rotationYaw;
        float f4 = playerdrawn.rotationPitch;
        float f5 = playerdrawn.prevRotationYawHead;
        float f6 = playerdrawn.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(pitch / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        playerdrawn.renderYawOffset = (float)Math.atan((double)(yaw / 40.0F)) * 20.0F;
        playerdrawn.rotationYaw = (float)Math.atan((double)(yaw / 40.0F)) * 40.0F;
        playerdrawn.rotationPitch = -((float)Math.atan((double)(pitch / 40.0F))) * 20.0F;
        playerdrawn.rotationYawHead = playerdrawn.rotationYaw;
        playerdrawn.prevRotationYawHead = playerdrawn.rotationYaw;
        GL11.glTranslatef(0.0F, playerdrawn.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(playerdrawn, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        playerdrawn.renderYawOffset = f2;
        playerdrawn.rotationYaw = f3;
        playerdrawn.rotationPitch = f4;
        playerdrawn.prevRotationYawHead = f5;
        playerdrawn.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}

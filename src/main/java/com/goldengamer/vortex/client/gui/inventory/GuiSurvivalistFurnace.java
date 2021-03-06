package com.goldengamer.vortex.client.gui.inventory;

import com.goldengamer.vortex.container.ContainerSurvivalistFurnace;
import com.goldengamer.vortex.reference.Textures;
import com.goldengamer.vortex.tileentity.TileEntitySurvivalistFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * Created by golde on 24/09/2015.
 */
public class GuiSurvivalistFurnace extends GuiContainer
{

    public TileEntitySurvivalistFurnace survivalistFurnace;

    public GuiSurvivalistFurnace(InventoryPlayer inventoryPlayer, TileEntitySurvivalistFurnace entity)
    {
        super(new ContainerSurvivalistFurnace(inventoryPlayer, entity));

        this.survivalistFurnace = entity;

        this.xSize = 176;
        this.ySize = 166;

    }

    public void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String name = "Survivalist Furnace";

        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
       // this.fontRendererObj.drawString(I18n.format("container.container", new Object[0]), 118, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(Textures.Gui.SURVIVALIST_FURNACE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (this.survivalistFurnace.isBurning())
        {
            int k = this.survivalistFurnace.getBurnTimeRemainingScaled(40);
            int j = 40 - k;
            drawTexturedModalRect(guiLeft + 29, guiTop + 65, 176, 0, 40 - j, 10);
        }

        int k = this.survivalistFurnace.getCookProgressScaled(24);
        drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 10, k + 1, 16);
    }
}

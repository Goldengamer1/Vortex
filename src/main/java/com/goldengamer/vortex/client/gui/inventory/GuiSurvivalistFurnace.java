package com.goldengamer.vortex.client.gui.inventory;

import com.goldengamer.vortex.inventory.ContainerSurvivalistFurnace;
import com.goldengamer.vortex.tileentity.TileEntitySurvivalistFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by golde on 24/09/2015.
 */
public class GuiSurvivalistFurnace extends GuiContainer
{

    public TileEntitySurvivalistFurnace survivalistFurnace;

    public GuiSurvivalistFurnace(InventoryPlayer inventoryPlayer, TileEntitySurvivalistFurnace entity)
    {
        super(ContainerSurvivalistFurnace(inventoryPlayer, entity));

        this.survivalistFurnace = entity;

        this.xSize = 176;
        this.ySize = 166;

    }

    public void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String name = this.survivalistFurnace.hasCustomInventoryName() ? this.survivalistFurnace.getInventoryName() : I18n.format(this.survivalistFurnace.getInventoryName(), new Object[0]);

        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 128, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        //NOOP
    }
}

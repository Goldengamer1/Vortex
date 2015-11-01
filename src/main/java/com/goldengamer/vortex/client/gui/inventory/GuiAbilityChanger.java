package com.goldengamer.vortex.client.gui.inventory;

import com.goldengamer.vortex.container.ContainerAbilityChanger;
import com.goldengamer.vortex.reference.Textures;
import com.goldengamer.vortex.tileentity.TileEntityAbilityChanger;
import com.goldengamer.vortex.utility.interfaces.implantClasses.IWarrior;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

/**
 * Created by golde on 21/10/2015.
 */
@SideOnly(Side.CLIENT)
public class GuiAbilityChanger extends GuiContainer
{
    public TileEntityAbilityChanger tileEntityAbilityChanger;
    private static final ResourceLocation ABILITY_CHANGER = new ResourceLocation("vortex:textures/gui/container/abilityChanger.png");

    public GuiAbilityChanger(InventoryPlayer invPlayer, World world,TileEntityAbilityChanger abilityChanger, EntityPlayer player) {
        super(new ContainerAbilityChanger(invPlayer, world,abilityChanger, player));
        this.tileEntityAbilityChanger = abilityChanger;

        this.xSize = 230;
        this.ySize = 219;
    }

    public void onGuiClosed()
    {
        super.onGuiClosed();
    }

    protected void drawGuiContainerForgroundLayer(int i, int j)
    {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("Ability Changer"), 100, 5, 0x000000);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        this.mc.getTextureManager().bindTexture(ABILITY_CHANGER);

        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

   // public void drawButton(Minecraft mc, int xx, int yy)
    //{
    //}
}

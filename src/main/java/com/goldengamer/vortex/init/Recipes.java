package com.goldengamer.vortex.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by golde on 15/09/2015.
 */
public class Recipes
{
    public static void init()
    {
        //Shaped Recipes
        //GameRegistry.addRecipe(new ItemStack(ModItems.testItem), " s ", "sss", " s ", 's', new ItemStack(Items.stick));

        //Shapeless Recipes
        //GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTest), new ItemStack(ModItems.testItem), new ItemStack(ModItems.testItem));

        //Shaped OreDic- Recipes
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.testItem), " s ", "sss", " s ", 's', "stickWood"));
        //Shapless OreDic- Recipes
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blockTest), new ItemStack(ModItems.testItem), new ItemStack(ModItems.testItem)));
    }
}

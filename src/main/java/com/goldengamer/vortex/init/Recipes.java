package com.goldengamer.vortex.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
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

        //Smelting Recipes
        GameRegistry.addSmelting(ModItems.TEST_ITEM, new ItemStack(ModBlocks.BLOCK_TEST), 50);

        //Shaped OreDic- Recipes
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.TEST_ITEM, 4), " s ", "sss", " s ", 's', "stickWood"));
        //Shapless OreDic- Recipes
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.BLOCK_TEST), new ItemStack(ModItems.IRON_HAMMER, 1 , OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.TEST_ITEM)));
    }
}

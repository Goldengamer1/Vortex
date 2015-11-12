package com.goldengamer.vortex.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
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
        GameRegistry.addRecipe(new ItemStack(ModItems.SPIKED_BASE), "   ", "zzz", "xxx", 'z', new ItemStack(Items.iron_ingot), 'x', new ItemStack(ModItems.IRON_SPIKE));
        GameRegistry.addRecipe(new ItemStack(ModItems.SPIKED_CLAWS), "v v", "z z", "x x", 'z', new ItemStack(Items.iron_ingot), 'x', new ItemStack(ModItems.SPIKED_BASE), 'v', new ItemStack(Items.slime_ball));
        GameRegistry.addRecipe(new ItemStack(ModItems.IRON_SHARPENER), "vvv", "xzx", "vvv", 'z', new ItemStack(Blocks.iron_block), 'x', new ItemStack(Items.diamond), 'v', new ItemStack(Items.flint));
        GameRegistry.addRecipe(new ItemStack(ModItems.POKING_STICK), "  v", "zx ", "zz ", 'z', new ItemStack(Items.stick), 'x', new ItemStack(Blocks.sticky_piston), 'v', new ItemStack(ModItems.SHARPENED_STICK));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.BLOCK_SURVIVALIST_FURNACE_IDLE), "zxz", "zvz", "zcz", 'z', new ItemStack(Blocks.stonebrick), 'x', new ItemStack(Blocks.furnace), 'v', new ItemStack(Blocks.coal_block), 'c', new ItemStack(Items.bucket));
        //Shapeless Recipes
        //GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockTest), new ItemStack(ModItems.testItem), new ItemStack(ModItems.testItem));

        //Smelting Recipes
        //GameRegistry.addSmelting(ModItems.TEST_ITEM, new ItemStack(ModBlocks.BLOCK_TEST), 50);

        //Shaped OreDic- Recipes
        //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.TEST_ITEM, 4), " s ", "sss", " s ", 's', "stickWood"));
        //Shapless OreDic- Recipes
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.IRON_SPIKE), new ItemStack(ModItems.IRON_SHARPENER, 1 , OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_ingot), new ItemStack(Items.flint)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.SHARPENED_STICK), new ItemStack(ModItems.IRON_SHARPENER, 1 , OreDictionary.WILDCARD_VALUE), new ItemStack(Items.stick)));
    }
}

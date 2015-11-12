package com.goldengamer.vortex.utility;

import com.goldengamer.vortex.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

/**
 * Created 11/11/2015.

 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 */

public final class IconHelper {

    public static IIcon forName(IIconRegister ir, String name) {
        return ir.registerIcon(Reference.LOWERCASE_MOD_ID + name);
    }

    public static IIcon forName(IIconRegister ir, String name, String dir) {
        return ir.registerIcon(Reference.LOWERCASE_MOD_ID + dir + "/" + name);
    }

    public static IIcon forBlock(IIconRegister ir, Block block) {
        return forName(ir, block.getUnlocalizedName().replaceAll("tile\\.", ""));
    }

    public static IIcon forBlock(IIconRegister ir, Block block, int i) {
        return forBlock(ir, block, Integer.toString(i));
    }

    public static IIcon forBlock(IIconRegister ir, Block block, int i, String dir) {
        return forBlock(ir, block, Integer.toString(i), dir);
    }

    public static IIcon forBlock(IIconRegister ir, Block block, String s) {
        return forName(ir, block.getUnlocalizedName().replaceAll("tile\\.", "") + s);
    }

    public static IIcon forBlock(IIconRegister ir, Block block, String s, String dir) {
        return forName(ir, block.getUnlocalizedName().replaceAll("tile\\.", "") + s, dir);
    }

    public static IIcon forItem(IIconRegister ir, Item item) {
        return forName(ir, item.getUnlocalizedName().replaceAll("item\\.", ""));
    }

    public static IIcon forItem(IIconRegister ir, Item item, int i) {
        return forItem(ir, item, Integer.toString(i));
    }

    public static IIcon forItem(IIconRegister ir, Item item, String s) {
        return forName(ir, item.getUnlocalizedName().replaceAll("item\\.", "") + s);
    }

}

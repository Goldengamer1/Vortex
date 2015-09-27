package com.goldengamer.vortex.block;

import com.goldengamer.vortex.Vortex;
import com.goldengamer.vortex.creativetab.CreativeTabVortex;
import com.goldengamer.vortex.handler.ConfigurationHandler;
import com.goldengamer.vortex.init.ModBlocks;
import com.goldengamer.vortex.reference.Reference;
import com.goldengamer.vortex.tileentity.TileEntitySurvivalistFurnace;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by golde on 23/09/2015.
 */
public class BlockSurvivalistFurnace extends BlockContainer {

    private final boolean isActive;

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    private static boolean keepInventory;
    private Random rand = new Random();

    public BlockSurvivalistFurnace(boolean isActive) {
        super(Material.iron);
        this.setHardness(3.0F);
        this.isActive = isActive;
    }

    public Item getItemDropped(int i, Random random , int j)
    {
        return Item.getItemFromBlock(ModBlocks.BLOCK_SURVIVALIST_FURNACE_IDLE);
    }

    public  void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            Block b1 = world.getBlock(x, y, z - 1);
            Block b2 = world.getBlock(x, y ,z + 1);
            Block b3 = world.getBlock(x - 1, y ,z );
            Block b4 = world.getBlock(x + 1, y ,z );

            byte b0 = 3;

            if (b1.func_149730_j() && !b2.func_149730_j())
            {
                b0 = 3;
            }
            if (b2.func_149730_j() && !b1.func_149730_j())
            {
                b0 = 2;
            }
            if (b3.func_149730_j() && !b4.func_149730_j())
            {
                b0 = 5;
            }
            if (b4.func_149730_j() && !b3.func_149730_j())
            {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            //TODO CHANGE THIS TO A GUI HANDLER THAT USES THE GUIs.name
            FMLNetworkHandler.openGui(player, Vortex.instance, 0, world, x, y, z);
        }
        return true;
    }


    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        return new TileEntitySurvivalistFurnace();
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y ,int z, Random random)
    {
        if(this.isActive && ConfigurationHandler.SurvivalistFurnaceParticals)
        {
            int l = world.getBlockMetadata(x, y, z);
            float f = (float)x + 0.5F;
            float f1 = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)z + 0.5F;
            float f3 = 0.52F;
            float f4 = random.nextFloat() * 0.6F - 0.3F;

            if (l == 4)
            {
                world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 5)
            {
                world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2)
            {
                world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3)
            {
                world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }

        }
    }

    public void onBlockPlacedBy(World world, int x, int y, int z , EntityLivingBase entityplayer, ItemStack itemstack)
    {
        int l = MathHelper.floor_double((double)(entityplayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0 )
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (l == 1 )
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if (l == 2 )
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (l == 3 )
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
        if (itemstack.hasDisplayName())
        {
            ((TileEntitySurvivalistFurnace)world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
        }
    }


//////////////////////////////
////////TEXTURES START////////
//////////////////////////////
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Reference.LOWERCASE_MOD_ID + ":" + "BLOCK_SURVIVALIST_FURNACE_SIDE");
        this.iconFront = iconRegister.registerIcon(Reference.LOWERCASE_MOD_ID + ":" + (this.isActive ? "BLOCK_SURVIVALIST_FURNACE_FRONT_ON" : "BLOCK_SURVIVALIST_FURNACE_FRONT_OFF"));
        this.iconTop = iconRegister.registerIcon(Reference.LOWERCASE_MOD_ID + ":" + "BLOCK_SURVIVALIST_FURNACE_TOP");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
       // return side == 1 ? this.iconTop : (side == 0 ? this.iconTop : (side != metadata ? this.blockIcon : this.iconFront));
        return metadata == 0 && side == 3 ? this.iconFront : side == 1 ? this.iconTop : (side == 0 ? this.iconTop: (side == metadata ? this.iconFront : this.blockIcon));
    }
//////////////////////////////
////////TEXTURES END//////////
//////////////////////////////

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    public static void updateSurvivalistFurnaceBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord)
    {
        int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

        TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
        keepInventory = true;

        if (active){
            worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.BLOCK_SURVIVALIST_FURNACE_ACTIVE);
        }else{
            worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.BLOCK_SURVIVALIST_FURNACE_IDLE);
        }

        keepInventory = false;

        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block oldblock, int oldMetadata)
    {
        if (!keepInventory)
        {
            TileEntitySurvivalistFurnace tileentity = (TileEntitySurvivalistFurnace) world.getTileEntity(x , y, z);

            if (tileentity != null)
            {
                for (int i = 0; i < tileentity.getSizeInventory(); i++)
                {
                    ItemStack itemStack = tileentity.getStackInSlot(i);

                    if (itemStack != null)
                    {
                        float f = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                        while (itemStack.stackSize > 0)
                        {
                            int j = this.rand.nextInt(21) + 10;

                            if (j > itemStack.stackSize)
                            {
                                j = itemStack.stackSize;
                            }
                            itemStack.stackSize -= j;

                            EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), ((double)(float)z + f2), new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));

                            if (itemStack.hasTagCompound())
                            {
                                item.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
                            }
                            world.spawnEntityInWorld(item);
                        }
                    }
                }
                world.func_147453_f(x, y, z, oldblock);
            }
        }
        super.breakBlock(world, x, y, z, oldblock, oldMetadata);
    }

    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemFromBlock(ModBlocks.BLOCK_SURVIVALIST_FURNACE_IDLE);
    }
}
package com.builtbroken.woolanvil.content.block;

import com.builtbroken.woolanvil.WoolAnvils;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockAnvil extends BlockFalling
{
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyInteger DAMAGE = PropertyInteger.create("damage", 0, 2);
    protected static final AxisAlignedBB X_AXIS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D);
    protected static final AxisAlignedBB Z_AXIS_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D);

    public int cap = 10;
    public double durabilityMultiplier = 1;
    public double enchantability = 1;

    public BlockAnvil()
    {
        super(Material.CLOTH);
        setDefaultState(getDefaultState().withProperty(DAMAGE, 0).withProperty(FACING, EnumFacing.NORTH));
        setLightOpacity(0);
        setCreativeTab(CreativeTabs.DECORATIONS);
        setHardness(1.0F);
        setSoundType(SoundType.CLOTH);
        setResistance(10.0F);
    }

    public static IBlockState damage(IBlockState state)
    {
        if(state.getProperties().containsKey(DAMAGE))
        {
            final int damage = state.getValue(DAMAGE);
            if (damage == 0 || damage == 1)
            {
                return state.withProperty(DAMAGE, damage + 1);
            }
        }
        return null;
    }

    @Override
    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        EnumFacing enumfacing = state.getValue(FACING);
        return enumfacing.getAxis() == EnumFacing.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
    }

    @Override
    @Nonnull
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    @Nonnull
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        final EnumFacing enumfacing = placer.getHorizontalFacing().rotateY();
        return getDefaultState().withProperty(DAMAGE, meta % 3).withProperty(FACING, enumfacing);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            playerIn.openGui(WoolAnvils.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this));
        items.add(new ItemStack(this, 1, 1));
        items.add(new ItemStack(this, 1, 2));
    }

    //<editor-fold desc="Entity">
    @Override
    protected void onStartFalling(EntityFallingBlock fallingEntity)
    {
        fallingEntity.setHurtEntities(false);
    }

    @Override
    public void onEndFalling(World worldIn, BlockPos pos, IBlockState fallingState, IBlockState hitState)
    {
        worldIn.playEvent(1031, pos, 0); //TODO change to sheep sound
    }

    @Override
    public void onBroken(World worldIn, BlockPos pos)
    {
        worldIn.playEvent(1029, pos, 0); //TODO change to sheep sound
    }

    //</editor-fold>

    //<editor-fold desc="BlockState">
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 3)).withProperty(DAMAGE, Integer.valueOf((meta & 15) >> 2));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
        i = i | ((Integer) state.getValue(DAMAGE)).intValue() << 2;
        return i;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(DAMAGE).intValue();
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.getBlock() != this ? state : state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, DAMAGE});
    }
    //</editor-fold>

    //<editor-fold desc="BlockProps">
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isToolEffective(String type, IBlockState state)
    {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    //</editor-fold>
}
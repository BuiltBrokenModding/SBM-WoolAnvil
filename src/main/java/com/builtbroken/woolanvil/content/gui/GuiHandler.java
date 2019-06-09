package com.builtbroken.woolanvil.content.gui;

import com.builtbroken.woolanvil.content.block.BlockAnvil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        final BlockPos pos = new BlockPos(x, y, z);
        final IBlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() instanceof BlockAnvil)
        {
            return new ContainerGenericAnvil(player.inventory, world, pos, player, (BlockAnvil) blockState.getBlock());
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        final BlockPos pos = new BlockPos(x, y, z);
        final IBlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() instanceof BlockAnvil)
        {
            return new GuiGenericAnvil(player.inventory, world, (BlockAnvil) blockState.getBlock());
        }
        return null;
    }
}





package com.builtbroken.woolanvil.content.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemAnvilBlock extends ItemBlock
{
    public ItemAnvilBlock(Block block)
    {
        super(block);
        setRegistryName(block.getRegistryName());
    }

    @Override
    public String getTranslationKey(ItemStack stack)
    {
        return super.getTranslationKey() + "." + getMetadata(stack.getItemDamage());
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage % 3;
    }
}
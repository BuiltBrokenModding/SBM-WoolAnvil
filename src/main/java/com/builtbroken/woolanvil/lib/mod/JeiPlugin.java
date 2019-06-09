package com.builtbroken.woolanvil.lib.mod;

import com.builtbroken.woolanvil.WoolAnvils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JeiPlugin implements IModPlugin
{
    @Override
    public void register(IModRegistry registry)
    {
        registry.addRecipeCatalyst(new ItemStack(WoolAnvils.block), VanillaRecipeCategoryUid.ANVIL);
    }
}

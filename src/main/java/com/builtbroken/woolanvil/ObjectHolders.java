package com.builtbroken.woolanvil;

import com.builtbroken.woolanvil.generic.BlockGenericAnvil;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ObjectHolders {
  @GameRegistry.ObjectHolder("extraanvils:stone_anvil")
  public static BlockGenericAnvil blockWoolAnvil;

  @GameRegistry.ObjectHolder("extraanvils:stone_anvil_chipped")
  public static BlockGenericAnvil blockWoolAnvilChipped;

  @GameRegistry.ObjectHolder("extraanvils:stone_anvil_damaged")
  public static BlockGenericAnvil blockWoolAnvilDamaged;
}

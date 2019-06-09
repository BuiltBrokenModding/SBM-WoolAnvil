package com.builtbroken.woolanvil;

import com.builtbroken.woolanvil.generic.BlockGenericAnvil;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT,modid = WoolAnvils.MODID)
public class ClientProxy extends CommonProxy {

  @SubscribeEvent
  public static void registerModels(ModelRegistryEvent event) {
    for (BlockGenericAnvil anvil: WoolAnvils.anvils) anvil.registerModel();
  }
}

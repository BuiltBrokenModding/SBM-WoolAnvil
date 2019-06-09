package com.builtbroken.woolanvil;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Dark(DarkGuardsman, Robert) on 6/9/2019.
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = WoolAnvils.DOMAIN)
public class ClientReg
{
    @SubscribeEvent
    public static void registerModel(ModelRegistryEvent event)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoolAnvils.block), 0, new ModelResourceLocation(WoolAnvils.block.getRegistryName(), "damage=0,facing=north"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoolAnvils.block), 1, new ModelResourceLocation(WoolAnvils.block.getRegistryName(), "damage=1,facing=north"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(WoolAnvils.block), 2, new ModelResourceLocation(WoolAnvils.block.getRegistryName(), "damage=2,facing=north"));
    }
}

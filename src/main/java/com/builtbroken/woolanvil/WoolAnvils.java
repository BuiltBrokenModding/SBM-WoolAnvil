package com.builtbroken.woolanvil;

import com.builtbroken.woolanvil.content.block.BlockAnvil;
import com.builtbroken.woolanvil.content.block.ItemAnvilBlock;
import com.builtbroken.woolanvil.content.gui.GuiHandler;
import com.builtbroken.woolanvil.lib.network.PacketHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = WoolAnvils.DOMAIN, name = "[SBM] Wool Anvil", version = WoolAnvils.VERSION)
@Mod.EventBusSubscriber(modid = WoolAnvils.DOMAIN)
public class WoolAnvils
{
    public static final String DOMAIN = "sbmwoolanvil";

    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String MC_VERSION = "@MC@";
    public static final String VERSION = MC_VERSION + "-" + MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;

    @Mod.Instance(WoolAnvils.DOMAIN)
    public static WoolAnvils INSTANCE;

    public static final String BLOCK_NAME = DOMAIN + ":anvil";

    @GameRegistry.ObjectHolder(BLOCK_NAME)
    public static BlockAnvil block;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        PacketHandler.registerMessages(WoolAnvils.DOMAIN);
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().register(new BlockAnvil()
                .setRegistryName(new ResourceLocation(BLOCK_NAME))
                .setTranslationKey(BLOCK_NAME)
        );
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new ItemAnvilBlock(block));
    }
}

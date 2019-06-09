package com.builtbroken.woolanvil;

import com.builtbroken.woolanvil.generic.BlockGenericAnvil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mod(modid = WoolAnvils.MODID,name = WoolAnvils.NAME, version = WoolAnvils.MODVERSION/*, dependencies = "required-after:forge@[14.23.5.2796)"*/)
public class WoolAnvils
{
    public static final String MODID = "woolanvil";
    public static final String NAME = "Wool Anvil";
    public static final String MODVERSION = "@VERSION@";

    public static final HashMap<BlockGenericAnvil,BlockGenericAnvil> anvilDamageMap = new HashMap<>();

    public static final List<BlockGenericAnvil> anvils = new ArrayList<>();

    public static ArrayList<String> strings = new ArrayList<>();

    public static CreativeTabs creativeTab = new CreativeTabs(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ObjectHolders.blockWoolAnvil);
        }
    };

    public static Logger logger = LogManager.getLogger();

    @Mod.Instance
    public static WoolAnvils instance;
    @SidedProxy(clientSide = "com.tfar.extraanvils.ClientProxy", serverSide = "com.tfar.extraanvils.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {

        boolean doJson = false;

        if (doJson) {
         //   Scripts.scripts();
        }

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        proxy.init(event);

    //    for (Item item : ForgeRegistries.ITEMS){
    //        if (item instanceof ItemArmor)System.out.println(item.getRegistryName().toString() +" "+ item.getItemEnchantability());
    //    }
    }
}

package com.builtbroken.woolanvil;

import com.google.gson.*;
import com.builtbroken.woolanvil.generic.AnvilProperties;
import com.builtbroken.woolanvil.generic.BlockGenericAnvil;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;

@Mod.EventBusSubscriber
public class Setup {

  public static void writeConfig() {

    File file = new File("config/woolanvil.json");
    if (file.exists()) return;

    BufferedInputStream in = new BufferedInputStream(Setup.class.
            getResourceAsStream("/default.json"));
    String s = null;
    try {
      s = IOUtils.toString(in, Charset.defaultCharset());
    } catch (IOException e) {
      e.printStackTrace();
    }

    JsonObject convertedObject = new Gson().fromJson(s, JsonObject.class);

    String s1 = prettyJson(convertedObject);

    try {
      FileWriter writer = new FileWriter(file);
      writer.write(s1);
      writer.flush();
    } catch (IOException ugh) {
      //it be like that sometimes
      ugh.printStackTrace();
    }
  }

  public static String prettyJson(JsonObject j) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.toJson(j);
  }

  @SubscribeEvent
  public static void registerBlocks(RegistryEvent.Register<Block> event) {
    writeConfig();

    IForgeRegistry<Block> registry = event.getRegistry();
    try {

      FileReader reader = new FileReader("config/woolanvil.json");
      JsonElement json = new JsonParser().parse(reader);
      JsonObject object = json.getAsJsonObject();
        String material = "wool";

        try {
          JsonObject obj = object.getAsJsonObject(material);
          boolean flag = obj.get("enabled").getAsBoolean();

          if (flag) {
            WoolAnvils.logger.info("registering " + material + " anvil");
            for (EnumVariants variant : EnumVariants.values()) {
              registry.register(new BlockGenericAnvil(new AnvilProperties(material, obj.get("level cap").getAsInt(), obj.get("weight").getAsDouble(), 1, obj.get("durability multiplier").getAsDouble(),obj.get("enchantability").getAsDouble()), variant));
            }
          } else {
            WoolAnvils.logger.info("skipping " + material + " anvil");
          }
          // eat the exception
        } catch (Exception ignored) {
          WoolAnvils.logger.error("Error registering "+ material + ", skipping");
        }

    } catch (IOException ofcourse) {
      throw new RuntimeException(ofcourse);
    }
  }

}
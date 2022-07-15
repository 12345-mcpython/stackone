package com.laosun.stackone;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

@Mod(StackOneMod.MODID)
public class StackOneMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "stackone";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public StackOneMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        File dir = new File("config/stackone");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "ignore_item.json");
        if (!file.isFile()) {
            try {
                if (!file.createNewFile()) {
                    LOGGER.error("Fail to create file!");
                }
            } catch (IOException e) {
                LOGGER.error("Fail to create file!");
                e.printStackTrace();
            }
        }
        char[] a1 = null;
        try {
            FileReader fileReader = new FileReader(file);
            a1 = new char[(int) file.length()];
            fileReader.read(a1);
        } catch (IOException e) {
            LOGGER.error("Fail to open file!");
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonArray jsonArray;
        ArrayList<String> ignoreItems = new ArrayList<>();
        try {
            if (a1 != null) {
                jsonArray = JsonParser.parseString(String.copyValueOf(a1)).getAsJsonArray();
                for (JsonElement user : jsonArray) {
                    IgnoreItem ignore = gson.fromJson(user, IgnoreItem.class);
                    ignoreItems.add(ignore.item);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Json has syntax error!");
            e.printStackTrace();
        }
        for (Item i : ForgeRegistries.ITEMS) {
            Field a;
            boolean is = false;
            for (String j : ignoreItems) {
                if (j.equals(i.getDescriptionId())) {
                    is = true;
                    break;
                }
            }
            if (is) {
                continue;
            }
            try {
                a = Item.class.getDeclaredField("maxStackSize");
            } catch (NoSuchFieldException e) {
                try {
                    a = Item.class.getDeclaredField("f_41370_");
                } catch (NoSuchFieldException ex) {
                    LOGGER.error("Failed to get field!!!");
                    return;
                }
            }
            a.setAccessible(true);
            try {
                a.set(i, 1);
            } catch (IllegalAccessException e) {
                LOGGER.error("Failed to set field!!!");
            }
        }
    }
}

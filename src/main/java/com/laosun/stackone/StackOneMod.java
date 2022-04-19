package com.laosun.stackone;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;


import java.lang.reflect.Field;
import java.util.logging.Logger;
import java.util.logging.Level;
public class StackOneMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        for (Item i : Registry.ITEM) {
            try {
                LOGGER.info(i.getTranslationKey());
                Class<? extends Item> a = Item.class;
                Field f2;
                try {
                    f2 = a.getDeclaredField("maxCount");
                } catch (NoSuchFieldException e) {
                    f2 = a.getDeclaredField("field_8013");
                }
                f2.setAccessible(true);
                f2.set(i, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

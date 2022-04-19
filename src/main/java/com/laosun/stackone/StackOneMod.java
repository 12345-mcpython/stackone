package com.laosun.stackone;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("examplemod")
public class StackOneMod {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public StackOneMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        // field_77777_bU
    }

    private void setup(final FMLCommonSetupEvent event) {
        try {
            for (Item i : ForgeRegistries.ITEMS) {
                Field field;
                try {
                    field = Item.class.getDeclaredField("maxStackSize");
                } catch (NoSuchFieldException e) {
                    field = Item.class.getDeclaredField("field_77777_bU");
                }
                field.setAccessible(true);
                field.setInt(i, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

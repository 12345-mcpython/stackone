package com.laosun.stackone;

import net.minecraft.world.item.Item;

import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("stackone")
public class StackOneMod {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public StackOneMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // MinecraftForge.EVENT_BUS.register(StackOneEvent.class);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        for (Item i : ForgeRegistries.ITEMS) {
            try {
                LOGGER.debug(i.getRegistryName());
                Class<? extends Item> a = Item.class;
                Field f2;
                try {
                    f2 = a.getDeclaredField("maxStackSize");
                } catch (NoSuchFieldException e) {
                    f2 = a.getDeclaredField("f_41370_");
                }
                f2.setAccessible(true);
                f2.set(i, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Item
    }

}

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

@Mod("stackone")
public class StackOneMod {
    private static final Logger LOGGER = LogManager.getLogger();

    public StackOneMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(StackOneEvent.class);
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Use the java's reflect to edit the max stack size.
    private void setup(final FMLCommonSetupEvent event) {
        for (Item i : ForgeRegistries.ITEMS) {
            try {
                LOGGER.debug(i.getRegistryName());
                Class<? extends Item> a = Item.class;
                Field f2;
                try {
                    f2 = a.getDeclaredField("maxStackSize");
                }catch (NoSuchFieldException e){
                    f2 = a.getDeclaredField("field_77777_bU");
                }
                f2.setAccessible(true);
                f2.set(i, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

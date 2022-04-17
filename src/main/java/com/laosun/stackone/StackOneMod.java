package com.laosun.stackone;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
        for (int i = 1; ; i++) {
            try {
                Item item = Item.byId(i);
                Item air = Item.byId(0);
                // If id not found, item returns air.
                if (item.equals(air)) {
                    break;
                }
                LOGGER.debug(item.getRegistryName());
                Class<? extends Item> a = Item.class;
                Field f2;
                try {
                    f2 = a.getDeclaredField("maxStackSize");
                }catch (NoSuchFieldException e){
                    f2 = a.getDeclaredField("field_77777_bU");
                }
                f2.setAccessible(true);
                f2.set(item, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

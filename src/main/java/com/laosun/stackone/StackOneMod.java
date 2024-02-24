package com.laosun.stackone;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Mod(StackOneMod.MODID)
public class StackOneMod {
    public static final String MODID = "stackone";
    public static final Logger LOGGER = LogUtils.getLogger();

    public StackOneMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
    }

    @SuppressWarnings("deprecation")
    private void commonSetup(final FMLCommonSetupEvent event) {
        ArrayList<String> ignoreItems = IgnoreItem.getIgnoreItems();
        for (Item i : ForgeRegistries.ITEMS) {
            if (ignoreItems.contains(i.builtInRegistryHolder().key().location().toString())) {
                continue;
            }
            Field a;

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

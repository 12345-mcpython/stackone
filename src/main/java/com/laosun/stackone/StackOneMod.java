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

    public boolean isItem(ArrayList<String> descriptionId, Item item) {
        boolean is = false;
        for (String i : descriptionId) {
            if (i.equals(item.getDescriptionId())) {
                is = true;
                break;
            }
        }
        return is;
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        ArrayList<String> ignoreItems = IgnoreItem.getIgnoreItems();
        for (Item i : ForgeRegistries.ITEMS) {
            Field a;
            if (isItem(ignoreItems, i)) {
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

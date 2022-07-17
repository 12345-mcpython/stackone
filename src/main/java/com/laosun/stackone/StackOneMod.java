package com.laosun.stackone;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Objects;

@Mod(modid = StackOneMod.MODID, name = StackOneMod.NAME, version = StackOneMod.VERSION)
public class StackOneMod {
    public static final String MODID = "stackone";
    public static final String NAME = "Stack One Mod";
    public static final String VERSION = "1.12.2-1.0.1";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    public boolean isItem(ArrayList<String> descriptionId, Item item) {
        boolean is = false;
        for (String i : descriptionId) {
            if (Objects.equals(item.getRegistryName(), new ResourceLocation(i))) {
                is = true;
                break;
            }
        }
        return is;
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ArrayList<String> a = IgnoreItem.getIgnoreItems();
        for(Item item : ForgeRegistries.ITEMS){
            if(isItem(a, item)){
                continue;
            }

            item.setMaxStackSize(1);
        }

    }
}

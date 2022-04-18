package com.laosun.stackone;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = StackOneMod.MODID, name = StackOneMod.NAME, version = StackOneMod.VERSION)
public class StackOneMod {
    public static final String MODID = "stackone";
    public static final String NAME = "Stack One Mod";
    public static final String VERSION = "1.12.2-1.0.0";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(StackModEvent.class);
        for(Item item : GetItems.create()){
            item.setMaxStackSize(1);
        }
    }
}

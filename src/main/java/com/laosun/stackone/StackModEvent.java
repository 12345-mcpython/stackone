package com.laosun.stackone;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static com.laosun.stackone.StackOneMod.logger;

public class StackModEvent {
    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event){
        event.crafting.setCount(1);
        logger.debug("onCrafting attacked!");
    }
}

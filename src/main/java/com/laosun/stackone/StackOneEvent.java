package com.laosun.stackone;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StackOneEvent {
    // private static final Logger LOGGER = LogManager.getLogger();
    @SubscribeEvent
    public static void itemCrafted(PlayerEvent.ItemCraftedEvent event) {
        event.getCrafting().setCount(1);
    }
}
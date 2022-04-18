package com.laosun.stackone;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.impl.registry.sync.FabricRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class StackOneMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("stackone");

	@Override
	public void onInitialize() {
		for(Item i : Registry.ITEM){
			try {
				LOGGER.debug(i.getTranslationKey());
				Class<? extends Item> a = Item.class;
				Field f2;
				try {
					f2 = a.getDeclaredField("maxCount");
				}catch (NoSuchFieldException e){
					f2 = a.getDeclaredField("field_8013");
				}
				f2.setAccessible(true);
				f2.set(i, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		for (int i = 1; ; i++) {
//			try {
//				Item item = Item.byRawId(i);
//				Item air = Item.byRawId(0);
//				if (item.equals(air)) {
//					break;
//				}
//				LOGGER.debug(item.getTranslationKey());
//				Class<? extends Item> a = Item.class;
//				Field f2;
//				try {
//					f2 = a.getDeclaredField("maxCount");
//				}catch (NoSuchFieldException e){
//					f2 = a.getDeclaredField("field_8013");
//				}
//				f2.setAccessible(true);
//				f2.set(item, 1);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
}

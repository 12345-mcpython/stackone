package com.laosun.stackone;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.lang.reflect.Field;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("stackone")
public class StackOneMod {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public StackOneMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        for (Item i : ForgeRegistries.ITEMS) {
            try {
                Field field;
                try {
                    field = Item.class.getDeclaredField("maxStackSize");
                } catch (NoSuchFieldException e) {
                    field = Item.class.getDeclaredField("f_41370_");
                }
                field.setAccessible(true);
                field.setInt(i, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

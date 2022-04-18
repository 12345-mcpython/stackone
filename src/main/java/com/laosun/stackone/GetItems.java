package com.laosun.stackone;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class GetItems {
    public static List<Item> create() {
        List<Item> list = new ArrayList<>();
        for (Item i : ForgeRegistries.ITEMS) {
            list.add(i);
        }
        return list;
    }
}

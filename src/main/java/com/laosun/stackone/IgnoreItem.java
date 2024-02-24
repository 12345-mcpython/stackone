package com.laosun.stackone;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.laosun.stackone.StackOneMod.LOGGER;

public class IgnoreItem {
    public String item;

    public static ArrayList<String> getIgnoreItems() {
        File dir = new File("config/stackone");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "ignore_item.json");
        if (!file.isFile()) {
            try {
                if (!file.createNewFile()) {
                    throw new IOException("Failed to create file!");
                }
            } catch (IOException e) {
                LOGGER.error("Failed to create file!");
                e.printStackTrace();
            }
        }
        char[] a1 = null;
        try (FileReader fileReader = new FileReader(file);) {
            a1 = new char[(int) file.length()];
            fileReader.read(a1);
        } catch (IOException e) {
            LOGGER.error("Failed to open file!");
            e.printStackTrace();
        }

        Gson gson = new Gson();
        JsonArray jsonArray;
        ArrayList<String> ignoreItems = new ArrayList<>();
        try {
            if (a1 != null) {
                jsonArray = JsonParser.parseString(String.copyValueOf(a1)).getAsJsonArray();
                for (JsonElement user : jsonArray) {
                    IgnoreItem ignore = gson.fromJson(user, IgnoreItem.class);
                    ignoreItems.add(ignore.item);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Failed to read JSON file!");
            e.printStackTrace();
        }
        return ignoreItems;
    }
}

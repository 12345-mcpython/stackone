package com.laosun.stackone;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.laosun.stackone.StackOneMod.LOGGER;

public class Stack {
    public String item;
    public short max_stack_size;

    public static Map<String, Short> getStackList() {
        Map<String, Short> map = new HashMap<>();
        File dir = new File("config/stackone");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "max_stack_size.json");
        if (!file.isFile()) {
            try {
                if (!file.createNewFile()) {
                    LOGGER.error("Fail to create file!");
                }
            } catch (IOException e) {
                LOGGER.error("Fail to create file!");
                e.printStackTrace();
            }
        }
        char[] a1 = null;
        try {
            FileReader fileReader = new FileReader(file);
            a1 = new char[(int) file.length()];
            fileReader.read(a1);
        } catch (IOException e) {
            LOGGER.error("Fail to open file!");
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonArray jsonArray;
        try {
            if (a1 != null) {
                jsonArray = JsonParser.parseString(String.copyValueOf(a1)).getAsJsonArray();
                for (JsonElement user : jsonArray) {
                    Stack ignore = gson.fromJson(user, Stack.class);
                    map.put(ignore.item, ignore.max_stack_size);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Json has syntax error!");
            e.printStackTrace();
        }
        return map;
    }
}

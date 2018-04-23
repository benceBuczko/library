package com.threelittlepigs.codecool.libraryManager.Utils;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class JSONUtil {
    public String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}

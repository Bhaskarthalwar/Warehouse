package com.warehouse.application.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.application.repository.Warehouse;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class CommonUtil {

    public static final Warehouse loadFile(String filename) throws IOException {

        File file = null;
        file = ResourceUtils.getFile(filename);
        if (!file.exists()) {
            file = ResourceUtils.getFile("classpath:" + filename);
        }
        String json = new String(Files.readAllBytes(file.toPath()));
        return fromJsonToObject(json, Warehouse.class);
    }

    private static final <T> T fromJsonToObject(String json, Class<T> beanClass) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        T obj = null;
        if (beanClass != null && json != null) {
            obj = mapper.readValue(json, beanClass);
        }
        return obj;
    }
}

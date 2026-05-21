package com.thinkAndGetIt.frontend.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads values from config.properties.
 * Usage: ConfigReader.get("base.url")
 */
public class ConfigReader {

    public static Properties properties = new Properties();


    static {
        try {
            FileInputStream fis= new FileInputStream("src/test/resources/frontendConfig.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties.");
        }
        return value.trim();
    }
}

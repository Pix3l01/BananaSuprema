package com.BiF.BananaSuprema.BananaSuprema.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static Configuration instance = null;
    private final Properties properties;

    private Configuration() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("Properties.properties");
        properties = new Properties();
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public Properties getProperties(){
        return properties;
    }

}

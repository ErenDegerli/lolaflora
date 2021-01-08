package com.lolaflora.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static volatile PropertyReader propInstance;

    private PropertyReader() {
    }

    public static synchronized PropertyReader getInstance() {
        if(propInstance == null) {
            propInstance = new PropertyReader();
        }
        return propInstance;
    }

    public String getProperty(String propertyName) throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(new File("config.properties"));

        prop.load(fileInputStream);

        if(prop.getProperty(propertyName)!= null) {
            return prop.getProperty(propertyName);
        }
        return null;
    }
}

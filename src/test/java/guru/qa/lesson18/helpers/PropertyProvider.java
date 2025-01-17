package guru.qa.lesson18.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertyProvider.class.getClassLoader().getResourceAsStream("auth.properties")) {
            if (input == null) {
                throw new RuntimeException("auth.properties not found");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public static String getProperty(String key) {
        if (System.getProperty(key) != null) {
            return System.getProperty(key);
        }
        if (properties.getProperty(key) != null) {
            return properties.getProperty(key);
        }
        throw new IllegalArgumentException("Property '" + key + "' is not set");
    }
}

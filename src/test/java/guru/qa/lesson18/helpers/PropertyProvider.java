package guru.qa.lesson18.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertyProvider.class.getClassLoader().getResourceAsStream("auth.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.out.println("Warning: auth.properties not found, using system properties only");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public static String getProperty(String key) {
        String value = System.getProperty(key, properties.getProperty(key));
        if (value == null) {
            throw new IllegalArgumentException("Property '" + key + "' is not set");
        }
        return value;
    }
}

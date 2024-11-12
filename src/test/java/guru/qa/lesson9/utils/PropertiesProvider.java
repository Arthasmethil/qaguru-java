package guru.qa.lesson9.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesProvider {
    private final Properties properties;

    public PropertiesProvider() {
        properties = new Properties();
        String filePath = "config.properties";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                throw new RuntimeException("Properties file not found in resources");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public String getLogin() {
        return properties.getProperty("login");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}

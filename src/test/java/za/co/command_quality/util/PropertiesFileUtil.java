package za.co.command_quality.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Azael on 2018-01-16.
 */
public class PropertiesFileUtil {

    public String getPropertyByKey(String propertyFilePath, String key) throws IOException {
        Properties properties = getProperties(propertyFilePath);
        return getPropertyByKey(properties, key);
    }

    public String getPropertyByKey(Properties properties, String key) {
        return properties.containsKey(key) ? properties.getProperty(key) : null;
    }

    public Properties getProperties(String propertyFilePath) throws IOException {
        InputStream propertiesInputStream = null;
        try {
            propertiesInputStream = getClass().getClassLoader().getResourceAsStream(propertyFilePath);
            if (propertiesInputStream != null) {
                Properties properties = new Properties();
                properties.load(propertiesInputStream);
                return properties;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            assert propertiesInputStream != null;
            propertiesInputStream.close();
        }
        throw new FileNotFoundException("property file '" + propertyFilePath + "' not found in the classpath");
    }
}

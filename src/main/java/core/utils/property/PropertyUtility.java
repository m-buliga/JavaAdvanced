package core.utils.property;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropertyUtility {

    private Properties properties;
    public PropertyUtility(String fileName) {
        loadFile(fileName);
    }

    // method to upload a file
    @SneakyThrows(Exception.class)
    private void loadFile(String fileName) {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/test-data/" + fileName + ".properties");
        properties.load(fileInputStream);

    }

    // method to return all data from a file
    public HashMap<String, String> getAllData() {
        HashMap<String, String> testData = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            testData.put(key, properties.getProperty(key));
        }
        return testData;
    }
}
package config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

public class AppLoader {
    public static AppConfig loadConfiguration(String conf) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            File file = new File(conf);
            return mapper.readValue(new File(conf), AppConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

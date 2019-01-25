package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class AppLoader {
    public static AppConfig loadConfiguration(String conf) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(AppLoader.class.getResourceAsStream(conf), AppConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

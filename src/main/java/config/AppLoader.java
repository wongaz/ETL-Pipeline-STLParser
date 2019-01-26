package config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AppLoader {
    static ObjectMapper mapper;
    static {
         mapper = new ObjectMapper(new YAMLFactory());
         mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public static AppConfig loadConfiguration(String conf){
        return loadConfiguration(new File(conf));
    }

    public static AppConfig loadConfiguration(File file){
        try {
            return loadConfiguration(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AppConfig loadConfiguration(InputStream stream) {
        try {
            return mapper.readValue(stream, AppConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

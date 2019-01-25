package config;

import lombok.Data;

import java.util.Map;

@Data
public class AppConfig {
    private String[] stlFilePath;
    private String parseFormat;
    private String distanceMetric;
    private String[] statistics;
    private String[] loadLocation;
    private Map<String, Map<String, String>> extraSpecification;
}

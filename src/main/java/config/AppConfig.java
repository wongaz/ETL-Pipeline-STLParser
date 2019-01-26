package config;

import lombok.Data;

import java.util.Map;

@Data
public class AppConfig {
    private String extractType;
    private Map<String, Map<String, String>> extractConfiguration;
    private String parseFormat;
    private String distanceMetric;
    private String[] statistics;
    private String[] loadType;
    private Map<String, Map<String, String>> loadConfiguration;
}

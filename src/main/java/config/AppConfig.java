package config;

import lombok.Data;

@Data
public class AppConfig {
    String stlFile;
    String parseFormat;
    String distanceMetric;
    String[] statistics;
    String outputFile;
}

package config;

import lombok.Data;

import java.util.Map;

@Data
public class PipelineConfig {
    private String pipelineName;
    private String extractType;
    private Map<String, String> extractConfiguration;
    private String parseFormat;
    private String[] statistics;
    private Map<String, String> statisticsConf;
    private String[] loadType;
    private Map<String, Map<String, String>> loadConf;
}

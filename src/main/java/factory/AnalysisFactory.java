package factory;

import lombok.ToString;

import java.util.Map;

@ToString
public class AnalysisFactory {
    private Map<String, Class> classMap;

    public AnalysisFactory(Map<String, Class> map) {
        classMap = map;
    }
}

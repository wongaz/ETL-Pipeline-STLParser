package factory;

import extract.AbstractExtractor;

import java.util.Map;

public class ExtractorFactory {
    private Map<String, Class> classMap;

    public ExtractorFactory() {
    }

    public ExtractorFactory(Map<String, Class> map) {
        classMap = map;
    }

    public AbstractExtractor getExtractor(String extractorName) {
        Object obj = ReflectionUtil.makeObject(extractorName, classMap);
        if (obj != null) {
            return (AbstractExtractor) obj;
        }
        return null;
    }
}

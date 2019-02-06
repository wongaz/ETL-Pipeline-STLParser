package factory.guice;

import extract.AbstractExtractor;
import factory.ReflectionUtil;

import java.util.Map;

public class GuiceExtractorFactory {
    private Map<String, Class> classMap;


    public GuiceExtractorFactory(Map<String, Class> map) {
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

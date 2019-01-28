package factory;

import java.util.Map;

public class ExtractorFactory {
    private Map<String, Class> classMap;


    public ExtractorFactory(Map<String, Class> map) {
        classMap = map;
    }
}

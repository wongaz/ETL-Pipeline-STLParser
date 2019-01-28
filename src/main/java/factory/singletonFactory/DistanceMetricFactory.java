package factory.singletonFactory;

import java.util.Map;

public class DistanceMetricFactory {
    private static Map<String, Class> classMap;
    private static DistanceMetricFactory factory;

    private DistanceMetricFactory() {

    }

    public static DistanceMetricFactory getInstance() {
        if (factory == null)
            factory = new DistanceMetricFactory();

        return factory;
    }

    public void setClassMap(Map<String, Class> map) {
        classMap = map;
    }
}

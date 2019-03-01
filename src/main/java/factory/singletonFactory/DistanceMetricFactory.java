package factory.singletonFactory;


import statistics.area.componentArea.distanceMetric.IMetric;

import java.util.Map;

public class DistanceMetricFactory {
    private static Map<String, IMetric> classMap;
    private static DistanceMetricFactory factory = new DistanceMetricFactory();

    private DistanceMetricFactory() {

    }

    public static DistanceMetricFactory getInstance() {
        if (factory == null)
            factory = new DistanceMetricFactory();

        return factory;
    }

    public void setClassMap(Map<String, IMetric> map) {
        classMap = map;
    }

    public IMetric getMetric(String metric) {
        return classMap.get(metric);
    }

    public int getClassMapSize() {
        return classMap.size();
    }


    public Map<String, IMetric> getClassMap() {
        return classMap;
    }
}

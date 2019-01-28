package factory.singletonFactory;

import factory.ReflectionUtil;
import statistics.area.componentArea.distanceMetric.IMetric;

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

    public IMetric getMetric(String metric) {
        Object obj = ReflectionUtil.makeObject(metric, classMap);
        if (obj != null) {
            return (IMetric) obj;
        }
        return null;
    }
}

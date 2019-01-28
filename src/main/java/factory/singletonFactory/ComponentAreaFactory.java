package factory.singletonFactory;

import factory.ReflectionUtil;
import statistics.area.componentArea.IComponentArea;

import java.util.Map;

public class ComponentAreaFactory {
    private static Map<String, Class> classMap;
    private static ComponentAreaFactory factory;

    public static ComponentAreaFactory getInstance() {
        if (factory == null)
            factory = new ComponentAreaFactory();

        return factory;
    }

    public void setClassMap(Map<String, Class> map) {
        classMap = map;
    }

    public IComponentArea getComponentArea(String componentName) {
        Object obj = ReflectionUtil.makeObject(componentName, classMap);
        if (obj != null) {
            return (IComponentArea) obj;
        }
        return null;
    }
}

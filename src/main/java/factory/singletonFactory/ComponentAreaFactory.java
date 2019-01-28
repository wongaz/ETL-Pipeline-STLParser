package factory.singletonFactory;

import statistics.area.componentArea.IComponentArea;

import java.util.Map;

public class ComponentAreaFactory {
    private static Map<String, IComponentArea> classMap;
    private static ComponentAreaFactory factory;

    public static ComponentAreaFactory getInstance() {
        if (factory == null)
            factory = new ComponentAreaFactory();

        return factory;
    }

    public void setClassMap(Map<String, IComponentArea> map) {
        classMap = map;
    }

    public IComponentArea getComponentArea(String componentName) {
        return classMap.get(componentName);
    }
}

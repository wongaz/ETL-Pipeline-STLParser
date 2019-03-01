package factory.singletonFactory;

import com.google.inject.Singleton;
import statistics.area.componentArea.IComponentArea;

import java.util.Map;

@Singleton
public class ComponentAreaFactory {
    private static Map<String, IComponentArea> classMap;
    private static ComponentAreaFactory factory = new ComponentAreaFactory();

    public synchronized static ComponentAreaFactory getInstance() {
        if (factory == null)
            factory = new ComponentAreaFactory();

        return factory;
    }

    public void setClassMap(Map<String, IComponentArea> map) {
        classMap = map;
    }

    public int getClassMapSize() {
        return classMap.size();
    }

    public IComponentArea getComponentArea(String componentName) {
        return classMap.get(componentName);
    }
}

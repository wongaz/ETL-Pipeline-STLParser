package factory.singletonFactory;

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
}

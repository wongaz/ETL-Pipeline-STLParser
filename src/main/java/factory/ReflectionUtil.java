package factory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ReflectionUtil {
    public static Object makeObject(String name, Map<String, Class> ClassMap) {
        Class<?> cls = ClassMap.get(name);
        try {
            return cls.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}

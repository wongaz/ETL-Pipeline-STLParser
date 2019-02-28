package factory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ReflectionUtil {
    public static Object makeObject(String name, Map<String, Class> ClassMap) throws NullPointerException {
        Class<?> cls = ClassMap.get(name);
        if (cls == null) throw new NullPointerException();
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

    public static Object makeObject(Class<?> classObject){
        try {
            return classObject.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}

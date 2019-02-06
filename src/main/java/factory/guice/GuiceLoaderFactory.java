package factory.guice;

import factory.ReflectionUtil;
import load.ILoader;
import lombok.ToString;

import javax.inject.Inject;
import java.util.Map;

@ToString
public class GuiceLoaderFactory {

    private Map<String, ILoader> classMap;

    @Inject
    public GuiceLoaderFactory(Map<String, ILoader> map) {
        this.classMap = map;
    }

    public ILoader getLoader(String loader) {
        Object obj = ReflectionUtil.makeObject(classMap.get(loader).getClass());
        if (obj != null) {
            return (ILoader) obj;
        }
        return null;
    }
}

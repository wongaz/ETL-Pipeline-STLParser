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

    public ILoader getLoader(String loaderName) throws NullPointerException {
        ILoader loader = classMap.get(loaderName);
        if (loader == null) {
            throw new NullPointerException();
        }
        Object obj = ReflectionUtil.makeObject(loader.getClass());
        if (obj != null) {
            return (ILoader) obj;
        }
        return null;
    }
}

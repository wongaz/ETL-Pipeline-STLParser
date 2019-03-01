package factory.guice;

import factory.FactoryReflectionUtil;
import factory.LoaderFactory;
import load.ILoader;
import lombok.ToString;

import javax.inject.Inject;
import java.util.Map;

@ToString
public class GuiceLoaderFactory extends LoaderFactory {

    private Map<String, ILoader> classMap;

    @Inject
    public GuiceLoaderFactory(Map<String, ILoader> map) {
        this.classMap = map;
    }

    @Override
    public ILoader getLoader(String loaderName) throws NullPointerException {
        ILoader loader = classMap.get(loaderName);
        if (loader == null) {
            throw new NullPointerException();
        }
        Object obj = FactoryReflectionUtil.makeObject(loader.getClass());
        if (obj != null) {
            return (ILoader) obj;
        }
        return null;
    }
}

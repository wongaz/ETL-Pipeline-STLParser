package factory;

import load.ILoader;
import lombok.ToString;

import java.util.Map;

@ToString
public class LoaderFactory {

    private Map<String, Class> classMap;


    public LoaderFactory(Map<String, Class> map) {
        classMap = map;
    }

    public ILoader getLoader(String loader) {

        return null;
    }
}

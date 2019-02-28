package factory.guice;

import com.google.inject.Inject;
import extract.AbstractExtractor;
import factory.ReflectionUtil;

import java.util.Map;

public class GuiceExtractorFactory {
    private Map<String, AbstractExtractor> classMap;

    @Inject
    public GuiceExtractorFactory(Map<String, AbstractExtractor> map) {
        classMap = map;
    }

    public AbstractExtractor getExtractor(String extractorName) throws NullPointerException {

        AbstractExtractor extractor = classMap.get(extractorName);
        if (extractor == null) {
            throw new NullPointerException();
        }
        Object obj = ReflectionUtil.makeObject(extractor.getClass());
        if (obj != null) {
            return (AbstractExtractor) obj;
        }
        return null;
    }
}

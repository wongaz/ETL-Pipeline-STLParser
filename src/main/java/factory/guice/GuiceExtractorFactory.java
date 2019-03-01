package factory.guice;

import com.google.inject.Inject;
import extract.AbstractExtractor;
import factory.ExtractorFactory;
import factory.FactoryReflectionUtil;
import lombok.ToString;

import java.util.Map;

@ToString
public class GuiceExtractorFactory extends ExtractorFactory {
    private Map<String, AbstractExtractor> classMap;

    @Inject
    public GuiceExtractorFactory(Map<String, AbstractExtractor> map) {
        classMap = map;
    }

    @Override
    public AbstractExtractor getExtractor(String extractorName) throws NullPointerException {

        AbstractExtractor extractor = classMap.get(extractorName);
        if (extractor == null) {
            throw new NullPointerException();
        }
        Object obj = FactoryReflectionUtil.makeObject(extractor.getClass());
        if (obj != null) {
            return (AbstractExtractor) obj;
        }
        return null;
    }
}

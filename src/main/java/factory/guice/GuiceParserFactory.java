package factory.guice;

import com.google.inject.Inject;
import extract.parser.AbstractParser;
import factory.ReflectionUtil;
import lombok.Data;

import java.util.Map;

@Data
public class GuiceParserFactory {
    private Map<String, Class> classMap;

    @Inject
    public GuiceParserFactory(Map<String, Class> map) {
        classMap = map;
    }

    public AbstractParser getAbstractParser(String parserName) {
        Object obj = ReflectionUtil.makeObject(parserName, classMap);
        if (obj != null) {
            return (AbstractParser) obj;
        }
        return null;
    }

}

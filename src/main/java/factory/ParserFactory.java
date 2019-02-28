package factory;

import extract.parser.AbstractParser;
import lombok.Data;

import java.util.Map;

@Data
public class ParserFactory {
    private Map<String, Class> classMap;

    public ParserFactory() {
    }

    public ParserFactory(Map<String, Class> map) {
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

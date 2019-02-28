package factory.guice;

import com.google.inject.Inject;
import extract.parser.AbstractParser;
import factory.ReflectionUtil;
import lombok.Data;

import java.util.Map;

@Data
public class GuiceParserFactory {
    private Map<String, AbstractParser> classMap;

    @Inject
    public GuiceParserFactory(Map<String, AbstractParser> map) {
        classMap = map;
    }

    public AbstractParser getAbstractParser(String parserName) throws NullPointerException {
        AbstractParser parser = classMap.get(parserName);
        if (parser == null) {
            throw new NullPointerException();
        }
        Object obj = ReflectionUtil.makeObject(parser.getClass());
        if (obj != null) {
            return (AbstractParser) obj;
        }
        return null;
    }

}

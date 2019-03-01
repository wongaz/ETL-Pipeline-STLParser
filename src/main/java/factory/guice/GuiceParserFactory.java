package factory.guice;

import com.google.inject.Inject;
import extract.parser.AbstractParser;
import factory.ParserFactory;
import factory.ReflectionUtil;
import lombok.ToString;

import java.util.Map;

@ToString
public class GuiceParserFactory extends ParserFactory {
    private Map<String, AbstractParser> classMap;

    @Inject
    public GuiceParserFactory(Map<String, AbstractParser> map) {
        classMap = map;
    }

    @Override
    public AbstractParser getAbstractParser(String parserName) throws NullPointerException {
        AbstractParser parser = classMap.get(parserName);
        if (parser == null) {
            NullPointerException ex = new NullPointerException();
            ex.printStackTrace();
            throw ex;
        }
        Object obj = ReflectionUtil.makeObject(parser.getClass());
        if (obj != null) {
            return (AbstractParser) obj;
        }
        return null;
    }

}

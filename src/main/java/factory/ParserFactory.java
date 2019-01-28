package factory;

import extract.parser.AbstractParser;
import lombok.Data;

import java.util.Map;

@Data
public class ParserFactory {
    private Map<String, Class> classMap;


    public ParserFactory(Map<String, Class> map) {
        classMap = map;
    }

    public AbstractParser getAbstractParser(String parserName) {

        return null;
    }

}

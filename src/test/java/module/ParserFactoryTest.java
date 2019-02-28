package module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import extract.parser.AbstractParser;
import extract.parser.STLParser;
import factory.ParserFactory;
import factory.guice.GuiceParserFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ParserFactoryTest {
    private static GuiceParserFactory guiceParserFactory;
    private static ParserFactory parserFactory;


    @BeforeClass
    public static void setUp() {
        Injector injector = Guice.createInjector(new ParserModule());
        guiceParserFactory = injector.getInstance(GuiceParserFactory.class);

        Map<String, Class> parserMap = new HashMap<>();
        parserMap.put("stl", STLParser.class);
        parserFactory = new ParserFactory(parserMap);
    }

    @Test
    public void getNewSTLParserTest() {
        AbstractParser object = parserFactory.getAbstractParser("stl");
        AbstractParser object2 = parserFactory.getAbstractParser("stl");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
        Assert.assertTrue(object instanceof STLParser);
        Assert.assertTrue(object2 instanceof STLParser);
    }


    @Test
    public void getGuiceNewSTLParserTest() {
        AbstractParser object = guiceParserFactory.getAbstractParser("stl");
        AbstractParser object2 = guiceParserFactory.getAbstractParser("stl");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
        Assert.assertTrue(object instanceof STLParser);
        Assert.assertTrue(object2 instanceof STLParser);

    }
}

package module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.guice.GuiceParserFactory;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParserFactoryTest {
    static GuiceParserFactory parserFactory;

    @BeforeClass
    public static void setUp() {
        Injector injector = Guice.createInjector(new ParserModule());
        parserFactory = injector.getInstance(GuiceParserFactory.class);
    }

    @Test
    public void getNewSTLParserTest() {

    }
}

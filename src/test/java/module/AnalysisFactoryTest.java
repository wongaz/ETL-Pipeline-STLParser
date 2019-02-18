package module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.guice.GuiceAnalysisFactory;
import org.junit.BeforeClass;

public class AnalysisFactoryTest {
    static GuiceAnalysisFactory analysisFactory;

    @BeforeClass
    public static void setUp() {
        Injector injector = Guice.createInjector(new AnalysisModule());
        analysisFactory = injector.getInstance(GuiceAnalysisFactory.class);
    }


}

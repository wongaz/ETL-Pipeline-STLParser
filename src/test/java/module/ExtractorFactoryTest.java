package module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import extract.AbstractExtractor;
import extract.FileExtractor;
import factory.guice.GuiceExtractorFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExtractorFactoryTest {

    static GuiceExtractorFactory extractorFactory;

    @BeforeClass
    public static void setUp() {
        Injector injector = Guice.createInjector(new ExtractorModule());
        extractorFactory = injector.getInstance(GuiceExtractorFactory.class);
    }

    @Test
    public void getNewFileExtractor() {
        AbstractExtractor object = extractorFactory.getExtractor("file");
        AbstractExtractor object2 = extractorFactory.getExtractor("file");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
        Assert.assertTrue(object instanceof FileExtractor);
        Assert.assertTrue(object2 instanceof FileExtractor);
    }
}

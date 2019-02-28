package module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import extract.AbstractExtractor;
import extract.FileExtractor;
import factory.ExtractorFactory;
import factory.guice.GuiceExtractorFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ExtractorFactoryTest {

    static GuiceExtractorFactory GuiceExtractorFactory;
    static ExtractorFactory extractorFactory;

    @BeforeClass
    public static void setUp() {
        Injector injector = Guice.createInjector(new ExtractorModule());
        GuiceExtractorFactory = injector.getInstance(GuiceExtractorFactory.class);

        Map<String, Class> extractorMap = new HashMap<>();
        extractorMap.put("file", FileExtractor.class);
        extractorFactory = new ExtractorFactory(extractorMap);
    }

    @Test
    public void getGuiceNewFileExtractor() {
        AbstractExtractor object = GuiceExtractorFactory.getExtractor("file");
        AbstractExtractor object2 = GuiceExtractorFactory.getExtractor("file");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
        Assert.assertTrue(object instanceof FileExtractor);
        Assert.assertTrue(object2 instanceof FileExtractor);
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

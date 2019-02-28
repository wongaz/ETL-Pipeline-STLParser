package module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.AnalysisFactory;
import factory.guice.GuiceAnalysisFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import statistics.IAnalysis;
import statistics.area.SurfaceAreaAnalysis;
import statistics.box.BoxAnalysis;
import statistics.count.CountAnalysis;

import java.util.HashMap;
import java.util.Map;

public class AnalysisFactoryTest {
    static GuiceAnalysisFactory guiceAnalysisFactory;
    static AnalysisFactory analysisFactory;

    @SuppressWarnings("Duplicates")
    @BeforeClass
    public static void setUp() {
        Injector injector = Guice.createInjector(new AnalysisModule());
        guiceAnalysisFactory = injector.getInstance(GuiceAnalysisFactory.class);

        Map<String, Class> analysisMap = new HashMap<>();
        analysisMap.put("count", SurfaceAreaAnalysis.class);
        analysisMap.put("box", BoxAnalysis.class);
        analysisMap.put("area", CountAnalysis.class);
        analysisFactory = new AnalysisFactory(analysisMap);
    }

    @Test
    public void getGuiceNewBoxAnalysisTest() {
        IAnalysis object = guiceAnalysisFactory.getAnalysis("box");
        IAnalysis object2 = guiceAnalysisFactory.getAnalysis("box");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
        Assert.assertTrue(object instanceof BoxAnalysis);
        Assert.assertTrue(object2 instanceof BoxAnalysis);
    }

    @Test
    public void getNewBoxAnalysisTest() {
        IAnalysis object = analysisFactory.getAnalysis("box");
        IAnalysis object2 = analysisFactory.getAnalysis("box");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
        Assert.assertTrue(object instanceof BoxAnalysis);
        Assert.assertTrue(object2 instanceof BoxAnalysis);
    }

    @Test
    public void getGuiceNewAreaAnalysisTest() {
        IAnalysis object = guiceAnalysisFactory.getAnalysis("area");
        IAnalysis object2 = guiceAnalysisFactory.getAnalysis("area");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
        Assert.assertTrue(object instanceof SurfaceAreaAnalysis);
        Assert.assertTrue(object2 instanceof SurfaceAreaAnalysis);
    }

    @Test
    public void getNewAreaAnalysisTest() {
        IAnalysis object = analysisFactory.getAnalysis("area");
        IAnalysis object2 = analysisFactory.getAnalysis("area");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
    }

    @Test
    public void getGuiceNewCountAnalysisTest() {
        IAnalysis object = guiceAnalysisFactory.getAnalysis("count");
        IAnalysis object2 = guiceAnalysisFactory.getAnalysis("count");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
        Assert.assertTrue(object instanceof CountAnalysis);
        Assert.assertTrue(object2 instanceof CountAnalysis);
    }

    @Test
    public void getNewCountAnalysisTest() {
        IAnalysis object = analysisFactory.getAnalysis("count");
        IAnalysis object2 = analysisFactory.getAnalysis("count");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
    }

    @Test(expected = NullPointerException.class)
    public void getGuiceBadTest() {
        IAnalysis object = guiceAnalysisFactory.getAnalysis("dfaea");
    }
}

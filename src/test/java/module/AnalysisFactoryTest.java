package module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.guice.GuiceAnalysisFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import statistics.IAnalysis;
import statistics.area.SurfaceAreaAnalysis;
import statistics.box.BoxAnalysis;
import statistics.count.CountAnalysis;

public class AnalysisFactoryTest {
    static GuiceAnalysisFactory analysisFactory;

    @BeforeClass
    public static void setUp() {
        Injector injector = Guice.createInjector(new AnalysisModule());
        analysisFactory = injector.getInstance(GuiceAnalysisFactory.class);
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
    public void getNewAreaAnalysisTest() {
        IAnalysis object = analysisFactory.getAnalysis("area");
        IAnalysis object2 = analysisFactory.getAnalysis("area");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
        Assert.assertTrue(object instanceof SurfaceAreaAnalysis);
        Assert.assertTrue(object2 instanceof SurfaceAreaAnalysis);
    }

    @Test
    public void getNewCountAnalysisTest() {
        IAnalysis object = analysisFactory.getAnalysis("count");
        IAnalysis object2 = analysisFactory.getAnalysis("count");
        Assert.assertNotNull(object);
        Assert.assertNotNull(object2);
        Assert.assertFalse(object == object2);
        Assert.assertTrue(object instanceof CountAnalysis);
        Assert.assertTrue(object2 instanceof CountAnalysis);
    }

    @Test(expected = NullPointerException.class)
    public void getBadTest() {
        IAnalysis object = analysisFactory.getAnalysis("dfaea");
    }
}

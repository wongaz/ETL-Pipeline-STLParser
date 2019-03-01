package module;

import factory.singletonFactory.ComponentAreaFactory;
import factory.singletonFactory.DistanceMetricFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import statistics.area.componentArea.FacetArea;
import statistics.area.componentArea.IComponentArea;
import statistics.area.componentArea.distanceMetric.EuclideanDistance;
import statistics.area.componentArea.distanceMetric.IMetric;
import statistics.area.componentArea.distanceMetric.ManhattanDistance;
import statistics.area.componentArea.distanceMetric.SupremumDistance;

import java.util.HashMap;
import java.util.Map;

public class SingletonFactoryTest {

    @BeforeClass
    @SuppressWarnings("Duplicates")
    public static void setUp() {
        Map<String, IMetric> metricMap = new HashMap<>();
        metricMap.put("manhattan", new ManhattanDistance());
        metricMap.put("euclidean", new EuclideanDistance());
        metricMap.put("supremum", new SupremumDistance());
        DistanceMetricFactory.getInstance().setClassMap(metricMap);


        Map<String, IComponentArea> componentMap = new HashMap<>();
        componentMap.put("facet", new FacetArea());
        ComponentAreaFactory.getInstance().setClassMap(componentMap);
    }

    @Test
    public void getMetricFactoryTest() {
        DistanceMetricFactory distanceMetricFactory = DistanceMetricFactory.getInstance();
        Assert.assertEquals(3, distanceMetricFactory.getClassMapSize());
    }

    @Test
    public void getMetricFactoryFromMapTest() {
        DistanceMetricFactory distanceMetricFactory = DistanceMetricFactory.getInstance();
        Assert.assertEquals(3, distanceMetricFactory.getClassMapSize());

        Assert.assertTrue(distanceMetricFactory.getMetric("manhattan") instanceof ManhattanDistance);
        Assert.assertTrue(distanceMetricFactory.getMetric("euclidean") instanceof EuclideanDistance);
        Assert.assertTrue(distanceMetricFactory.getMetric("supremum") instanceof SupremumDistance);

    }


    @Test
    public void getComponentFactory() {
        ComponentAreaFactory componentAreaFactory = ComponentAreaFactory.getInstance();
        Assert.assertEquals(1, componentAreaFactory.getClassMapSize());

        Assert.assertTrue(componentAreaFactory.getComponentArea("facet") instanceof FacetArea);

    }


}

package statistics.area.componentArea;

import org.apache.commons.lang3.tuple.Triple;
import statistics.area.distanceMetric.EuclideanDistance;
import statistics.area.distanceMetric.IMetric;
import statistics.area.distanceMetric.ManhattanDistance;
import statistics.area.distanceMetric.SupremumDistance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacetArea implements IComponentArea {
    static Map<String, IMetric> metricMap = new HashMap<>();
    static {
        metricMap.put("manhattan", new ManhattanDistance());
        metricMap.put("euclidean", new EuclideanDistance());
        metricMap.put("supremum", new SupremumDistance());
    }
    @Override
    public double computeArea(String metric, List<Triple<Double, Double, Double>> verticesList) {
        return 0;
    }
}

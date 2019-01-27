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

    /**
     * Uses Herun's Formula for computing the triangle in 3 dimensional space.
     *
     * @param metric
     * @param verticesList
     * @return
     */
    @Override
    public double computeArea(String metric, List<Triple<Double, Double, Double>> verticesList) {
        IMetric selectedMetric = metricMap.get(metric);

        double a = selectedMetric.calculateDistance(verticesList.get(0), verticesList.get(1));
        double b = selectedMetric.calculateDistance(verticesList.get(0), verticesList.get(2));
        double c = selectedMetric.calculateDistance(verticesList.get(1), verticesList.get(2));

        double s = (a + b + c) / 3;

        return Math.sqrt(s * (s - a) * (s - b) *(s-c));
    }
}

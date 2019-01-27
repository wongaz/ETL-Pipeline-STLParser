package statistics.area.componentArea.distanceMetric;

import org.apache.commons.lang3.tuple.Triple;

public interface IMetric {
    double calculateDistance(Triple<Double, Double, Double> v1, Triple<Double, Double, Double> v2);
}

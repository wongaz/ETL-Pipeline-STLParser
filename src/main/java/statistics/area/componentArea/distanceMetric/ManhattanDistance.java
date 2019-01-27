package statistics.area.componentArea.distanceMetric;

import org.apache.commons.lang3.tuple.Triple;


public class ManhattanDistance implements IMetric {

    @Override
    public double calculateDistance(Triple<Double, Double, Double> v1, Triple<Double, Double, Double> v2) {
        double sum = Math.abs(v1.getLeft() - v2.getLeft())
                + Math.abs(v1.getMiddle() - v2.getMiddle())
                + Math.abs(v1.getRight() - v2.getRight());
        return Math.sqrt(sum);
    }
}

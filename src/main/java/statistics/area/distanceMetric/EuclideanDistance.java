package statistics.area.distanceMetric;

import org.apache.commons.lang3.tuple.Triple;

public class EuclideanDistance implements IMetric {

    private double square(double val) {
        return Math.pow(val, 2);
    }


    @Override
    public double calculateDistance(Triple<Double, Double, Double> v1, Triple<Double, Double, Double> v2) {
        double sum = square(v1.getLeft() - v2.getLeft())
                + square(v1.getMiddle() - v2.getMiddle())
                + square(v1.getRight() - v2.getRight());
        return Math.sqrt(sum);
    }
}

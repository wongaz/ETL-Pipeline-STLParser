package statistics.area.distanceMetric;


import org.apache.commons.lang3.tuple.Triple;

public class SupremumDistance implements IMetric {
    @Override
    public double calculateDistance(Triple<Double, Double, Double> v1, Triple<Double, Double, Double> v2) {
        double left = Math.abs(v1.getLeft() - v2.getLeft());
        double mid = Math.abs(v1.getMiddle() - v2.getMiddle());
        double right = Math.abs(v1.getRight() - v2.getRight());
        return Math.max(Math.max(left, mid), right);
    }
}

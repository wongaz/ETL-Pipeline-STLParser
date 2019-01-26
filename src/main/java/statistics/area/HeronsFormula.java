package statistics.area;

import model.Model;
import statistics.IAnalysis;
import statistics.area.distanceMetric.EuclideanDistance;
import statistics.area.distanceMetric.IMetric;
import statistics.area.distanceMetric.ManhattanDistance;
import statistics.area.distanceMetric.SupremumDistance;

import java.util.HashMap;
import java.util.Map;

public class HeronsFormula implements IAnalysis {

    static {
        Map<String, IMetric> metricMap = new HashMap<>();
        metricMap.put("manhattan", new ManhattanDistance());
        metricMap.put("euclidean", new EuclideanDistance());
        metricMap.put("supremum", new SupremumDistance());
    }

    @Override
    public void runAnalysis(Model model, Map<String, String> statisticsConf) {

    }
}

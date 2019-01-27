package statistics.count;

import model.Model;
import statistics.IAnalysis;

import java.util.Map;

public class CountAnalysis implements IAnalysis {

    @Override
    public void runAnalysis(Model model, Map<String, String> statisticsConf) {
        int num_triangle = model.getComponents().size();
        model.addAnalysis("Number of Triangles", num_triangle);
    }
}

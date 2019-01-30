package statistics.count;

import model.Model;
import org.apache.commons.lang3.tuple.Pair;
import statistics.IAnalysis;

import java.util.Map;

public class CountAnalysis implements IAnalysis {

    @Override
    public Pair<String, Object> runAnalysis(Model model, Map<String, String> statisticsConf) {
        int num_triangle = model.getComponents().size();
        return Pair.of("Number of Triangles", num_triangle);
    }
}

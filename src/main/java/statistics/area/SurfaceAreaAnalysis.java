package statistics.area;

import model.Model;
import statistics.IAnalysis;
import statistics.area.componentArea.FacetArea;
import statistics.area.componentArea.IComponentArea;

import java.util.HashMap;
import java.util.Map;

public class SurfaceAreaAnalysis implements IAnalysis {

    static Map<String, IComponentArea> componentMap = new HashMap<>();

    static {
        componentMap.put("facet", new FacetArea());
    }

    @Override
    public void runAnalysis(Model model, Map<String, String> statisticsConf) {

    }
}

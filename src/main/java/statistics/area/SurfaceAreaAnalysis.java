package statistics.area;

import model.Model;
import model.modelComponent.AbstractComponent;
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
        String metric = statisticsConf.get("metric");
        double surfaceArea = 0.0;
        for (AbstractComponent component : model.getComponents()) {
            IComponentArea componentArea = componentMap.get(component.getComponentName());
            surfaceArea = surfaceArea + componentArea.computeArea(metric, component.getVertices());
        }
        model.addAnalysis("Surface Area", surfaceArea);
    }

}

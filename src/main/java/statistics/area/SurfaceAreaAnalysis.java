package statistics.area;

import factory.singletonFactory.ComponentAreaFactory;
import model.Model;
import model.modelComponent.AbstractComponent;
import org.apache.commons.lang3.tuple.Pair;
import statistics.IAnalysis;
import statistics.area.componentArea.IComponentArea;

import java.util.Map;

public class SurfaceAreaAnalysis implements IAnalysis {


    @Override
    public Pair<String, Object> runAnalysis(Model model, Map<String, String> statisticsConf) {
        String metric = statisticsConf.get("metric");
        if (metric == null) {
            metric = "euclidean";
        }
        ComponentAreaFactory componentMap = ComponentAreaFactory.getInstance();
        double totalSurfaceArea = 0.0;
        for (AbstractComponent component : model.getComponents()) {
            IComponentArea componentArea = componentMap.getComponentArea(component.getComponentName());
            double surfaceArea = componentArea.computeArea(metric, component.getVertices());
            totalSurfaceArea = totalSurfaceArea + surfaceArea;
        }
        return Pair.of("Surface Area", totalSurfaceArea);
    }

}

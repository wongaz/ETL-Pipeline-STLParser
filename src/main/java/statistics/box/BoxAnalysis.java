package statistics.box;

import model.Model;
import model.modelComponent.AbstractComponent;
import statistics.IAnalysis;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoxAnalysis implements IAnalysis {

    @Override
    public void runAnalysis(Model model, Map<String, String> statisticsConf) {
        List<AbstractComponent> components = model.getComponents();
        List<?> vertices = components
                .stream()
                .map(abstractComponent -> abstractComponent.getVertices().stream().collect(Collectors.toList())).collect(Collectors.toList());

        vertices.forEach(x -> System.out.println(x));
    }
}

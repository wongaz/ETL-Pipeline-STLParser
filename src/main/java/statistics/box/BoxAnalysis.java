package statistics.box;

import model.Model;
import model.modelComponent.AbstractComponent;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import statistics.IAnalysis;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoxAnalysis implements IAnalysis {

    @SuppressWarnings("Duplicates")
    @Override
    public Pair<String, Object> runAnalysis(Model model, Map<String, String> statisticsConf) {
        List<AbstractComponent> components = model.getComponents();
        List<Triple<Double, Double, Double>> vertices = components
                .stream()
                .map(abstractComponent ->
                        abstractComponent.getVertices().stream().collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        DoubleSummaryStatistics xSummary = vertices.stream().mapToDouble(Triple::getLeft).summaryStatistics();
        DoubleSummaryStatistics ySummary = vertices.stream().mapToDouble(Triple::getMiddle).summaryStatistics();
        DoubleSummaryStatistics zSummary = vertices.stream().mapToDouble(Triple::getRight).summaryStatistics();

        double xMax = xSummary.getMax();
        double xMin = xSummary.getMin();

        double yMax = ySummary.getMax();
        double yMin = ySummary.getMin();

        double zMax = zSummary.getMax();
        double zMin = zSummary.getMin();

        List<Triple<Double, Double, Double>> box = new ArrayList<>();

        box.add(Triple.of(xMin, yMin, zMin));
        box.add(Triple.of(xMin, yMin, zMax));
        box.add(Triple.of(xMin, yMax, zMin));
        box.add(Triple.of(xMin, yMax, zMax));

        box.add(Triple.of(xMax, yMin, zMin));
        box.add(Triple.of(xMax, yMin, zMax));
        box.add(Triple.of(xMax, yMax, zMin));
        box.add(Triple.of(xMax, yMax, zMax));

        return Pair.of("Box", box);
    }
}

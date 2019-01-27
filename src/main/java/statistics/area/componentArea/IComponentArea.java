package statistics.area.componentArea;

import org.apache.commons.lang3.tuple.Triple;

import java.util.List;

public interface IComponentArea {
    double computeArea(String metric, List<Triple<Double, Double, Double>> verticesList);
}

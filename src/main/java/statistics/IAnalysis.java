package statistics;

import model.Model;

import java.io.Serializable;
import java.util.Map;


public interface IAnalysis extends Serializable {
    void runAnalysis(Model model, Map<String, String> statisticsConf);
}

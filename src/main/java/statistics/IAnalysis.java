package statistics;

import model.Model;

import java.util.Map;

public interface IAnalysis {
    void runAnalysis(Model model, Map<String, String> statisticsConf);
}

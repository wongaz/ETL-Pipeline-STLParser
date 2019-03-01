package factory;

import lombok.ToString;
import statistics.IAnalysis;

import java.util.Map;

@ToString
public class AnalysisFactory {
    private Map<String, Class> classMap;

    public AnalysisFactory() {
    }

    public AnalysisFactory(Map<String, Class> map) {
        classMap = map;
    }

    public IAnalysis getAnalysis(String analysisName) throws NullPointerException {
        Object obj = FactoryReflectionUtil.makeObject(analysisName, classMap);
        if (obj != null) {
            return (IAnalysis) obj;
        }
        return null;
    }
}

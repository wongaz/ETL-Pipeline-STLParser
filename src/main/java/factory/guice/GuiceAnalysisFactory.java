package factory.guice;

import factory.ReflectionUtil;
import lombok.ToString;
import statistics.IAnalysis;

import java.util.Map;

@ToString
public class GuiceAnalysisFactory {
    private Map<String, Class> classMap;

    public GuiceAnalysisFactory(Map<String, Class> map) {
        classMap = map;
    }

    public IAnalysis getAnalysis(String analysisName) {
        Object obj = ReflectionUtil.makeObject(analysisName, classMap);
        if (obj != null) {
            return (IAnalysis) obj;
        }
        return null;
    }
}

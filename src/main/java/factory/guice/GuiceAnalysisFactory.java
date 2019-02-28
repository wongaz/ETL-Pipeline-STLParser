package factory.guice;

import com.google.inject.Inject;
import factory.AnalysisFactory;
import factory.ReflectionUtil;
import lombok.ToString;
import statistics.IAnalysis;

import java.util.Map;

@ToString
public class GuiceAnalysisFactory extends AnalysisFactory {
    private Map<String, IAnalysis> classMap;


    @Inject
    public GuiceAnalysisFactory(Map<String, IAnalysis> map) {
        classMap = map;
    }

    @Override
    public IAnalysis getAnalysis(String analysisName) throws NullPointerException {
        IAnalysis analysis = classMap.get(analysisName);
        if (analysis == null) {
            throw new NullPointerException();
        }

        Object obj = ReflectionUtil.makeObject(analysis.getClass());
        if (obj != null) {
            return (IAnalysis) obj;
        }
        return null;
    }
}

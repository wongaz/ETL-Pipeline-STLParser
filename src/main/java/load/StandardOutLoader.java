package load;

import model.Model;

import java.util.Map;

public class StandardOutLoader implements ILoader {
    @Override
    public boolean load(Model model) {
        Map<String, ?> statsMap = model.getAnalysisMap();
        if (statsMap == null) {
            return false;
        }
        statsMap.forEach((x, y) -> System.out.println(x + ":" + y));
        return true;
    }

    @Override
    public void setOutConfigurations(Map<String, String> confMap) {

    }
}

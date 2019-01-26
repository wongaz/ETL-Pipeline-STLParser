package load;

import model.Model;

import java.util.Map;

public interface ILoader {
    boolean load(Model model);

    void setOutConfigurations(Map<String, String> confMap);
}

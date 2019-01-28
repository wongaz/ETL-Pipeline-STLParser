package load;

import model.Model;

import java.io.Serializable;
import java.util.Map;

public interface ILoader extends Serializable {
    boolean load(Model model);

    void setOutConfigurations(Map<String, String> confMap);
}

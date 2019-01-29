package model;

import lombok.Data;
import model.modelComponent.AbstractComponent;

import java.util.*;

@Data
public class Model {

    private List<AbstractComponent> components;
    private String modelName;
    private Map<String, Object> analysisMap;

    public Model() {
        this.components = new ArrayList<>();
        this.analysisMap = new LinkedHashMap<>();
    }

    public Model(String string) {
        this.modelName = string;
        this.components = new ArrayList<>();
        this.analysisMap = new HashMap<>();
    }

    public void addComponent(AbstractComponent abstractComponent) {
        components.add(abstractComponent);
    }

    public boolean addAnalysis(String name, Object data) {
        //Cannot request for the same analysis twice
        if (analysisMap.containsKey(name)) {
            return false;
        } else {
            analysisMap.put(name,data);
            return true;
        }
    }
}

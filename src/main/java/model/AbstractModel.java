package model;

import lombok.Data;
import model.modelComponent.AbstractComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public abstract class AbstractModel {

    private List<AbstractComponent> components;
    private String modelName;
    private Map<String, Object> analysisMap;

    public AbstractModel(){
        this.components = new ArrayList<>();
        this.analysisMap = new HashMap<>();
    }

    public boolean addAnalysis(String name, Object data) {
        if (analysisMap.containsKey(name)) {
            return false;
        } else {
            analysisMap.put(name,data);
            return true;
        }
    }
}

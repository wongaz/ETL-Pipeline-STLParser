package model.modelComponent;

import lombok.Data;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractComponent {
    private String componentName;
    private Triple<Double, Double, Double> basis;
    private List<Triple<Double, Double, Double>> vertices;
    private Type category;

    public AbstractComponent(String name, String category){
        this.componentName = name;
        this.category = Type.valueOf(category);
        this.vertices = new ArrayList<>();
    }

    public void addVertice(Triple<Double, Double, Double> vertex){
        this.vertices.add(vertex);

    }

    public void setBasis(double ni, double nj, double nk){
        this.basis = Triple.of(ni, nj, nk);
    }

    public void setCategory(String category){
        this.category = Type.valueOf(category);

    }

    public enum Type{
        NORMAL, ABNORMAL;
    }

}

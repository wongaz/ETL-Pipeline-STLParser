package extract.parser;

import lombok.Data;
import model.Model;
import model.modelComponent.AbstractComponent;

import java.io.Serializable;

@Data
public abstract class AbstractParser implements Serializable {

    protected AbstractComponent currentComponent;
    private Model model = new Model();

    public void setNewModel() {
        this.model = new Model();
    }

    public void setNewModel(String name) {
        this.model = new Model(name);
    }

    public void reset(){
        this.model = null;
    }

    public abstract void parse(String line);
}

package extract.parser;

import lombok.Data;
import model.Model;
import model.modelComponent.AbstractComponent;

@Data
public abstract class AbstractParser {

    protected AbstractComponent currentComponent;
    private Model model;

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

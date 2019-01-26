package extract.parser;

import lombok.Data;
import model.AbstractModel;

@Data
public abstract class AbstractParser {

    private AbstractModel model;


    public void reset(){
        this.model = null;
    }

    public abstract void parse(String line);
}

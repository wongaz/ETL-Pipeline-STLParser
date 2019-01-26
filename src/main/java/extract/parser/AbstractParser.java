package extract.parser;

import model.AbstractModel;

public abstract class AbstractParser {

    private AbstractModel model;


    public void reset(){
        this.model = null;
    }

    public abstract void parse(String line);
}

package extract;

import extract.parser.IParser;
import lombok.Data;
import model.AbstractModel;

@Data
public abstract class AbstractExtracter {

    private IParser parser;
    private AbstractModel abstractModel;


    public AbstractExtracter(){

    }

    public AbstractExtracter(IParser parser){
        this.parser = parser;
    }

    public abstract boolean read();
}

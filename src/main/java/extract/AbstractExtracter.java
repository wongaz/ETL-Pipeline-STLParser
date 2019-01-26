package extract;

import extract.parser.AbstractParser;
import lombok.Data;
import model.AbstractModel;

import java.util.Map;

@Data
public abstract class AbstractExtracter {

    private AbstractParser parser;


    public AbstractExtracter(){

    }

    public AbstractExtracter(AbstractParser parser){
        this.parser = parser;
    }

    public AbstractModel getModel(){
        return parser.getModel();
    }

    public abstract void setExtractionMap(Map<String, String> extractionMap);
    public abstract boolean read();
}

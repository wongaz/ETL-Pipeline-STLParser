package extract;

import extract.parser.AbstractParser;
import lombok.Data;
import model.Model;

import java.io.Serializable;
import java.util.Map;

@Data
public abstract class AbstractExtractor implements Serializable {

    private AbstractParser parser;


    public AbstractExtractor() {

    }

    public AbstractExtractor(AbstractParser parser) {
        this.parser = parser;
    }

    public Model getModel() {
        return parser.getModel();
    }

    public void setNewModel(String name) {
        parser.setNewModel(name);
    }

    public abstract void setExtractionMap(String string, Map<String, String> extractionMap);
    public abstract boolean read();
}

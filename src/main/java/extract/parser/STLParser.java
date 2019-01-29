package extract.parser;


import model.modelComponent.AbstractComponent;
import model.modelComponent.FacetComponent;
import org.apache.commons.lang3.tuple.Triple;

public class STLParser extends AbstractParser {

    @Override
    public void parse(String line) {
        line = line.trim();
        String[] word = line.split(" ");
        switch (word[0]) {
            case "solid":
                //System.out.println(word[1]);
                break;
            case "facet":
                String type = word[1].toUpperCase();
                double ni = Double.parseDouble(word[2]);
                double nj = Double.parseDouble(word[3]);
                double nk = Double.parseDouble(word[4]);
                AbstractComponent component = new FacetComponent("facet", type);
                component.setBasis(ni, nj, nk);
                super.currentComponent = component;
                break;
            case "outer":
                break;
            case "vertex":
                double x = Double.parseDouble(word[1]);
                double y = Double.parseDouble(word[2]);
                double z = Double.parseDouble(word[3]);
                super.currentComponent.addVertice(Triple.of(x, y, z));
                break;
            case "endloop":
                break;
            case "endfacet":
                super.getModel().addComponent(super.currentComponent);
                break;

        }
    }
}

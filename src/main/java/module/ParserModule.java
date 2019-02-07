package module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import extract.AbstractExtractor;
import extract.parser.AbstractParser;
import extract.parser.STLParser;

public class ParserModule extends AbstractModule {
    @Override
    protected void configure() {
        MapBinder<String, AbstractParser> mapBinder = MapBinder.newMapBinder(binder(), String.class, AbstractParser.class);

        mapBinder.addBinding("stl").to(STLParser.class);

    }
}

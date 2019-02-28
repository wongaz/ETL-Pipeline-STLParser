package module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import extract.AbstractExtractor;
import extract.FileExtractor;


public class ExtractorModule extends AbstractModule {
    @Override
    protected void configure() {
        MapBinder<String, AbstractExtractor> mapBinder = MapBinder.newMapBinder(binder(), String.class, AbstractExtractor.class);
        mapBinder.addBinding("file").to(FileExtractor.class);
    }
}

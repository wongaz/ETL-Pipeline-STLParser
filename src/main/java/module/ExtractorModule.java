package module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import extract.AbstractExtractor;
import extract.FileExtractor;
import load.FileOutLoader;
import load.ILoader;
import load.StandardOutLoader;

public class ExtractorModule extends AbstractModule {
    @Override
    protected void configure() {
        MapBinder<String, AbstractExtractor> mapBinder = MapBinder.newMapBinder(binder(), String.class, AbstractExtractor.class);
        mapBinder.addBinding("file").to(FileExtractor.class);
    }
}

package module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import load.FileOutLoader;
import load.ILoader;
import load.StandardOutLoader;

public class LoaderModule extends AbstractModule {
    @Override
    protected void configure() {
        MapBinder<String, ILoader> mapBinder = MapBinder.newMapBinder(binder(), String.class, ILoader.class);
        mapBinder.addBinding("something").to(FileOutLoader.class);
        mapBinder.addBinding("keyB").to(StandardOutLoader.class);
    }
}

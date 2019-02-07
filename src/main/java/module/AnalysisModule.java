package module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import load.FileOutLoader;
import load.ILoader;
import load.StandardOutLoader;
import statistics.IAnalysis;
import statistics.area.SurfaceAreaAnalysis;
import statistics.box.BoxAnalysis;
import statistics.count.CountAnalysis;

public class AnalysisModule extends AbstractModule {
    @Override
    protected void configure() {
        MapBinder<String, IAnalysis> mapBinder = MapBinder.newMapBinder(binder(), String.class, IAnalysis.class);
        mapBinder.addBinding("count").to(CountAnalysis.class);
        mapBinder.addBinding("area").to(SurfaceAreaAnalysis.class);
        mapBinder.addBinding("box").to(BoxAnalysis.class);
    }
}

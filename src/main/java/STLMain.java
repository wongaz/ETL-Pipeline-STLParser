import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppConfig;
import config.AppLoader;
import config.PipelineConfig;
import factory.AnalysisFactory;
import factory.ExtractorFactory;
import factory.LoaderFactory;
import factory.ParserFactory;
import factory.guice.GuiceAnalysisFactory;
import factory.guice.GuiceExtractorFactory;
import factory.guice.GuiceLoaderFactory;
import factory.guice.GuiceParserFactory;
import factory.singletonFactory.ComponentAreaFactory;
import factory.singletonFactory.DistanceMetricFactory;
import module.AnalysisModule;
import module.ExtractorModule;
import module.LoaderModule;
import module.ParserModule;
import pipeline.Pipeline;
import pipeline.PipelineBuilder;
import statistics.area.componentArea.FacetArea;
import statistics.area.componentArea.IComponentArea;
import statistics.area.componentArea.distanceMetric.EuclideanDistance;
import statistics.area.componentArea.distanceMetric.IMetric;
import statistics.area.componentArea.distanceMetric.ManhattanDistance;
import statistics.area.componentArea.distanceMetric.SupremumDistance;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class STLMain {

    public static void disableAccessWarnings() {
        try {
            Class unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Object unsafe = field.get(null);

            Method putObjectVolatile = unsafeClass.getDeclaredMethod("putObjectVolatile", Object.class, long.class, Object.class);
            Method staticFieldOffset = unsafeClass.getDeclaredMethod("staticFieldOffset", Field.class);

            Class loggerClass = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field loggerField = loggerClass.getDeclaredField("logger");
            Long offset = (Long) staticFieldOffset.invoke(unsafe, loggerField);
            putObjectVolatile.invoke(unsafe, loggerClass, offset, null);
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings("Duplicates")
    public static void main(String... args){

        disableAccessWarnings();

        Map<String, IMetric> metricMap = new HashMap<>();
        metricMap.put("manhattan", new ManhattanDistance());
        metricMap.put("euclidean", new EuclideanDistance());
        metricMap.put("supremum", new SupremumDistance());
        DistanceMetricFactory.getInstance().setClassMap(metricMap);


        Map<String, IComponentArea> componentMap = new HashMap<>();
        componentMap.put("facet", new FacetArea());
        ComponentAreaFactory.getInstance().setClassMap(componentMap);


        Injector analysisInjector = Guice.createInjector(new AnalysisModule());
        AnalysisFactory analysisFactory = analysisInjector.getInstance(GuiceAnalysisFactory.class);

        Injector extractorInjector = Guice.createInjector(new ExtractorModule());
        ExtractorFactory extractorFactory = extractorInjector.getInstance(GuiceExtractorFactory.class);

        Injector loaderInjector = Guice.createInjector(new LoaderModule());
        LoaderFactory loaderFactory = loaderInjector.getInstance(GuiceLoaderFactory.class);

        Injector parserInjector = Guice.createInjector(new ParserModule());
        ParserFactory parserFactory = parserInjector.getInstance(GuiceParserFactory.class);

        PipelineBuilder pipelineBuilder =
                new PipelineBuilder(analysisFactory, extractorFactory, loaderFactory, parserFactory);

        //String conf = "configFiles/moon.yaml";
        if (args.length == 0) {
            System.out.println("Please Supply at least 1 Config YAML to run pipeline");
            System.exit(1);
        }
        String conf = args[0];

        AppConfig appConfig = AppLoader.loadConfiguration(conf);
        //System.out.println(appConfig);

        List<Pipeline> pipelines = new ArrayList<>();
        for (PipelineConfig pipelineConfig : appConfig.getPipelineConfigs()) {
            pipelines.add(pipelineBuilder.build(pipelineConfig));
        }

        pipelines.forEach(Pipeline::runPipeline);
    }
}

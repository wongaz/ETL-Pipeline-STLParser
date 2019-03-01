import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppConfig;
import config.AppLoader;
import config.PipelineConfig;
import config.pipeline.Pipeline;
import factory.guice.GuiceLoaderFactory;
import factory.singletonFactory.ComponentAreaFactory;
import factory.singletonFactory.DistanceMetricFactory;
import module.LoaderModule;
import statistics.area.componentArea.FacetArea;
import statistics.area.componentArea.IComponentArea;
import statistics.area.componentArea.distanceMetric.EuclideanDistance;
import statistics.area.componentArea.distanceMetric.IMetric;
import statistics.area.componentArea.distanceMetric.ManhattanDistance;
import statistics.area.componentArea.distanceMetric.SupremumDistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class STLMain {

    @SuppressWarnings("Duplicates")
    public static void main(String... args){

//        Map<String, Class> extractorMap = new HashMap<>();
//        extractorMap.put("file", FileExtractor.class);
//        ExtractorFactory extractorFactory = new ExtractorFactory(extractorMap);
//
//        Map<String, Class> parserMap = new HashMap<>();
//        parserMap.put("STL", STLParser.class);
//        ParserFactory parserFactory = new ParserFactory(parserMap);
//
//        Map<String, Class> analysisMap = new HashMap<>();
//        analysisMap.put("count", SurfaceAreaAnalysis.class);
//        analysisMap.put("box", BoxAnalysis.class);
//        analysisMap.put("area", CountAnalysis.class);
//        AnalysisFactory analysisFactory = new AnalysisFactory(analysisMap);
//
//        Map<String, Class> loaderMap = new HashMap<>();
//        loaderMap.put("stdout", StandardOutLoader.class);
//        loaderMap.put("file", FileOutLoader.class);
//        LoaderFactory loaderFactory = new LoaderFactory(loaderMap);

        Map<String, IMetric> metricMap = new HashMap<>();
        metricMap.put("manhattan", new ManhattanDistance());
        metricMap.put("euclidean", new EuclideanDistance());
        metricMap.put("supremum", new SupremumDistance());
        DistanceMetricFactory.getInstance().setClassMap(metricMap);


        Map<String, IComponentArea> componentMap = new HashMap<>();
        componentMap.put("facet", new FacetArea());
        ComponentAreaFactory.getInstance().setClassMap(componentMap);

        Injector injector = Guice.createInjector(new LoaderModule());
        GuiceLoaderFactory guiceLoaderFactory = injector.getInstance(GuiceLoaderFactory.class);

        String conf = "configFiles/moon.yaml";

        AppConfig appConfig = AppLoader.loadConfiguration(conf);
        //System.out.println(appConfig);

        List<Pipeline> pipelines = new ArrayList<>();
        for (PipelineConfig pipelineConfig : appConfig.getPipelineConfigs()) {
//            config.pipeline.Pipeline config.pipeline = new config.pipeline.Pipeline();
//            config.pipeline.setName(pipelineConfig.getPipelineName());
//
//            //Deep Copies
//            String extract = pipelineConfig.getExtractType();
//            config.pipeline.setExtractor(extractorFactory.getExtractor(extract));
//            config.pipeline.setExtractorConf(pipelineConfig.getExtractConfiguration());
//
//            String parserName = pipelineConfig.getParseFormat();
//            config.pipeline.setParser(parserFactory.getAbstractParser(parserName));
//
//            List<IAnalysis> analyses = new ArrayList<>();
//            for (String statistic : pipelineConfig.getStatistics()) {
//                analyses.add(analysisFactory.getAnalysis(statistic));
//            }
//            config.pipeline.setStatistics(analyses);
//
//            config.pipeline.setStatsConf(pipelineConfig.getStatisticsConf());
//
//            Map<String, ILoader> loaders = new HashMap<>();
//            for (String loaderName : pipelineConfig.getLoadType()) {
//                loaders.put(loaderName, loaderFactory.getLoader(loaderName));
//            }
//            config.pipeline.setLoaders(loaders);
//
//            config.pipeline.setLoadConfiguration(pipelineConfig.getLoadConf());
//            pipelines.add(config.pipeline);
        }

        //pipelines.forEach(x -> System.out.println(x.toString()));
        pipelines.forEach(Pipeline::runPipeline);
    }
}

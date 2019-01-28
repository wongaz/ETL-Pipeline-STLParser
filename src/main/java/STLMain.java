import config.AppConfig;
import config.AppLoader;
import config.PipelineConfig;
import extract.FileExtractor;
import extract.parser.STLParser;
import factory.AnalysisFactory;
import factory.ExtractorFactory;
import factory.LoaderFactory;
import factory.ParserFactory;
import load.FileOutLoader;
import load.ILoader;
import load.StandardOutLoader;
import statistics.IAnalysis;
import statistics.area.SurfaceAreaAnalysis;
import statistics.box.BoxAnalysis;
import statistics.count.CountAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class STLMain {

    public static void main(String... args){
        Map<String, Class> extractorMap = new HashMap<>();
        extractorMap.put("file", FileExtractor.class);
        ExtractorFactory extractorFactory = new ExtractorFactory(extractorMap);

        Map<String, Class> parserMap = new HashMap<>();
        parserMap.put("STL", STLParser.class);
        ParserFactory parserFactory = new ParserFactory(parserMap);

        Map<String, Class> analysisMap = new HashMap<>();
        analysisMap.put("count", SurfaceAreaAnalysis.class);
        analysisMap.put("box", BoxAnalysis.class);
        analysisMap.put("area", CountAnalysis.class);
        AnalysisFactory analysisFactory = new AnalysisFactory(analysisMap);

        Map<String, Class> loaderMap = new HashMap<>();
        loaderMap.put("stdout", StandardOutLoader.class);
        loaderMap.put("file", FileOutLoader.class);
        LoaderFactory loaderFactory = new LoaderFactory(loaderMap);


        String conf = "configFiles/moon.yaml";

        AppConfig appConfig = AppLoader.loadConfiguration(conf);
        System.out.println(appConfig);

        List<Pipeline> pipelines = new ArrayList<>();
        for (PipelineConfig pipelineConfig : appConfig.getPipelineConfigs()) {
            Pipeline pipeline = new Pipeline();
            pipeline.setName(pipelineConfig.getPipelineName());

            //Deep Copies
            String extract = pipelineConfig.getExtractType();
            pipeline.setExtractor(extractorFactory.getExtractor(extract));
            pipeline.setExtractorConf(pipelineConfig.getExtractConfiguration());

            String parserName = pipelineConfig.getParseFormat();
            pipeline.setParser(parserFactory.getAbstractParser(parserName));

            List<IAnalysis> analyses = new ArrayList<>();
            for (String statistic : pipelineConfig.getStatistics()) {
                analyses.add(analysisFactory.getAnalysis(statistic));
            }
            pipeline.setStatistics(analyses);

            pipeline.setStatsConf(pipelineConfig.getStatisticsConf());

            List<ILoader> loader = new ArrayList<>();
            for (String loaderName : pipelineConfig.getLoadType()) {
                loader.add(loaderFactory.getLoader(loaderName));
            }
            pipeline.setLoaders(loader);

            pipeline.setLoadConfiguration(pipelineConfig.getLoadConfiguration());
            pipelines.add(pipeline);

        }

        pipelines.forEach(x -> System.out.println(x.toString()));
        pipelines.forEach(Pipeline::runPipeline);
    }
}

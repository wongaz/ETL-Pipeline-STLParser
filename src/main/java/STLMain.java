import config.AppConfig;
import config.AppLoader;
import config.PipelineConfig;
import extract.AbstractExtractor;
import extract.FileExtractor;
import extract.parser.AbstractParser;
import extract.parser.STLParser;
import load.FileOutLoader;
import load.ILoader;
import load.StandardOutLoader;
import org.apache.commons.lang.SerializationUtils;
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
        Map<String, AbstractParser> parserMap = new HashMap<>();
        parserMap.put("STL", new STLParser());

        Map<String, AbstractExtractor> extractorMap = new HashMap<>();
        extractorMap.put("file", new FileExtractor());

        Map<String, IAnalysis> analysisMap = new HashMap<>();
        analysisMap.put("count", new CountAnalysis());
        analysisMap.put("box", new BoxAnalysis());
        analysisMap.put("area", new SurfaceAreaAnalysis());

        Map<String, ILoader> loaderMap = new HashMap<>();
        loaderMap.put("stdout", new StandardOutLoader());
        loaderMap.put("file", new FileOutLoader());


        String conf = "configFiles/moon.yaml";

        AppConfig appConfig = AppLoader.loadConfiguration(conf);
        System.out.println(appConfig);

        List<Pipeline> pipelines = new ArrayList<>();
        for (PipelineConfig pipelineConfig : appConfig.getPipelineConfigs()) {
            Pipeline pipeline = new Pipeline();
            pipeline.setName(pipelineConfig.getPipelineName());

            //Deep Copies
            String extract = pipelineConfig.getExtractType();
            pipeline.setExtractor((AbstractExtractor)
                    SerializationUtils.clone(extractorMap.get(extract)));

            pipeline.setExtractorConf(pipelineConfig.getExtractConfiguration());

            String parserName = pipelineConfig.getParseFormat();
            pipeline.setParser((AbstractParser)
                    SerializationUtils.clone(parserMap.get(parserName)));

            List<IAnalysis> analyses = new ArrayList<>();
            for (String statistic : pipelineConfig.getStatistics()) {
                analyses.add((IAnalysis) SerializationUtils.clone(analysisMap.get(statistic)));
            }
            pipeline.setStatistics(analyses);

            pipeline.setStatsConf(pipelineConfig.getStatisticsConf());

            List<ILoader> loader = new ArrayList<>();
            for (String loaderName : pipelineConfig.getLoadType()) {
                loader.add((ILoader) SerializationUtils.clone(loaderMap.get(loaderName)));
            }
            pipeline.setLoaders(loader);
            pipeline.setLoadConfiguration(pipelineConfig.getLoadConfiguration());

            pipelines.add(pipeline);

        }
        pipelines.forEach(x -> System.out.println(x.toString()));
        pipelines.forEach(Pipeline::runPipeline);

//        AbstractExtractor selectedExtractor = extractorMap.get(appConfig.getExtractType());
//        AbstractParser selectedParseStyle = parserMap.get(appConfig.getParseFormat());
//
//        selectedExtractor.setParser(selectedParseStyle);
//        Map<String, Map<String,String>> extractConf =  appConfig.getExtractConfiguration();
//        Map<String, Model> modelMap = new HashMap<>();
//
//        for (Map.Entry<String, Map<String, String>> entry : extractConf.entrySet()) {
//            selectedExtractor.setExtractionMap(entry.getKey(), entry.getValue());
//            selectedExtractor.read();
//            modelMap.put(entry.getKey(), selectedExtractor.getModel());
//        }
//
//        for (String statistic : appConfig.getStatistics()) {
//            for (Map.Entry<String, Model> modelEntry : modelMap.entrySet()) {
//                IAnalysis analysis = analysisMap.get(statistic);
//                Map<String, String> statisticConf = appConfig.getStatisticsConf();
//                analysis.runAnalysis(modelEntry.getValue(), statisticConf);
//            }
//        }
//
//        modelMap.values().forEach(x -> System.out.println(x.getAnalysisMap()));
//
//        String[] stringArray = appConfig.getLoadType();
//        for (String s : stringArray) {
//            ILoader loader = loaderMap.get(s);
//            loader.setOutConfigurations(appConfig.getLoadConfiguration().get(s));
//            loader.load(modelMap.get("Moon"));
//        }

    }
}

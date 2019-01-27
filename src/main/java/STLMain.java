import config.AppConfig;
import config.AppLoader;
import extract.AbstractExtractor;
import extract.FileExtractor;
import extract.parser.AbstractParser;
import extract.parser.STLParser;
import load.FileOutLoader;
import load.ILoader;
import load.StandardOutLoader;
import model.Model;
import statistics.IAnalysis;
import statistics.area.SurfaceAreaAnalysis;
import statistics.box.BoxAnalysis;
import statistics.count.CountAnalysis;

import java.util.HashMap;
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

        AbstractExtractor selectedExtractor = extractorMap.get(appConfig.getExtractType());
        AbstractParser selectedParseStyle = parserMap.get(appConfig.getParseFormat());

        selectedExtractor.setParser(selectedParseStyle);
        Map<String, Map<String,String>> extractConf =  appConfig.getExtractConfiguration();
        Map<String, Model> modelMap = new HashMap<>();

        for (Map.Entry<String, Map<String, String>> entry : extractConf.entrySet()) {
            selectedExtractor.setExtractionMap(entry.getKey(), entry.getValue());
            selectedExtractor.read();
            modelMap.put(entry.getKey(), selectedExtractor.getModel());
        }

        for (String statistic : appConfig.getStatistics()) {
            for (Map.Entry<String, Model> modelEntry : modelMap.entrySet()) {
                IAnalysis analysis = analysisMap.get(statistic);
                Map<String, String> statisticConf = appConfig.getStatisticsConf();
                analysis.runAnalysis(modelEntry.getValue(), statisticConf);
            }
        }

        modelMap.values().forEach(x -> System.out.println(x.getAnalysisMap()));


    }
}

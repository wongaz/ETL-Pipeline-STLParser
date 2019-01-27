import config.AppConfig;
import config.AppLoader;
import extract.AbstractExtractor;
import extract.FileExtractor;
import extract.parser.AbstractParser;
import extract.parser.STLParser;
import model.Model;

import java.util.HashMap;
import java.util.Map;

public class STLMain {


    public static void main(String... args){
        Map<String, AbstractParser> parserMap = new HashMap<>();
        parserMap.put("STL", new STLParser());

        Map<String, AbstractExtractor> extractorMap = new HashMap<>();
        extractorMap.put("file", new FileExtractor());

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

        modelMap.values().forEach(x -> System.out.println(x.toString()));


    }
}

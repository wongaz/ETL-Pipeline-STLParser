import config.AppConfig;
import config.AppLoader;
import extract.AbstractExtracter;
import extract.FileExtracter;
import extract.parser.AbstractParser;
import extract.parser.STLParser;
import model.AbstractModel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class STLMain {

    private static void run(){

    }

    public static void main(String... args){
        Map<String, AbstractParser> parserMap = new HashMap<>();
        parserMap.put("STL", new STLParser());

        Map<String, AbstractExtracter> extracterMap = new HashMap<>();
        extracterMap.put("file", new FileExtracter());

        String conf = "configFiles/moon.yaml";

        AppConfig appConfig = AppLoader.loadConfiguration(conf);
        System.out.println(appConfig.toString());

        AbstractExtracter selectedExtracter = extracterMap.get(appConfig.getExtractType());
        AbstractParser selectedParseStyle = parserMap.get(appConfig.getParseFormat());

        selectedExtracter.setParser(selectedParseStyle);
        Map<String, Map<String,String>> extractConf =  appConfig.getExtractConfiguration();
        Map<String, AbstractModel> modelMap = new HashMap<>();

        for (Map.Entry<String, Map<String, String>> entry : extractConf.entrySet()) {
            System.out.println(entry.getKey());
            selectedExtracter.setExtractionMap(entry.getValue());
            selectedExtracter.read();
            modelMap.put(entry.getKey(),selectedExtracter.getModel());
        }


    }
}

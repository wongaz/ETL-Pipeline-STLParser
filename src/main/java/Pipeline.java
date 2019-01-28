import extract.AbstractExtractor;
import extract.parser.AbstractParser;
import load.ILoader;
import lombok.Data;
import model.Model;
import statistics.IAnalysis;

import java.util.List;
import java.util.Map;

@Data
public class Pipeline {
    private String name;
    private AbstractExtractor extractor;
    private Map<String, String> extractorConf;
    private AbstractParser parser;
    private List<IAnalysis> statistics;
    private Map<String, String> statsConf;
    private List<ILoader> loaders;
    private Map<String, Map<String, String>> loadConfiguration;
    private Model model;


    private void extract() {

    }

    private void transform() {

    }

    private void load() {

    }

    public void runPipeline() {

    }

}

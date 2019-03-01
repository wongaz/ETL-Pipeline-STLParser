package pipeline;

import extract.AbstractExtractor;
import extract.parser.AbstractParser;
import load.ILoader;
import lombok.Data;
import model.Model;
import org.apache.commons.lang3.tuple.Pair;
import statistics.IAnalysis;

import java.util.List;
import java.util.Map;

@Data
public class Pipeline {
    private static final String EXTRACT_MESSAGE = "STARTING EXTRACTION PHASE: ";
    private static final String TRANSFORM_MESSAGE = "STARTING TRANSFORMATION PHASE: ";
    private static final String LOAD_MESSAGE = "STARTING LOAD PHASE: ";

    private int phase = 0;
    private String name;
    private AbstractExtractor extractor;
    private Map<String, String> extractorConf;
    private AbstractParser parser;
    private List<IAnalysis> statistics;
    private Map<String, String> statsConf;
    private Map<String, ILoader> loaders;
    private Map<String, Map<String, String>> loadConfiguration;
    private Model model;


    public void extract() {
        if (phase == 0) {
            System.out.println(EXTRACT_MESSAGE + this.name);
            this.extractor.setExtractionMap(this.extractorConf);
            this.extractor.setParser(parser);
            this.extractor.read();
            this.model = this.extractor.getModel();
            phase++;
        }
    }

    public void transform() {
        if (phase == 1) {
            System.out.println(TRANSFORM_MESSAGE + this.name);
            statistics.forEach(x -> {
                Pair<String, ?> tuple = x.runAnalysis(this.model, this.statsConf);
                this.model.addAnalysis(tuple.getLeft(), tuple.getRight());
            });
            phase++;
        }
    }

    public void load() {
        if (phase == 2) {
            System.out.println(LOAD_MESSAGE + this.name);
            this.loaders.entrySet().forEach(x -> {
                Map<String, String> loadConf = this.loadConfiguration.get(x.getKey());
                ILoader currentLoader = x.getValue();
                currentLoader.setOutConfigurations(loadConf);
                currentLoader.load(this.model);
            });
            phase++;
        }

    }

    public void runPipeline() {
        extract();
        transform();
        load();

    }

}

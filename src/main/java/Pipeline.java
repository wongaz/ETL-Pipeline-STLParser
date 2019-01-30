import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private String name;
    private AbstractExtractor extractor;
    private Map<String, String> extractorConf;
    private AbstractParser parser;
    private List<IAnalysis> statistics;
    private Map<String, String> statsConf;
    private Map<String, ILoader> loaders;
    private Map<String, Map<String, String>> loadConfiguration;
    private Model model;


    private void extract() {
        this.extractor.setExtractionMap(this.extractorConf);
        this.extractor.setParser(parser);
        this.extractor.read();
        this.model = this.extractor.getModel();
    }

    private void toGson() {
        //Gson gson = new Gson();
        //System.out.println(gson.toJson(this.model));
        ObjectMapper objectMapper = new ObjectMapper();
        String serialized = null;
        try {
            serialized = objectMapper.writeValueAsString(this.model);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(serialized);
        System.out.println(this.model);

    }

    private void transform() {
        statistics.forEach(x -> {
            Pair<String, ?> tuple = x.runAnalysis(this.model, this.statsConf);
            this.model.addAnalysis(tuple.getLeft(), tuple.getRight());
        });
    }

    private void load() {
        this.loaders.entrySet().forEach(x -> {
            Map<String, String> loadConf = this.loadConfiguration.get(x.getKey());
            ILoader currentLoader = x.getValue();
            currentLoader.setOutConfigurations(loadConf);
            currentLoader.load(this.model);
        });

    }

    public void runPipeline() {
        extract();
        toGson();
        transform();
        load();

    }

}

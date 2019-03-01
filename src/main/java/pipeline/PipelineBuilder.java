package pipeline;

import config.PipelineConfig;
import factory.AnalysisFactory;
import factory.ExtractorFactory;
import factory.LoaderFactory;
import factory.ParserFactory;
import load.ILoader;
import statistics.IAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PipelineBuilder {
    private AnalysisFactory analysisFactory;
    private ExtractorFactory extractorFactory;
    private LoaderFactory loaderFactory;
    private ParserFactory parserFactory;

    public PipelineBuilder(AnalysisFactory analysisFactory, ExtractorFactory extractorFactory, LoaderFactory loaderFactory,
                           ParserFactory parserFactory) {
        this.analysisFactory = analysisFactory;
        this.extractorFactory = extractorFactory;
        this.loaderFactory = loaderFactory;
        this.parserFactory = parserFactory;
    }

    public Pipeline build(PipelineConfig config) {
        Pipeline pipeline = new Pipeline();
        pipeline.setName(config.getPipelineName());

        //Deep Copies
        String extract = config.getExtractType();
        pipeline.setExtractor(extractorFactory.getExtractor(extract));
        pipeline.setExtractorConf(config.getExtractConfiguration());

        String parserName = config.getParseFormat();
        pipeline.setParser(parserFactory.getAbstractParser(parserName));

        List<IAnalysis> analyses = new ArrayList<>();
        for (String statistic : config.getStatistics()) {
            analyses.add(analysisFactory.getAnalysis(statistic));
        }
        pipeline.setStatistics(analyses);

        pipeline.setStatsConf(config.getStatisticsConf());

        Map<String, ILoader> loaders = new HashMap<>();
        for (String loaderName : config.getLoadType()) {
            loaders.put(loaderName, loaderFactory.getLoader(loaderName));
        }
        pipeline.setLoaders(loaders);
        if (config.getLoadConf() != null) {
            pipeline.setLoadConfiguration(config.getLoadConf());
        } else {
            pipeline.setLoadConfiguration(new HashMap<String, Map<String, String>>());
        }
        return pipeline;

    }

}

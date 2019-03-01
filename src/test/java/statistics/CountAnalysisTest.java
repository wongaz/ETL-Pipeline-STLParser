package statistics;


import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppConfig;
import config.AppLoader;
import config.PipelineConfig;
import config.pipeline.Pipeline;
import config.pipeline.PipelineBuilder;
import factory.AnalysisFactory;
import factory.ExtractorFactory;
import factory.LoaderFactory;
import factory.ParserFactory;
import factory.guice.GuiceAnalysisFactory;
import factory.guice.GuiceExtractorFactory;
import factory.guice.GuiceLoaderFactory;
import factory.guice.GuiceParserFactory;
import module.AnalysisModule;
import module.ExtractorModule;
import module.LoaderModule;
import module.ParserModule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountAnalysisTest {

    private static AppConfig sampleConfig;
    private static AnalysisFactory analysisFactory;
    private static ExtractorFactory extractorFactory;
    private static LoaderFactory loaderFactory;
    private static ParserFactory parserFactory;
    private static PipelineBuilder pipelineBuilder;

    @BeforeClass
    public static void setup() {
        InputStream stream1 = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("configFiles/sample_count.yaml");
        sampleConfig = AppLoader.loadConfiguration(stream1);

        Injector analysisInjector = Guice.createInjector(new AnalysisModule());
        analysisFactory = analysisInjector.getInstance(GuiceAnalysisFactory.class);

        Injector extractorInjector = Guice.createInjector(new ExtractorModule());
        extractorFactory = extractorInjector.getInstance(GuiceExtractorFactory.class);

        Injector loaderInjector = Guice.createInjector(new LoaderModule());
        loaderFactory = loaderInjector.getInstance(GuiceLoaderFactory.class);

        Injector parserInjector = Guice.createInjector(new ParserModule());
        parserFactory = parserInjector.getInstance(GuiceParserFactory.class);

        pipelineBuilder = new PipelineBuilder(analysisFactory, extractorFactory, loaderFactory, parserFactory);
    }

    @Test
    public void testCountAnalysisSimple() {
        List<Pipeline> pipelines = new ArrayList<>();
        for (PipelineConfig pipelineConfig : sampleConfig.getPipelineConfigs()) {
            pipelines.add(pipelineBuilder.build(pipelineConfig));
        }
        Pipeline first = pipelines.get(0);
        first.extract();
        first.transform();
        first.load();

        Map<String, Object> analysisMap = first.getModel().getAnalysisMap();
        int count = (int) analysisMap.get("Number of Triangles");

        Assert.assertEquals(2, count);

    }
}

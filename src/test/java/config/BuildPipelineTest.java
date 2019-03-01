package config;

import com.google.inject.Guice;
import com.google.inject.Injector;
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
import pipeline.Pipeline;
import pipeline.PipelineBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BuildPipelineTest {

    private static AppConfig moonConfig, sampleConfig, nPipeConfig;
    private static AnalysisFactory analysisFactory;
    private static ExtractorFactory extractorFactory;
    private static LoaderFactory loaderFactory;
    private static ParserFactory parserFactory;
    private static PipelineBuilder pipelineBuilder;


    @BeforeClass
    public static void setUp() {
        InputStream stream1 = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("configFiles/moon.yaml");
        moonConfig = AppLoader.loadConfiguration(stream1);

        InputStream stream2 = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("configFiles/sample.yaml");
        sampleConfig = AppLoader.loadConfiguration(stream2);

        InputStream stream3 = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("configFiles/2pipes.yaml");
        nPipeConfig = AppLoader.loadConfiguration(stream3);

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
    public void moonConfigBuildTest() {
        List<Pipeline> pipelines = new ArrayList<>();
        for (PipelineConfig pipelineConfig : moonConfig.getPipelineConfigs()) {
            pipelines.add(pipelineBuilder.build(pipelineConfig));
        }
        Assert.assertEquals(1, pipelines.size());

        Pipeline first = pipelines.get(0);
        Assert.assertEquals("moon", first.getName());
        Assert.assertEquals(1, first.getExtractorConf().size());
        Assert.assertEquals(3, first.getStatistics().size());
        Assert.assertEquals(2, first.getLoaders().size());
    }

    @Test
    public void sampleConfigBuildTest() {
        List<Pipeline> pipelines = new ArrayList<>();
        for (PipelineConfig pipelineConfig : sampleConfig.getPipelineConfigs()) {
            pipelines.add(pipelineBuilder.build(pipelineConfig));
        }
        Assert.assertEquals(1, pipelines.size());

        Pipeline first = pipelines.get(0);
        Assert.assertEquals("simple", first.getName());
        Assert.assertEquals(1, first.getExtractorConf().size());
        Assert.assertEquals(1, first.getStatistics().size());
        Assert.assertEquals(1, first.getLoaders().size());

    }

    @Test
    public void nPipelineBuildTest() {
        List<Pipeline> pipelines = new ArrayList<>();
        for (PipelineConfig pipelineConfig : nPipeConfig.getPipelineConfigs()) {
            pipelines.add(pipelineBuilder.build(pipelineConfig));
        }
        Assert.assertEquals(2, pipelines.size());

        Pipeline first = pipelines.get(0);
        Assert.assertEquals("moon", first.getName());
        Assert.assertEquals(1, first.getExtractorConf().size());
        Assert.assertEquals(3, first.getStatistics().size());
        Assert.assertEquals(1, first.getLoaders().size());

        Pipeline second = pipelines.get(1);
        Assert.assertEquals("simple", second.getName());
        Assert.assertEquals(1, second.getExtractorConf().size());
        Assert.assertEquals(3, second.getStatistics().size());
        Assert.assertEquals(1, second.getLoaders().size());
    }
}

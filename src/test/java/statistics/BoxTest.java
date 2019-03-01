package statistics;

import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppConfig;
import config.AppLoader;
import config.PipelineConfig;
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
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pipeline.Pipeline;
import pipeline.PipelineBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoxTest {
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
                .getResourceAsStream("configFiles/sample_box.yaml");
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
    public void testBoxAnalysis() {
        List<Pipeline> pipelines = new ArrayList<>();
        for (PipelineConfig pipelineConfig : sampleConfig.getPipelineConfigs()) {
            pipelines.add(pipelineBuilder.build(pipelineConfig));
        }
        Pipeline first = pipelines.get(0);
        first.extract();
        first.transform();
        first.load();

        Map<String, Object> analysisMap = first.getModel().getAnalysisMap();
        ArrayList<Triple<Double, Double, Double>> boxList =
                (ArrayList<Triple<Double, Double, Double>>) analysisMap.get("Box");

        Assert.assertTrue(boxList.contains(Triple.of(0.0, 0.0, 0.0)));
        Assert.assertTrue(boxList.contains(Triple.of(0.0, 1.0, 0.0)));
        Assert.assertTrue(boxList.contains(Triple.of(0.0, 0.0, 1.0)));
        Assert.assertTrue(boxList.contains(Triple.of(0.0, 1.0, 1.0)));
        Assert.assertTrue(boxList.contains(Triple.of(1.0, 0.0, 0.0)));
        Assert.assertTrue(boxList.contains(Triple.of(1.0, 0.0, 1.0)));
        Assert.assertTrue(boxList.contains(Triple.of(1.0, 1.0, 0.0)));
        Assert.assertTrue(boxList.contains(Triple.of(1.0, 1.0, 1.0)));

    }


}

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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultipleAnalysisTest {

    private static PipelineBuilder pipelineBuilder;
    private static List<Pipeline> pipelines;

    @BeforeClass
    @SuppressWarnings("Duplicates")
    public static void setup() {
        InputStream stream1 = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("configFiles/2pipes.yaml");
        AppConfig nConfig = AppLoader.loadConfiguration(stream1);

        Injector analysisInjector = Guice.createInjector(new AnalysisModule());
        AnalysisFactory analysisFactory = analysisInjector.getInstance(GuiceAnalysisFactory.class);

        Injector extractorInjector = Guice.createInjector(new ExtractorModule());
        ExtractorFactory extractorFactory = extractorInjector.getInstance(GuiceExtractorFactory.class);

        Injector loaderInjector = Guice.createInjector(new LoaderModule());
        LoaderFactory loaderFactory = loaderInjector.getInstance(GuiceLoaderFactory.class);

        Injector parserInjector = Guice.createInjector(new ParserModule());
        ParserFactory parserFactory = parserInjector.getInstance(GuiceParserFactory.class);

        pipelineBuilder = new PipelineBuilder(analysisFactory, extractorFactory, loaderFactory, parserFactory);

        List<Pipeline> pipelineList = new ArrayList<>();
        for (PipelineConfig pipelineConfig : nConfig.getPipelineConfigs()) {
            pipelineList.add(pipelineBuilder.build(pipelineConfig));
        }
        pipelines = pipelineList;
    }

    @Test
    public void multiBuildTest() {
        Assert.assertEquals(2, pipelines.size());
    }


    private void simpleBoxTest(Pipeline pipeline) {
        Map<String, Object> analysisMap = pipeline.getModel().getAnalysisMap();
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

    private void moonBoxTest(Pipeline moonPipeline) {
        Map<String, Object> analysisMap = moonPipeline.getModel().getAnalysisMap();
        ArrayList<Triple<Double, Double, Double>> boxList =
                (ArrayList<Triple<Double, Double, Double>>) analysisMap.get("Box");

        Assert.assertTrue(boxList.contains(Triple.of(0.0, 0.0, 0.0)));
        Assert.assertTrue(boxList.contains(Triple.of(0.0, 0.35, 0.0)));
        Assert.assertTrue(boxList.contains(Triple.of(0.0, 0.35, 3.0)));
        Assert.assertTrue(boxList.contains(Triple.of(1.62841, 0.0, 0.0)));
        Assert.assertTrue(boxList.contains(Triple.of(1.62841, 0.0, 3.0)));
        Assert.assertTrue(boxList.contains(Triple.of(1.62841, 0.35, 0.0)));
        Assert.assertTrue(boxList.contains(Triple.of(0.0, 0.0, 3.0)));
        Assert.assertTrue(boxList.contains(Triple.of(1.62841, 0.35, 3.0)));
    }

    private void modelAreaTest(double expected, Pipeline pipeline) {
        Map<String, Object> analysisMap = pipeline.getModel().getAnalysisMap();
        double area = (double) analysisMap.get("Surface Area");
        DecimalFormat df = new DecimalFormat("#.######");
        df.setRoundingMode(RoundingMode.CEILING);

        String area_rounded = df.format(area);
        Assert.assertEquals(String.valueOf(expected), area_rounded);
    }

    private void modelCountTest(int expected, Pipeline pipeline) {

        Map<String, Object> analysisMap = pipeline.getModel().getAnalysisMap();
        int count = (int) analysisMap.get("Number of Triangles");

        Assert.assertEquals(expected, count);

    }


    @Test
    public void runPipesTest() {
        pipelines.forEach(x -> {
            x.extract();
            x.transform();
        });

        Pipeline first = pipelines.get(0);
        Pipeline second = pipelines.get(1);

        moonBoxTest(first);
        simpleBoxTest(second);

        modelAreaTest(7.772635, first);
        modelAreaTest(1.414214, second);

        modelCountTest(116, first);
        modelCountTest(2, second);


    }

}

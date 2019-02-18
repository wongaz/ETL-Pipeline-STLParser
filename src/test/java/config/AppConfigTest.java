package config;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;

public class AppConfigTest {
    private static AppConfig moonConfig, appConfig2, nPipeConfig;
    private static PipelineConfig moonPipeline, samplePipeline;
    @BeforeClass
    public static void setUp(){
        InputStream stream1 = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("configFiles/moon.yaml");
        moonConfig = AppLoader.loadConfiguration(stream1);
        moonPipeline = moonConfig.getPipelineConfigs()[0];

        InputStream stream2= Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("configFiles/sample.yaml");
        appConfig2 = AppLoader.loadConfiguration(stream2);
        samplePipeline = appConfig2.getPipelineConfigs()[0];

        InputStream stream3= Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("configFiles/2pipes.yaml");
        nPipeConfig = AppLoader.loadConfiguration(stream3);
    }

    @Test
    public void testAppLoader(){
        Assert.assertNotNull(moonConfig);
        Assert.assertNotNull(appConfig2);
        Assert.assertNotNull(nPipeConfig);
    }

    @Test
    public void testExtractType() {
        Assert.assertEquals("file", moonPipeline.getExtractType());
        Assert.assertEquals("file",samplePipeline.getExtractType());

    }

    @Test
    public void testPipelineName(){
        Assert.assertEquals("moon", moonPipeline.getPipelineName());
        Assert.assertEquals("simple",samplePipeline.getPipelineName());
    }

    @Test
    public void testNumberOfPipelineConfig(){
        Assert.assertEquals(1,moonConfig.getPipelineConfigs().length);
        Assert.assertEquals(1,appConfig2.getPipelineConfigs().length);
        Assert.assertEquals(2, nPipeConfig.getPipelineConfigs().length);
    }

    @Test
    public void testExtractConfiguration() {
        String fileName = samplePipeline.getExtractConfiguration().get("file");
        Assert.assertEquals("STLFiles/simple.stl", fileName);

    }

    @Test
    public void testParseFormat() {
        Assert.assertEquals("STL", moonPipeline.getParseFormat());
        Assert.assertEquals("STL", samplePipeline.getParseFormat());
    }


}

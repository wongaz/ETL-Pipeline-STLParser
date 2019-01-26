package config;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;

public class AppConfigTest {
    private static AppConfig appConfig1, appConfig2;

    @BeforeClass
    public static void setUp(){
        InputStream stream1 = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("test.yaml");
        appConfig1 = AppLoader.loadConfiguration(stream1);

        InputStream stream2= Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("sample.yaml");
        appConfig2 = AppLoader.loadConfiguration(stream2);
    }

    @Test
    public void testAppLoader(){
        Assert.assertNotNull(appConfig1);
        Assert.assertNotNull(appConfig2);
    }

    @Test
    public void testExtractType() {
        Assert.assertEquals("db_mongo", appConfig1.getExtractType());
        Assert.assertEquals("file", appConfig2.getExtractType());
    }

    @Test
    public void testExtractConfiguration() {

    }

    @Test
    public void testParseFormat() {
        Assert.assertEquals("STL", appConfig1.getParseFormat());
    }

    @Test
    public void testLoadType() {

    }

    @Test
    public void testLoadConfiguration() {

    }


}

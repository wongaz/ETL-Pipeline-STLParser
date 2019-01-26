package config;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class AppConfigTest {
    private static AppConfig appConfig1, appConfig2, badConfig;

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
//
//        InputStream stream3= Thread.currentThread()
//                .getContextClassLoader()
//                .getResourceAsStream("samp.yaml");
//        badConfig = AppLoader.loadConfiguration(stream3);



    }

    @Test
    public void testAppLoader(){
        Assert.assertNotNull(appConfig1);
        Assert.assertNotNull(appConfig2);
//        Assert.assertNull(badConfig);
    }

}

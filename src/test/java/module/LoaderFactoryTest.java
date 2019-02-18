package module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.guice.GuiceLoaderFactory;
import load.FileOutLoader;
import load.ILoader;
import load.StandardOutLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoaderFactoryTest {
    static GuiceLoaderFactory guiceLoaderFactory;

    @BeforeClass
    public static void setUp() {
        Injector injector = Guice.createInjector(new LoaderModule());
        guiceLoaderFactory = injector.getInstance(GuiceLoaderFactory.class);
    }

    @Test
    public void getNewFileOut() {
        ILoader fileOutLoader1 = guiceLoaderFactory.getLoader("file");
        ILoader fileOutLoader2 = guiceLoaderFactory.getLoader("file");
        Assert.assertNotNull(fileOutLoader1);
        Assert.assertNotNull(fileOutLoader2);
        Assert.assertFalse(fileOutLoader1 == fileOutLoader2);
        Assert.assertTrue(fileOutLoader1 instanceof FileOutLoader);
        Assert.assertTrue(fileOutLoader2 instanceof FileOutLoader);

    }

    @Test
    public void getNewStandOut() {
        ILoader StandardOutLoader1 = guiceLoaderFactory.getLoader("stdout");
        ILoader StandardOutLoader2 = guiceLoaderFactory.getLoader("stdout");
        Assert.assertNotNull(StandardOutLoader1);
        Assert.assertNotNull(StandardOutLoader2);
        Assert.assertFalse(StandardOutLoader1 == StandardOutLoader2);
        Assert.assertTrue(StandardOutLoader1 instanceof StandardOutLoader);
        Assert.assertTrue(StandardOutLoader2 instanceof StandardOutLoader);
    }
}

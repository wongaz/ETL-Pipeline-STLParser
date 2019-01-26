import config.AppConfig;
import config.AppLoader;

import java.io.File;

public class STLMain {
    public static void main(String... args){
        String conf = "configFiles/moon.yaml";
        File file = new File(conf);
        AppConfig appConfig = AppLoader.loadConfiguration(file);
        System.out.println(appConfig.toString());
    }
}

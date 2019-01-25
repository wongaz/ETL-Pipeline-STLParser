import config.AppConfig;
import config.AppLoader;

public class STLMain {
    public static void main(String... args){
        String conf = "configFiles/moon.yaml";
        AppConfig appConfig = AppLoader.loadConfiguration(conf);
        System.out.println(appConfig.toString());
    }
}

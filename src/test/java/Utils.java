import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public static void loadConfigs(Properties properties){
        final String configFile = "config.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resourceStream = loader.getResourceAsStream(configFile);
        try {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

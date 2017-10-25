import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Drivers {
    public static Map<WebDriver, Integer> chromeDrivers = new HashMap<>();
    public static Map<WebDriver, Integer> firefoxDrivers = new HashMap<>();
    private static Properties properties = new Properties();
    private static String browser;
    private static Boolean isGrid;

    static {
        Utils.loadConfigs(properties);
        browser = String.valueOf(properties.get("browser")).toLowerCase();
        isGrid = String.valueOf(properties.get("grid")).equalsIgnoreCase("true");
    }


    public static WebDriver getDriver() {
        WebDriver driver = null;
        if (isGrid) {
            //TODO : Write grid logic here
        } else {
            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
                    driver = getLocalChromeDriver();
                    break;
            }
        }
        return driver;
    }

    public static WebDriver getLocalChromeDriver() {
        for (Map.Entry<WebDriver, Integer> entry : chromeDrivers.entrySet()) {
            if (entry.getValue() == 0) {
                entry.setValue(1);
                return entry.getKey();
            }
        }
        WebDriver driver = new ChromeDriver();
        chromeDrivers.put(driver, 1);
        return driver;
    }

    public static boolean releaseChromeDriver(WebDriver driver) {
        for (Map.Entry<WebDriver, Integer> entry : chromeDrivers.entrySet()) {
            if (entry.getKey().equals(driver)) {
                entry.setValue(0);
                return true;
            }
        }
        return false;
    }

    public static void releaseDriver(WebDriver driver) {
        if (isGrid) {
            //TODO : Write grid logic here
        } else {
            switch (browser) {
                case "chrome":
                    releaseChromeDriver(driver);
                    break;
            }
        }
    }

    public static void killAll() {
        for (Map.Entry<WebDriver, Integer> entry : chromeDrivers.entrySet()) {
            if (entry.getKey() != null)
                entry.getKey().quit();
        }
    }
}

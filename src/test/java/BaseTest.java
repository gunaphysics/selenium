import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {
    private static Properties properties = new Properties();
    protected WebDriver driver;

    @BeforeTest
    public void initTest() {
        driver = Drivers.getDriver();
    }

    @AfterTest
    public void tearTest() {
        Drivers.releaseDriver(driver);
    }

    @AfterSuite
    public void killDrivers(){
        Drivers.killAll();
    }
}

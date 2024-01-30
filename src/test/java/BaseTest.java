import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import com.datasite.utils.DriverFactory;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    protected WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeTest
    public void setUp() {
        driver = DriverFactory.getDriver();
        softAssert = new SoftAssert();
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            DriverFactory.quitDriver();
        }
    }
}


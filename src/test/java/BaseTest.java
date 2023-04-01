import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    ExtentReports extent;
    ExtentHtmlReporter htmlReporter;
    ExtentTest logger;

    @BeforeSuite
    public void reportSetup() {
        htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setup(Method m) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://mail.google.com/");
        logger = extent.createTest(m.getName());
    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, result.getThrowable());
            String screenshot = captureScreenshot(driver);
            logger.fail("Screenshot: ", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, result.getTestName());
        } else {
            logger.log(Status.SKIP, result.getTestName());
        }
        extent.flush();
        driver.quit();
    }

    public static String captureScreenshot(WebDriver driver) {

        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        String screenshot = scrShot.getScreenshotAs(OutputType.BASE64);
        return screenshot;
    }
}

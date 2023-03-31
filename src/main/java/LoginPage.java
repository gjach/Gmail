import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage{

    @FindBy(id = "identifierId")
    public WebElement inputLogin;
    @FindBy(xpath = ".//span[text()='Dalej']")
    public WebElement nextBtn;
    @FindBy(css = "input[type='password'")
    public WebElement inputPassword;

    public LoginPage (WebDriver driver){
        super(driver);
    }
    public MailboxPage login(String username, String password){
        inputLogin.sendKeys(username);
        nextBtn.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='password'")));
        inputPassword.sendKeys(password);
        nextBtn.click();
        wait.until(ExpectedConditions.textToBe(By.xpath(".//div[text()='Utwórz']"), "Utwórz"));
        return new MailboxPage(driver);
    }
}

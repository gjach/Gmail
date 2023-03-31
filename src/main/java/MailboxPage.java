import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MailboxPage extends BasePage {

    @FindBy(xpath = ".//div[text()='Utwórz']")
    public WebElement newMessageBtn;
    @FindBy(css = "div[role='textbox']")
    public WebElement messageTextField;
    @FindBy(css = "input[placeholder='Temat']")
    public WebElement subjectTextField;
    @FindBy(xpath = ".//div[text()='Wyślij']")
    public WebElement sendBtn;

    public MailboxPage(WebDriver driver) {
        super(driver);
    }

    public MailboxPage createNewMessage() {
        newMessageBtn.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Temat']")));
        return new MailboxPage(driver);
    }

    public MailboxPage completeMessageFields(String recipients, String subject, String message) {
        actions = new Actions(driver);
        actions.sendKeys(recipients).build().perform();
        messageTextField.sendKeys(message);
        subjectTextField.sendKeys(subject);
        return new MailboxPage(driver);
    }

    public MailboxPage sendMessage(){
        sendBtn.click();
        return new MailboxPage(driver);
    }

    public void waitForMessageAndClickIt(String subject) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//span/span[text()='" + subject + "']"))).click();
    }
}

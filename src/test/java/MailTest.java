import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
public class MailTest extends BaseTest {

    By actualTitleLocator = By.xpath(".//h2[text()='" + Config.subject + "']");
    By actualContentLocator = By.xpath(".//div[text()='" + Config.message + "']");


    @Test
public void sendEmailTest(){
        loginPage = new LoginPage(driver);
        loginPage.login(Config.username,Config.password)
                .createNewMessage()
                .completeMessageFields(Config.recipients, Config.subject, Config.message)
                .sendMessage()
                .waitForMessageAndClickIt(Config.subject);
        WebElement actualTitle = driver.findElement(actualTitleLocator);
        WebElement actualContent = driver.findElement(actualContentLocator);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle.getText(), Config.subject);
        softAssert.assertEquals(actualContent.getText(), Config.message);
        softAssert.assertAll();
    }
}

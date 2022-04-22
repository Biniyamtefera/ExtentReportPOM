package Orlicom;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class SeleniumLoginTest {
    WebDriver driver;
    String baseUrl;
    ExtentReports report;
    ExtentTest test;
    @BeforeClass
    public void beforeClass() {
        baseUrl = "http://www.letskodeit.com/";
       // driver = new FirefoxDriver();
        WebDriverManager.chromedriver().setup();
        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test
    public void test1_validLoginTest() throws Exception {
        WebElement signupLink = driver.findElement(By.id("comp-iiqg1vggactionTitle"));
        ////div[text()='Sign Up or Log In']
        signupLink.click();

        WebElement loginLink = driver.findElement(By.id("signUpDialogswitchDialogLink"));
        loginLink.click();

        WebElement emailField = driver.findElement(By.xpath("//div[@id='memberLoginDialogemail']//input"));
        ////input[@id='email'](2)

        emailField.sendKeys("test@email.com");

        WebElement passwordField = driver.findElement(By.xpath("//div[@id='memberLoginDialogpassword']//input"));
        ////input[@id='password']
        passwordField.sendKeys("abcabc");

        WebElement goButton = driver.findElement(By.id("memberLoginDialogsubmitButton"));
        goButton.click();

        Thread.sleep(3000);

        WebElement welcomeText = null;

        try {
            welcomeText = driver.findElement(By.xpath("//div[text()='Hello test@email.com']"));
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(welcomeText != null);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

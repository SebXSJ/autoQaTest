package pl.jsystems.qa.qagui.classic;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.jsystems.qa.qagui.classic.page.LoginPage;
import pl.jsystems.qa.qagui.classic.page.MainWordpressPage;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static com.google.common.truth.Truth.assertThat;
import static java.lang.Thread.sleep;

@Tag("frontend")
@DisplayName("Frontend test")
public class FrontendTest {


    private WebDriver driver;


    /*@BeforeAll
    public void setupUP() throws URISyntaxException {
        //System.setProperty("webdriver.chrome.driver", Paths.get(FrontendTest.class.getClassLoader().getResource("drivers/chromedriver.exe").toURI()).toFile().getAbsolutePath());
        //driver = new ChromeDriver();
        //baseUrl = "https://www.google.com/";
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }*/

    @BeforeEach
    public void setUP(){
        try {
            System.setProperty("webdriver.chrome.driver", Paths.get(getClass().getClassLoader().getResource("driver/chromedriver.exe").toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
    MainWordpressPage mainWordpressPage;
    LoginPage loginPage;

    @DisplayName("Login test")
    @Test
    public void loginTest() throws InterruptedException {

        driver.get("https://wordpress.com/");
        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainWordpressPage = new MainWordpressPage(driver);
        loginPage = new LoginPage(driver);
        mainWordpressPage.loginButton.click();
        //driver.findElement(By.cssSelector(".x-nav-item.x-nav-item--wide.x-nav-item--logged-in")).click();
        loginPage.loginInput.clear();
        loginPage.loginInput.clear();
        loginPage.loginInput.click();
        loginPage.loginInput.sendKeys("sebastianpacko");
        driver.findElement(By.cssSelector(".button.button.form-button.is-primary")).click();

    }

    @AfterEach
    public void after(){
        driver.quit();
    }

}

package pl.jsystems.qa.qagui.classic;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.jsystems.qa.qagui.classic.Function.LoginFunction;
import pl.jsystems.qa.qagui.classic.page.MainUserPage;
import pl.jsystems.qa.qagui.classic.page.UserPage;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import static com.google.common.truth.Truth.assertThat;
import static java.lang.Thread.sleep;

@Tag("frontend")
@DisplayName("Frontend test")
public class FrontendTest {

    private WebDriver driver;

    @BeforeAll
    public static void setUpBefore() {
    }

    @BeforeEach
    public void setUpEach() {
        try {
            System.setProperty("webdriver.chrome.driver", Paths.get(getClass().getClassLoader().getResource("driver/chromedriver.exe").toURI()).toFile().getAbsolutePath());
            System.setProperty("webdriver.gecko.driver", Paths.get(getClass().getClassLoader().getResource("driver/geckodriver.exe").toURI()).toFile().getAbsolutePath());
            System.setProperty("webdriver.edge.driver", Paths.get(getClass().getClassLoader().getResource("driver/msedgedriver.exe").toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
//        driver = new SafariDriver();
//        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

    }

    @DisplayName("Login test")
    @Test
    public void loginTest() {
        driver.get("https://wordpress.com/");
        driver.findElement(By.cssSelector(".x-nav-item.x-nav-item--wide.x-nav-item--logged-in")).click();
        driver.findElement(By.id("usernameOrEmail")).clear();
        driver.findElement(By.id("usernameOrEmail")).click();
        driver.findElement(By.id("usernameOrEmail")).sendKeys("sebastianpacko");
        driver.findElement(By.cssSelector(".button.form-button.is-primary")).click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("zaq12WSX");
        driver.findElement(By.cssSelector(".button.form-button.is-primary")).click();

        String welcomeText = driver.findElement(By.className("empty-content__title")).getText();

        assertThat(welcomeText).isEqualTo("Witaj w Czytniku");

    }

    @DisplayName("Check user")
    @Test
    public void checkUser() {
        driver.get("https://wordpress.com/");

        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();

        MainUserPage mainUserPage = new MainUserPage(driver);
        mainUserPage.userAvatar.click();

        UserPage userProfilePage = new UserPage(driver);
        String userName = userProfilePage.userNamePanel.getText();

        assertThat(userName).isEqualTo("sebastianpacko");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}
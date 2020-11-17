package pl.jsystems.qa.qagui.classic.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage extends BasicPage {
    public UserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "profile-gravatar__user-display-name")
    public WebElement userNamePanel;
//    public WebElement userNamePanel = driver.findElement(By.className("profile-gravatar__user-display-name"));


}

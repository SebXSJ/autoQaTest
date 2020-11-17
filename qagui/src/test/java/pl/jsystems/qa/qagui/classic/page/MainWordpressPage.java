package pl.jsystems.qa.qagui.classic.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainWordpressPage {
    WebDriver driver;

    public MainWordpressPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement loginButton = driver.findElement(By.cssSelector(".x-nav-item.x-nav-item--wide.x-nav-item--logged-in"));


}

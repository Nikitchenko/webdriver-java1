package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import com.cucumber.junit.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.cucumber.junit.constants.Constants.BOOKDEPOSITORY_URL;

public abstract class AbstractPage {

    protected WebDriverWait waitExplicit = Waiter.waitExplicit();

    protected AbstractPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public static void openSitePage(String url) {
        DriverManager.getDriver().get(BOOKDEPOSITORY_URL + url);
    }

    public static String getURL() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public WebElement findElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public boolean isElementDisplayed(By by) {
        return !findElement(by).isDisplayed();
    }
}

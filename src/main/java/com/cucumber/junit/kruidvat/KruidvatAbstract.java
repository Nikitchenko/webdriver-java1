package com.cucumber.junit.kruidvat;

import com.cucumber.junit.driver.DriverManager;
import com.cucumber.junit.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.cucumber.junit.constants.Constants.BOOKDEPOSITORY_URL;

public abstract class KruidvatAbstract {

    protected WebDriverWait waitExplicit = Waiter.waitExplicit();

    protected KruidvatAbstract() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public static void openSitePage(String url) {
        DriverManager.getDriver().get( url);
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


    public static void clearAllCookies() {
        DriverManager.getDriver().manage().deleteAllCookies(); // Deletes all the cookies
    }


    public static void addTheCookie(String cookieName, String cookieValue) {
        DriverManager.getDriver().manage().addCookie(new Cookie(cookieName, cookieValue)); //Creates and adds the cookie
    }
}

package com.cucumber.junit.pages;

import com.codeborne.selenide.Condition;
import com.cucumber.junit.driver.DriverManager;
import com.cucumber.junit.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.cucumber.junit.constants.Constants.BOOKDEPOSITORY_URL;

public abstract class AbstractPage {

    protected WebDriverWait waitExplicit = Waiter.waitExplicit();

    protected AbstractPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public static void openSitePage(String url) {
        open(BOOKDEPOSITORY_URL + url);
    }

    public static String getURL() {
        return url();
    }

    public WebElement findElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }



    public void selectOptionInSelector(WebElement selector, String optionText) {
        $(selector).shouldBe(Condition.visible)
                .selectOptionContainingText(optionText);
    }

    public void provideTextInInputField(WebElement inputField, String text) {
        $(inputField).setValue(text);
    }

    public void elementClick (WebElement element) {
        $(element).click();
    }

    public String getElementText(WebElement element) {
        return $(element).getText();
    }

    public boolean isElementDisplayed(WebElement element) {
        return $(element).isDisplayed();
    }


}

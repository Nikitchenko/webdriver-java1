package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import com.cucumber.junit.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    //protected Waiter waiter = new Waiter();
    protected WebDriverWait waitExplicit = Waiter.waitExplicit();

    public AbstractPage(){
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public WebElement findElement(By by){
        return DriverManager.getDriver().findElement(by);
    }

    public boolean isElementDisplayed(By by){
        return !findElement(by).isDisplayed();
    }
}

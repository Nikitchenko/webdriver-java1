package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

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

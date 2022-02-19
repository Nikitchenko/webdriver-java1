package com.cucumber.junit.pages;


import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.swing.*;

import static com.cucumber.junit.constants.Constants.BOOKDEPOSITORY_URL;

public class HomePage extends AbstractPage {

    //private static final String BOOKDEPOSITORY_URL = "https://www.bookdepository.com/";
    //private static final String MENU_SEARCH_XPATH = "//input[@name = 'searchTerm']";

    public void openBookdepositoryWebsite(){
        DriverManager.getDriver().get(BOOKDEPOSITORY_URL);
    }

//    public WebElement getMenuSearch(){
//        return findElement(By.xpath(MENU_SEARCH_XPATH));
//    }

    @FindBy(xpath = "//input[@name = 'searchTerm']")
    private WebElement menuSearchInput;

    public WebElement getMenuSearchInput(){
        return menuSearchInput;
    }


}

package com.cucumber.junit.pages;


import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.*;

public class HomePage extends AbstractPage {

    private static final String BOOKDEPOSITORY_URL = "https://www.bookdepository.com/";
    private static final String MENU_SEARCH_XPATH = "//input[@name = 'searchTerm']";

    public void openBookdepositoryWebsite(){
        DriverManager.getDriver().get(BOOKDEPOSITORY_URL);
    }

    public WebElement getMenuSearch(){
        return findElement(By.xpath(MENU_SEARCH_XPATH));
    }

}

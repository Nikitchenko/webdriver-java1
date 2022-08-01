package com.cucumber.junit.util;

import com.cucumber.junit.driver.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {

    public static WebDriverWait waitExplicit(){
       WebDriverWait waitExplicit = new WebDriverWait(WebDriverManager.getDriver(), 15 );
       return waitExplicit;
    }

}

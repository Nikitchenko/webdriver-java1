package com.cucumber.junit.util;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {

    public static WebDriverWait waitExplicit(){
       WebDriverWait waitExplicit = new WebDriverWait(DriverManager.getDriver(), 15 );
       return waitExplicit;
    }

}

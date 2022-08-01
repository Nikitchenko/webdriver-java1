package com.cucumber.junit.hooks;

import com.cucumber.junit.driver.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DriverHooks {

    @Before
    public void setupDriver(){
        WebDriverManager.setupDriver();
    }

    @After
    public void quitDriver(){
        WebDriverManager.quitDriver();
    }

}

package com.cucumber.junit.hooks;

import com.cucumber.junit.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverHooks {

    private static final Logger logger = LogManager.getLogger(DriverHooks.class.getName());

    @Before
    public void setupDriver(){
        DriverManager.setupDriver();
        logger.info("Web Driver start.");
    }

    @After
    public void quitDriver(){
        DriverManager.quitDriver();
        logger.info("Web Driver quit.");
    }

}

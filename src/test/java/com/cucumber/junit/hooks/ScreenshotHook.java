package com.cucumber.junit.hooks;

import com.cucumber.junit.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotHook {

    private static final Logger logger = LogManager.getLogger(ScreenshotHook.class.getName());
    private static final String PNG_FILE_EXTENSION = "image/png";

    @After
    public void takeScreenshot(Scenario scenario){

        if (scenario.isFailed()) {
            logger.error("Scenario is failed.");

            logger.debug("Current URL is: " );
            scenario.log(DriverManager.getDriver().getCurrentUrl());
            // log instead of write

            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, PNG_FILE_EXTENSION, scenario.getName());
            // attach instead of embed
            logger.debug("Screenshot taken.");
        }
    }
}

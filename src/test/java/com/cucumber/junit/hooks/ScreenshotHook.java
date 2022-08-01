package com.cucumber.junit.hooks;

import com.cucumber.junit.driver.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotHook {

    private static final String PNG_FILE_EXTENSION = "image/png";

    @After
    public void takeScreenshot(Scenario scenario){
        if (scenario.isFailed()) {
            scenario.log(WebDriverManager.getDriver().getCurrentUrl());
            // log instead of write

            byte[] screenshot = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, PNG_FILE_EXTENSION, scenario.getName());
            // attach instead of embed
        }
    }
}

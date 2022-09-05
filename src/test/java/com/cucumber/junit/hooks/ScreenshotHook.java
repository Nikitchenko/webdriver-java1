package com.cucumber.junit.hooks;

import com.codeborne.selenide.Selenide;
import com.cucumber.junit.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Base64;

import static com.codeborne.selenide.Selenide.screenshot;

public class ScreenshotHook {

    private static final String PNG_FILE_EXTENSION = "image/png";

    @After
    public void takeScreenshot(Scenario scenario){
        if (scenario.isFailed()) {
            scenario.log(DriverManager.getDriver().getCurrentUrl());
            // log instead of write

            String screenshotAsBase64 = Selenide.screenshot(OutputType.BASE64);
            byte[] decoded = Base64.getDecoder().decode(screenshotAsBase64);
            scenario.attach(decoded, PNG_FILE_EXTENSION, scenario.getName());
            // attach instead of embed
        }
    }
}

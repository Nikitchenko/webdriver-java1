package com.cucumber.junit.driver;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class DriverManager {

    private static final String CHROME_DRIVER_PATH = "src/main/resources/chromedriver.exe";

    private DriverManager() {
    }

    public static void setupDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(12, TimeUnit.SECONDS);

        Configuration.reportsFolder = "target/selenide-reports";
        setWebDriver(driver);

    }

    public static WebDriver getDriver() {
        return getWebDriver();

    }

    public static void quitDriver() {
        Optional.ofNullable(getDriver()).ifPresent(webDriver ->
            webDriver.quit()
        );
    }
}

package com.cucumber.junit.driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private  static final String CHROME_DRIVER_PATH = "src/main/resources/chromedriver.exe";
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setupDriver(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriverThreadLocal.set(driver);
    }

    public static WebDriver getDriver(){
        return webDriverThreadLocal.get();

    }
    public static void quitDriver(){
        Optional.ofNullable(getDriver()).ifPresent(webDriver -> {
            webDriver.quit();
            webDriverThreadLocal.remove();
        });

    }
}

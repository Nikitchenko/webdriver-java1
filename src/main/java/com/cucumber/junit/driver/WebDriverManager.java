package com.cucumber.junit.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {

    private static final String CHROME_DRIVER_PATH = "src/main/resources/chromedriver.exe";
    private static final String GECKO_DRIVER_PATH = "src/main/resources/geckodriver.exe";
    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private WebDriverManager() {
    }

    public static void setupDriver() {

        WebDriver driver;
        String browser = System.getProperty("browser");

        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.firefox.driver", GECKO_DRIVER_PATH);
            driver = new FirefoxDriver();
        }
        else {
            throw new IllegalStateException("This driver is not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(12, TimeUnit.SECONDS);
        webDriverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        return webDriverThreadLocal.get();

    }

    public static void quitDriver() {
        Optional.ofNullable(getDriver()).ifPresent(webDriver -> {
            webDriver.quit();
            webDriverThreadLocal.remove();
        });

    }
}

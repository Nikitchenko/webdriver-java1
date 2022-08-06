package com.cucumber.junit.pages;


import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.cucumber.junit.constants.Constants.BOOKDEPOSITORY_URL;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//input[@name = 'searchTerm']")
    private WebElement menuSearchInput;

    @FindBy(xpath = "//button[@class= 'header-search-btn']")
    private WebElement headerSearchBtn;

    public void openBookdepositoryWebsite() {
        DriverManager.getDriver().get(BOOKDEPOSITORY_URL);
    }

    public WebElement getMenuSearchInput() {
        return menuSearchInput;
    }

    public void searchForBook(String book) {
        menuSearchInput.sendKeys(book);
    }

    public SearchPage searchBtnClick() {
        waitExplicit.until(ExpectedConditions.elementToBeClickable(headerSearchBtn));
        headerSearchBtn.click();

        return new SearchPage();
    }

    public SearchPage searchBtnClickJS() {

        JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
        js.executeScript("arguments[0].click();", headerSearchBtn);

        return new SearchPage();
    }

}

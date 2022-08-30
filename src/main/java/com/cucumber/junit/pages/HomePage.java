package com.cucumber.junit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.open;
import static com.cucumber.junit.constants.Constants.BOOKDEPOSITORY_URL;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//input[@name = 'searchTerm']")
    private WebElement menuSearchInput;

    @FindBy(xpath = "//button[@class= 'header-search-btn']")
    private WebElement headerSearchBtn;

    public void openBookdepositoryWebsite() {
        open(BOOKDEPOSITORY_URL);
    }

    public void searchForBook(String book) {
        provideTextInInputField(menuSearchInput, book);
    }

    public SearchPage searchBtnClick() {
        elementClick(headerSearchBtn);
        return new SearchPage();
    }
}

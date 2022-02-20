package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.cucumber.junit.constants.Constants.BASKET_URL;
import static com.cucumber.junit.constants.Constants.PDP_URL;

public class BasketPage extends AbstractPage{

    public void openBasketPage(){
        DriverManager.getDriver().get(BASKET_URL);
    }

    @FindBy(xpath = "//dl[@class = 'total']/dd")
    private WebElement totalPrice;

    public WebElement getTotalPrice(){
        return totalPrice;
    }

    @FindBy(className = "item-total")
    private WebElement itemTotal;

    public WebElement getItemTotal(){
        return getTotalPrice();
    }

}

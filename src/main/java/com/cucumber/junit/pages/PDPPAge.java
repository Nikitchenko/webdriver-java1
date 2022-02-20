package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.cucumber.junit.constants.Constants.*;

public class PDPPAge extends AbstractPage{

    public void openPDPPage(String isbn){
        DriverManager.getDriver().get(PDP_URL+isbn);
    }

    @FindBy(xpath = "//a[@class = 'btn btn-primary add-to-basket' and contains(text(), 'Add to basket')]")
    private WebElement addToBasketBtn;

    public WebElement getAddToBasketBtn(){
        return addToBasketBtn;
    }

    @FindBy(xpath = "//div[@class = 'price-info-wrap']//span[@class = 'sale-price']")
    private WebElement salePrice;

    public WebElement getSalePrice(){
        return salePrice;
    }

    @FindBy(xpath = "//a[contains(text(), 'Basket / Checkout')]")
    private WebElement basketCheckoutLink;

    public WebElement getBasketCheckoutLink(){
        return basketCheckoutLink;
    }


    public BasketPage basketCheckoutLinkClick() {
        basketCheckoutLink.click();
        return new BasketPage();
    }

}

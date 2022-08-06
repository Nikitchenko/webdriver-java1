package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.cucumber.junit.constants.Constants.BASKET_URL;

public class BasketPage extends AbstractPage {

    @FindBy(className = "item-total")
    private WebElement itemTotalElem;

    @FindBy(xpath = "//dl[@class = 'total']/dd")
    private WebElement totalPriceElem;

    @FindBy(xpath = "//div[@class = 'checkout-btns-wrap']/a[@href = '/payment/guest' and text() = 'Checkout']")
    private WebElement checkoutBtn;

    @FindBy(xpath = "//dl[@class = 'delivery-text']/dd")
    private WebElement deliveryCost;

    public void openBasketPage() {
        DriverManager.getDriver().get(BASKET_URL);
    }

    public WebElement getItemTotalElem() {
        return itemTotalElem;
    }

    public String getItemTotal() {
        return itemTotalElem.getText();
    }

    public WebElement getTotalPriceElem() {
        return totalPriceElem;
    }

    public String getTotalPrice() {
        return totalPriceElem.getText();
    }

    public WebElement getCheckoutBtn() {
        return checkoutBtn;
    }

    public void checkoutBtnClick() {
        checkoutBtn.click();
    }

    public CheckoutPage checkoutPageOpened() {
        return new CheckoutPage();
    }

    public String getDeliveryCost() {
        return deliveryCost.getText();
    }

}

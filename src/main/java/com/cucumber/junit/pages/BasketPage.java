package com.cucumber.junit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.cucumber.junit.constants.Constants.BASKET_URL;

public class BasketPage extends AbstractPage {

    @FindBy(className = "item-total")
    private WebElement itemTotalElem;

    @FindBy(xpath = "//dl[@class = 'delivery-text']/dd")
    private WebElement deliveryCost;

    @FindBy(xpath = "//dl[@class = 'total']/dd")
    private WebElement totalPriceElem;

    @FindBy(xpath = "//div[@class = 'checkout-btns-wrap']/a[@href = '/payment/guest' and text() = 'Checkout']")
    private WebElement checkoutBtn;

    public void openBasketPage() {
        openSitePage(BASKET_URL);
    }

    public String getItemTotal() {
        return itemTotalElem.getText();
    }

    public String getDeliveryCost() {
        return getElementText(deliveryCost);
    }

    public String getTotalPrice() {
        return getElementText(totalPriceElem);
    }

    public void checkoutBtnClick() {
        elementClick(checkoutBtn);
    }

    public CheckoutPage checkoutPageOpened() {
        return new CheckoutPage();
    }

}

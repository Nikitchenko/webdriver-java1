package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.cucumber.junit.constants.Constants.BASKET_URL;
import static com.cucumber.junit.constants.Constants.CHECKOUT_URL;

public class CheckoutPage extends AbstractPage{

    public void openCheckoutPage(){
        DriverManager.getDriver().get(CHECKOUT_URL);
    }

    @FindBy(xpath = "//strong[text() = 'Sub-total']/../../dd[@class = 'text-right']")
    private WebElement checkoutSubtotalElem;

    @FindBy(xpath = "//strong[text() = 'VAT']/../../dd[@class = 'text-right total-tax']")
    private WebElement checkoutVATElem;

    @FindBy(xpath = "//strong[text() = 'Total']/../../dd[@class = 'text-right total-price']")
    private WebElement checkoutTotalElem;

    @FindBy(xpath = "//input[@name='emailAddress']")
    private WebElement checkoutEmailAddressField;

    @FindBy(xpath = "//button[text() = 'Buy now']")
    private WebElement buyNowBtn;

    @FindBy(xpath = "//input[@name='emailAddress']/following-sibling::div[@class = 'error-block']")
    private WebElement invalidErrorMessage;


    public WebElement getCheckoutSubtotalElem(){
        return checkoutSubtotalElem;
    }

    public WebElement getCheckoutVATElem() {
        return checkoutVATElem;
    }

    public WebElement getCheckoutTotalElem() {
        return checkoutTotalElem;
    }

    public WebElement getCheckoutEmailAddressField() {
        return checkoutEmailAddressField;
    }

    public WebElement getBuyNowBtn() {
        return buyNowBtn;
    }

    public WebElement getInvalidErrorMessage() {
        return invalidErrorMessage;
    }


}

package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.cucumber.junit.constants.Constants.CHECKOUT_URL;

public class CheckoutPage extends AbstractPage {

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
    private WebElement invalidErrorMessageElem;

    public void openCheckoutPage() {
        DriverManager.getDriver().get(CHECKOUT_URL);
    }

    public WebElement getCheckoutSubtotalElem() {
        return checkoutSubtotalElem;
    }

    public String getCheckoutSubtotal() {
        return checkoutSubtotalElem.getText();
    }

    public WebElement getCheckoutVATElem() {
        return checkoutVATElem;
    }

    public String getCheckoutVAT() {
        return checkoutVATElem.getText();
    }

    public WebElement getCheckoutTotalElem() {
        return checkoutTotalElem;
    }

    public String getCheckoutTotal() {
        return checkoutTotalElem.getText();
    }

    public WebElement getCheckoutEmailAddressField() {
        return checkoutEmailAddressField;
    }

    public WebElement getBuyNowBtn() {
        return buyNowBtn;
    }

    public WebElement getInvalidErrorMessageElem() {
        return invalidErrorMessageElem;
    }

    public String getInvalidErrorMessage() {
        return invalidErrorMessageElem.getText();
    }
}

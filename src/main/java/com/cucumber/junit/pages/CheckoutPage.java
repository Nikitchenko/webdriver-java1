package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static com.cucumber.junit.constants.Constants.CHECKOUT_URL;

public class CheckoutPage extends AbstractPage {

    @FindBy(xpath = "//strong[text() = 'Sub-total']/../../dd[@class = 'text-right']")
    private WebElement checkoutSubtotalElem;

    @FindBy(xpath = "//strong[text() = 'Delivery']/../../dd[@class = 'text-right']")
    private WebElement checkoutDeliveryElem;

    @FindBy(xpath = "//strong[text() = 'VAT']/../../dd[@class = 'text-right total-tax']")
    private WebElement checkoutVATElem;

    @FindBy(xpath = "//strong[text() = 'Total']/../../dd[@class = 'text-right total-price']")
    private WebElement checkoutTotalElem;

    @FindBy(xpath = "//input[@name='emailAddress']")
    private WebElement checkoutEmailAddressField;

    @FindBy(xpath = "//input[@name='delivery-telephone']")
    private WebElement checkoutDeliveryPhoneField;

    @FindBy(xpath = "//button[text() = 'Buy now']")
    private WebElement buyNowBtn;

    @FindBy(xpath = "//input[@name='emailAddress']/following-sibling::div[@class = 'error-block']")
    private WebElement invalidErrorMessageElem;

    @FindBy(xpath = "//input[@id = 'delivery-fullName']")
    WebElement fullNameField;

    @FindBy(xpath = "//select[@id = 'delivery-CountryDropdown']")
    private WebElement deliveryCountryDropdown;

    @FindBy(xpath = "//button[@name = 'manualEntryButton']")
    WebElement manualEntryAddressButton;

    @FindBy(xpath = "//input[@name = 'delivery-addressLine1']")
    WebElement deliveryAddressLine1Input;

    @FindBy(xpath = "//input[@name = 'delivery-addressLine2']")
    WebElement deliveryAddressLine2Input;

    @FindBy(xpath = "//input[@name = 'delivery-city']")
    WebElement deliveryCityInput;

    @FindBy(xpath = "//input[@name = 'delivery-county']")
    WebElement deliveryCountyInput;

    @FindBy(xpath = "//input[@name = 'delivery-postCode']")
    WebElement deliveryPostcodeInput;

    @FindBy(xpath = "//input[@id = 'credit-card-number']")
    WebElement creditCardNumberInput;

    public void openCheckoutPage() {
        DriverManager.getDriver().get(CHECKOUT_URL);
    }

    public WebElement getCheckoutSubtotalElem() {
        return checkoutSubtotalElem;
    }

    public String getCheckoutSubtotal() {
        return checkoutSubtotalElem.getText();
    }

    public String getCheckoutDelivery() {
        return checkoutDeliveryElem.getText();
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

    public void provideCheckoutEmail(String checkoutEmail){
        checkoutEmailAddressField.sendKeys(checkoutEmail);
    }

    public void provideCheckoutPhone(String checkoutPhone){
        checkoutDeliveryPhoneField.sendKeys(checkoutPhone);
    }

    public void provideFullName(String fullName){
        fullNameField.sendKeys(fullName);
    }

    public void setDeliveryCountry (String deliveryCountry){
        waitExplicit.until(ExpectedConditions.elementToBeClickable(deliveryCountryDropdown));
        Select selectCountry = new Select(deliveryCountryDropdown);
        selectCountry.selectByVisibleText(deliveryCountry);

    }

    public void manualEntryAddressButtonClick(){
        waitExplicit.until(ExpectedConditions.elementToBeClickable(manualEntryAddressButton));
        manualEntryAddressButton.click();
    }

    public void provideDeliveryAddressLine1 (String deliveryAddressLine1){
        deliveryAddressLine1Input.sendKeys(deliveryAddressLine1);
    }

    public void provideDeliveryAddressLine2 (String deliveryAddressLine2){
        deliveryAddressLine2Input.sendKeys(deliveryAddressLine2);
    }

    public void provideDeliveryCity (String deliveryCity){
        deliveryCityInput.sendKeys(deliveryCity);
    }

    public void provideDeliveryCounty (String deliveryCounty){
        deliveryCountyInput.sendKeys(deliveryCounty);
    }

    public void provideDeliveryPostcode (String deliveryPostcode){
        deliveryPostcodeInput.sendKeys(deliveryPostcode);
    }

    public void provideCreditCardNumber (String creditCardNumber){
        creditCardNumberInput.sendKeys(creditCardNumber);
    }

    public WebElement getBuyNowBtn() {
        return buyNowBtn;
    }

    public void buyNowBtnClick(){
        buyNowBtn.click();
    }

    public WebElement getInvalidErrorMessageElem() {
        return invalidErrorMessageElem;
    }

    public Boolean isInvalidErrorMessageDisplayed() {
        return invalidErrorMessageElem.isDisplayed();
    }
}

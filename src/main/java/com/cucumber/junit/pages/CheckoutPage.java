package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//input[@name='emailAddress']/following-sibling::div[@class = 'error-block']")
    private WebElement invalidEmailErrorMessageElem;

    @FindBy(xpath = "//input[@name='delivery-telephone']")
    private WebElement checkoutDeliveryPhoneField;

    @FindBy(xpath = "//button[text() = 'Buy now']")
    private WebElement buyNowBtn;

    @FindBy(xpath = "//input[@id = 'delivery-fullName']")
    private WebElement fullNameField;

    @FindBy(xpath = "//input[@id='delivery-fullName']/following-sibling::div[@class = 'error-block']")
    private WebElement invalidDeliveryFullNameErrorElem;

    @FindBy(xpath = "//select[@id = 'delivery-CountryDropdown']")
    private WebElement deliveryCountryDropdown;

    @FindBy(xpath = "//div[@id='deliveryCountryDropdown']//following-sibling::div[@class = 'error-block']")
    private WebElement invalidDeliveryCountryErrorElem;

    @FindBy(xpath = "//button[@name = 'manualEntryButton']")
    private WebElement manualEntryAddressButton;

    @FindBy(xpath = "//input[@name = 'delivery-addressLine1']")
    private WebElement deliveryAddressLine1Input;

    @FindBy(xpath = "//input[@id='delivery-addressLine1']/following-sibling::div[@class = 'error-block']")
    private WebElement invalidDeliveryAddressLine1ErrorElem;

    @FindBy(xpath = "//input[@name = 'delivery-addressLine2']")
    private WebElement deliveryAddressLine2Input;

    @FindBy(xpath = "//input[@id='delivery-addressLine2']/following-sibling::div[@class = 'error-block']")
    private WebElement invalidDeliveryAddressLine2ErrorElem;

    @FindBy(xpath = "//input[@name = 'delivery-city']")
    private WebElement deliveryCityInput;

    @FindBy(xpath = "//input[@id='delivery-city']/following-sibling::div[@class = 'error-block']")
    private WebElement invalidDeliveryCityErrorElem;

    @FindBy(xpath = "//input[@name = 'delivery-county']")
    private WebElement deliveryCountyInput;

    @FindBy(xpath = "//input[@id='delivery-county']/following-sibling::div[@class = 'error-block']")
    private WebElement invalidDeliveryCountyErrorElem;

    @FindBy(xpath = "//input[@name = 'delivery-postCode']")
    private WebElement deliveryPostcodeInput;

    @FindBy(xpath = "//input[@id='delivery-postCode']/following-sibling::div[@class = 'error-block']")
    private WebElement invalidDeliveryPostcodeErrorElem;

    @FindBy(xpath = "//input[@id = 'credit-card-number']")
    private WebElement creditCardNumberInput;

    @FindBy(xpath = "//iframe[@id = 'braintree-hosted-field-number']")
    private WebElement creditCardNumberFrame;

    @FindBy(xpath = "//input[@id = 'expiration']")
    private WebElement creditCardExpirationDateInput;

    @FindBy(xpath = "//iframe[@id = 'braintree-hosted-field-expirationDate']")
    private WebElement creditCardExpirationFrame;

    @FindBy(xpath = "//input[@id = 'cvv']")
    private WebElement creditCardCVVInput;

    @FindBy(xpath = "//iframe[@id = 'braintree-hosted-field-cvv']")
    private WebElement creditCardCVVFrame;

    @FindBy(xpath = "//div[@class = 'buynow-error-msg']")
    private WebElement paymentFieldsValidationErrorMsgElem;

    public void openCheckoutPage() {
        openSitePage(CHECKOUT_URL);
    }


    public void buyNowBtnClick() {
        elementClick(buyNowBtn);
    }

    public String invalidEmailErrorMessage() {
        return getElementText(invalidEmailErrorMessageElem);
    }

    public String invalidFullNameErrorMessage() {
        return getElementText(invalidDeliveryFullNameErrorElem);
    }

    public String invalidAddressLine1ErrorMessage() {
        return getElementText(invalidDeliveryAddressLine1ErrorElem);
    }

    public String invalidCityErrorMessage() {
        return getElementText(invalidDeliveryCityErrorElem);
    }

    public String invalidPostcodeErrorMessage() {
        return getElementText(invalidDeliveryPostcodeErrorElem);
    }

    public String paymentFieldsValidationErrorMessage() {
        return getElementText(paymentFieldsValidationErrorMsgElem);
    }

    public String getCheckoutSubtotal() {
        return getElementText(checkoutSubtotalElem);
    }

    public String getCheckoutDelivery() {
        return getElementText(checkoutDeliveryElem);
    }

    public String getCheckoutVAT() {
        return getElementText(checkoutVATElem);
    }

    public String getCheckoutTotal() {
        return getElementText(checkoutTotalElem);
    }

    public void provideCheckoutEmail(String checkoutEmail) {
        provideTextInInputField(checkoutEmailAddressField, checkoutEmail);
    }

    public void provideFullName(String fullName) {
        provideTextInInputField(fullNameField, fullName);
    }

    public void provideDeliveryAddressLine1(String deliveryAddressLine1) {
        provideTextInInputField(deliveryAddressLine1Input, deliveryAddressLine1);
    }

    public void provideDeliveryAddressLine2(String deliveryAddressLine2) {
        provideTextInInputField(deliveryAddressLine2Input, deliveryAddressLine2);
    }


    public void provideDeliveryCountry(String deliveryCountry) {

        selectOptionInSelector(deliveryCountryDropdown, deliveryCountry);
    }

    public void provideDeliveryCity(String deliveryCity) {
        provideTextInInputField(deliveryCityInput, deliveryCity);
    }

    public void provideDeliveryCounty(String deliveryCounty) {
        provideTextInInputField(deliveryCountyInput, deliveryCounty);
    }

    public void provideDeliveryPostcode(String deliveryPostcode) {
        provideTextInInputFieldWithTab(deliveryPostcodeInput, deliveryPostcode);
    }

    public Boolean isInvalidDeliveryFullNameMessageDisplayed() {
        return isElementDisplayed(invalidDeliveryFullNameErrorElem);
    }

    public Boolean isInvalidDeliveryAddressLine1MessageDisplayed() {
        return isElementDisplayed(invalidDeliveryAddressLine1ErrorElem);
    }

    public Boolean isInvalidDeliveryAddressLine2MessageDisplayed() {
        return isElementDisplayed(invalidDeliveryAddressLine2ErrorElem);
    }

    public Boolean isInvalidDeliveryCityMessageDisplayed() {
        return isElementDisplayed(invalidDeliveryCityErrorElem);
    }

    public Boolean isInvalidDeliveryCountyMessageDisplayed() {
        return isElementDisplayed(invalidDeliveryCountyErrorElem);
    }

    public Boolean isInvalidDeliveryPostcodeMessageDisplayed() {
        return isElementDisplayed(invalidDeliveryPostcodeErrorElem);
    }


    public void provideCreditCardNumber(String creditCardNumber) {
        DriverManager.getDriver().switchTo().frame(creditCardNumberFrame);
        provideTextInInputField(creditCardNumberInput, creditCardNumber);
        DriverManager.getDriver().switchTo().defaultContent();
    }

    public void provideExpirationDate(String expirationDate) {
        DriverManager.getDriver().switchTo().frame(creditCardExpirationFrame);
        provideTextInInputField(creditCardExpirationDateInput, expirationDate);
        DriverManager.getDriver().switchTo().defaultContent();
    }

    public void provideCVV(String cvv) {
        DriverManager.getDriver().switchTo().frame(creditCardCVVFrame);
        provideTextInInputField(creditCardCVVInput, cvv);
        DriverManager.getDriver().switchTo().defaultContent();
    }


    public Boolean isInvalidEmailErrorMessageDisplayed() {
        return isElementDisplayed(invalidEmailErrorMessageElem);
    }


    public void provideCheckoutPhone(String checkoutPhone) {
        provideTextInInputField(checkoutDeliveryPhoneField, checkoutPhone);
    }

    public void manualEntryAddressButtonClick() {
        elementClick(manualEntryAddressButton);
    }

    public Boolean isInvalidDeliveryCountryMessageDisplayed() {
        return isElementDisplayed(invalidDeliveryCountryErrorElem);
    }

}

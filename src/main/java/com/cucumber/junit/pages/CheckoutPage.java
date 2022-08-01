package com.cucumber.junit.pages;

import com.cucumber.junit.driver.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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
        WebDriverManager.getDriver().get(CHECKOUT_URL);
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

    public void provideCheckoutEmail(String checkoutEmail) {
        checkoutEmailAddressField.sendKeys(checkoutEmail);
    }

    public void provideCheckoutPhone(String checkoutPhone) {
        checkoutDeliveryPhoneField.sendKeys(checkoutPhone);
    }

    public void provideFullName(String fullName) {
        fullNameField.sendKeys(fullName);
    }

    public void setDeliveryCountry(String deliveryCountry) {
        waitExplicit.until(ExpectedConditions.elementToBeClickable(deliveryCountryDropdown));
        Select selectCountry = new Select(deliveryCountryDropdown);
        selectCountry.selectByVisibleText(deliveryCountry);
    }

    public void manualEntryAddressButtonClick() {
        waitExplicit.until(ExpectedConditions.elementToBeClickable(manualEntryAddressButton));
        manualEntryAddressButton.click();
    }

    public void provideDeliveryAddressLine1(String deliveryAddressLine1) {
        deliveryAddressLine1Input.sendKeys(deliveryAddressLine1);
    }

    public void provideDeliveryAddressLine2(String deliveryAddressLine2) {
        deliveryAddressLine2Input.sendKeys(deliveryAddressLine2);
    }

    public void provideDeliveryCity(String deliveryCity) {
        deliveryCityInput.sendKeys(deliveryCity);
    }

    public void provideDeliveryCounty(String deliveryCounty) {
        deliveryCountyInput.sendKeys(deliveryCounty);
    }

    public void provideDeliveryPostcode(String deliveryPostcode) {
        deliveryPostcodeInput.sendKeys(deliveryPostcode);
        deliveryPostcodeInput.sendKeys(Keys.TAB);
    }

    public WebElement getBuyNowBtn() {
        return buyNowBtn;
    }

    public void buyNowBtnClick() {
        buyNowBtn.click();
    }

    public WebElement getInvalidErrorMessageElem() {
        return invalidEmailErrorMessageElem;
    }

    public Boolean isInvalidEmailErrorMessageDisplayed() {
        return invalidEmailErrorMessageElem.isDisplayed();
    }

    public String invalidEmailErrorMessage() {
        return invalidEmailErrorMessageElem.getText();
    }

    public Boolean isInvalidDeliveryFullNameMessageDisplayed() {
        return invalidDeliveryFullNameErrorElem.isDisplayed();
    }

    public String invalidFullNameErrorMessage() {
        return invalidDeliveryFullNameErrorElem.getText();
    }

    public Boolean isInvalidDeliveryAddressLine1MessageDisplayed() {
        return invalidDeliveryAddressLine1ErrorElem.isDisplayed();
    }

    public String invalidAddressLine1ErrorMessage() {
        return invalidDeliveryAddressLine1ErrorElem.getText();
    }

    public Boolean isInvalidDeliveryAddressLine2MessageDisplayed() {
        return invalidDeliveryAddressLine2ErrorElem.isDisplayed();
    }

    public Boolean isInvalidDeliveryCityMessageDisplayed() {
        return invalidDeliveryCityErrorElem.isDisplayed();
    }

    public String invalidCityErrorMessage() {
        return invalidDeliveryCityErrorElem.getText();
    }

    public Boolean isInvalidDeliveryCountyMessageDisplayed() {
        return invalidDeliveryCountyErrorElem.isDisplayed();
    }

    public Boolean isInvalidDeliveryPostcodeMessageDisplayed() {
        return invalidDeliveryPostcodeErrorElem.isDisplayed();
    }

    public String invalidPostcodeErrorMessage() {
        return invalidDeliveryPostcodeErrorElem.getText();
    }

    public String paymentFieldsValidationErrorMessage() {
        return paymentFieldsValidationErrorMsgElem.getText();
    }

    public void provideCreditCardNumber(String creditCardNumber) {
        WebDriverManager.getDriver().switchTo().frame(creditCardNumberFrame);
        creditCardNumberInput.sendKeys(creditCardNumber);
        WebDriverManager.getDriver().switchTo().defaultContent();
    }

    public void provideExpirationDate(String expirationDate) {
        WebDriverManager.getDriver().switchTo().frame(creditCardExpirationFrame);
        creditCardExpirationDateInput.sendKeys(expirationDate);
        WebDriverManager.getDriver().switchTo().defaultContent();
    }

    public void provideCVV(String cvv) {
        WebDriverManager.getDriver().switchTo().frame(creditCardCVVFrame);
        creditCardCVVInput.sendKeys(cvv);
        WebDriverManager.getDriver().switchTo().defaultContent();
    }

    public void provideAddressLinesActions (String line1, String line2) {

        // this scroll down needed for geckodriver to avoid the MoveTargetOutOfBoundsException issue
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        js.executeScript("window.scrollBy(0,500)");

        Actions builder = new Actions(WebDriverManager.getDriver());
        Action seriesOfActions = builder
                .moveToElement(deliveryAddressLine1Input)
                .click()
                .sendKeys(line1)
                .moveToElement(deliveryAddressLine2Input)
                .click()
                .sendKeys(line2)
                .build();
        seriesOfActions.perform();
    }

    public void providePostcodeActions(String postcode) {
        Actions builder = new Actions(WebDriverManager.getDriver());
        Action seriesOfActions = builder
                .moveToElement(deliveryPostcodeInput)
                .doubleClick()
                .sendKeys(postcode)
                .sendKeys(Keys.TAB)
                .build();
        seriesOfActions.perform();
    }

    public void buyNowBtnClickJS() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        js.executeScript("window.scrollBy(0,700)");
        js.executeScript("arguments[0].click();", buyNowBtn);
    }

}

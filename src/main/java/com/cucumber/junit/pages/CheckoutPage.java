package com.cucumber.junit.pages;

import com.codeborne.selenide.Condition;
import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;
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
        DriverManager.getDriver().get(CHECKOUT_URL);
    }

    public WebElement getCheckoutSubtotalElem() {
        return checkoutSubtotalElem;
    }

    public String getCheckoutSubtotal() {
        //return checkoutSubtotalElem.getText();
        return $(checkoutSubtotalElem).getText();
    }

    public String getCheckoutDelivery() {
        //return checkoutDeliveryElem.getText();
        return $(checkoutDeliveryElem).getText();

    }

    public WebElement getCheckoutVATElem() {
        return checkoutVATElem;
    }

    public String getCheckoutVAT() {
        //return checkoutVATElem.getText();
        return $(checkoutVATElem).getText();
    }

    public WebElement getCheckoutTotalElem() {
        return checkoutTotalElem;
    }

    public String getCheckoutTotal() {
        //return checkoutTotalElem.getText();
        return $(checkoutTotalElem).getText();

    }

    public WebElement getCheckoutEmailAddressField() {
        return checkoutEmailAddressField;
    }

    public void provideCheckoutEmail(String checkoutEmail) {
        //checkoutEmailAddressField.sendKeys(checkoutEmail);
        $(checkoutEmailAddressField).setValue(checkoutEmail);
    }

    public void provideCheckoutPhone(String checkoutPhone) {
        //checkoutDeliveryPhoneField.sendKeys(checkoutPhone);
        $(checkoutDeliveryPhoneField).setValue(checkoutPhone);
    }

    public void provideFullName(String fullName) {
        //fullNameField.sendKeys(fullName);
        $(fullNameField).setValue(fullName);

    }

    public void setDeliveryCountry(String deliveryCountry) {
        //waitExplicit.until(ExpectedConditions.elementToBeClickable(deliveryCountryDropdown));
        //Select selectCountry = new Select(deliveryCountryDropdown);
        //selectCountry.selectByVisibleText(deliveryCountry);
        System.out.println(deliveryCountry);
        $(By.id("delivery-CountryDropdown"))
                .shouldBe(Condition.visible)
                .selectOptionByValue(deliveryCountry);
    }

    public void manualEntryAddressButtonClick() {
        waitExplicit.until(ExpectedConditions.elementToBeClickable(manualEntryAddressButton));
        manualEntryAddressButton.click();
    }

    public void provideDeliveryAddressLine1(String deliveryAddressLine1) {
        //deliveryAddressLine1Input.sendKeys(deliveryAddressLine1);
        $(deliveryAddressLine1Input).setValue(deliveryAddressLine1);
    }

    public void provideDeliveryAddressLine2(String deliveryAddressLine2) {
        //deliveryAddressLine2Input.sendKeys(deliveryAddressLine2);
        $(deliveryAddressLine2Input).setValue(deliveryAddressLine2);
    }

    public void provideDeliveryCity(String deliveryCity) {
        //deliveryCityInput.sendKeys(deliveryCity);
        $(deliveryCityInput).setValue(deliveryCity);
    }

    public void provideDeliveryCounty(String deliveryCounty) {
        //deliveryCountyInput.sendKeys(deliveryCounty);
        $(deliveryCountyInput).setValue(deliveryCounty);
    }

    public void provideDeliveryPostcode(String deliveryPostcode) {
        //deliveryPostcodeInput.sendKeys(deliveryPostcode);
        //deliveryPostcodeInput.sendKeys(Keys.TAB);
        $(deliveryPostcodeInput).setValue(deliveryPostcode).pressTab();
    }

   public WebElement getBuyNowBtn() {
        return buyNowBtn;
    }

    public void buyNowBtnClick() {
        //buyNowBtn.click();
        $(buyNowBtn).click();
    }

    //public WebElement getInvalidErrorMessageElem() {
        //return invalidEmailErrorMessageElem;
    //}

    public Boolean isInvalidEmailErrorMessageDisplayed() {
        return invalidEmailErrorMessageElem.isDisplayed();
    }

    public String invalidEmailErrorMessage() {
        //return invalidEmailErrorMessageElem.getText();
        return $(invalidEmailErrorMessageElem).getText();
    }

    public Boolean isInvalidDeliveryFullNameMessageDisplayed() {
        //return invalidDeliveryFullNameErrorElem.isDisplayed();
        return $(invalidDeliveryFullNameErrorElem).isDisplayed();
    }

    public Boolean isInvalidDeliveryCountryMessageDisplayed() {
        //return invalidDeliveryFullNameErrorElem.isDisplayed();
        return $(invalidDeliveryCountryErrorElem).isDisplayed();
    }

    public String invalidFullNameErrorMessage() {
        //return invalidDeliveryFullNameErrorElem.getText();
        return $(invalidDeliveryFullNameErrorElem).getText();
    }

    public Boolean isInvalidDeliveryAddressLine1MessageDisplayed() {
        //return invalidDeliveryAddressLine1ErrorElem.isDisplayed();
        return $(invalidDeliveryAddressLine1ErrorElem).isDisplayed();
    }

    public String invalidAddressLine1ErrorMessage() {
        //return invalidDeliveryAddressLine1ErrorElem.getText();
        return $(invalidDeliveryAddressLine1ErrorElem).getText();
    }

    public Boolean isInvalidDeliveryAddressLine2MessageDisplayed() {
        //return invalidDeliveryAddressLine2ErrorElem.isDisplayed();
        return $(invalidDeliveryAddressLine2ErrorElem).isDisplayed();
    }

    public Boolean isInvalidDeliveryCityMessageDisplayed() {
        //return invalidDeliveryCityErrorElem.isDisplayed();
        return $(invalidDeliveryCityErrorElem).isDisplayed();
    }

    public String invalidCityErrorMessage() {
        //return invalidDeliveryCityErrorElem.getText();
        return $(invalidDeliveryCityErrorElem).getText();
    }

    public Boolean isInvalidDeliveryCountyMessageDisplayed() {
        //return invalidDeliveryCountyErrorElem.isDisplayed();
        return $(invalidDeliveryCountyErrorElem).isDisplayed();
    }

    public Boolean isInvalidDeliveryPostcodeMessageDisplayed() {
        //return invalidDeliveryPostcodeErrorElem.isDisplayed();
        return $(invalidDeliveryPostcodeErrorElem).isDisplayed();
    }

    public String invalidPostcodeErrorMessage() {
        //return invalidDeliveryPostcodeErrorElem.getText();
        return $(invalidDeliveryPostcodeErrorElem).getText();
    }

    public String paymentFieldsValidationErrorMessage() {
        //return paymentFieldsValidationErrorMsgElem.getText();
        return $(paymentFieldsValidationErrorMsgElem).getText();
    }




    public void provideCreditCardNumber(String creditCardNumber) {
        DriverManager.getDriver().switchTo().frame(creditCardNumberFrame);
        ///creditCardNumberInput.sendKeys(creditCardNumber);
        $(creditCardNumberInput).setValue(creditCardNumber);
        DriverManager.getDriver().switchTo().defaultContent();
    }

    public void provideExpirationDate(String expirationDate) {
        DriverManager.getDriver().switchTo().frame(creditCardExpirationFrame);
        //creditCardExpirationDateInput.sendKeys(expirationDate);
        $(creditCardExpirationDateInput).setValue(expirationDate);
        DriverManager.getDriver().switchTo().defaultContent();
    }

    public void provideCVV(String cvv) {
        DriverManager.getDriver().switchTo().frame(creditCardCVVFrame);
        //creditCardCVVInput.sendKeys(cvv);
        $(creditCardCVVInput).setValue(cvv);
        DriverManager.getDriver().switchTo().defaultContent();
    }

    //public void provideAddressLinesActions (String line1, String line2) {
        //Actions builder = new Actions(DriverManager.getDriver());
        //Action seriesOfActions = builder
                //.moveToElement(deliveryAddressLine1Input).click().sendKeys(line1)
                //.moveToElement(deliveryAddressLine2Input).click().sendKeys(line2)
                //.build();
        //seriesOfActions.perform();
    //}

    //public void providePostcodeActions(String postcode) {
        //Actions builder = new Actions(DriverManager.getDriver());
        //Action seriesOfActions = builder
                //.moveToElement(deliveryPostcodeInput).doubleClick().sendKeys(postcode).sendKeys(Keys.TAB)
                //.build();
        //seriesOfActions.perform();
    //}

    //public void buyNowBtnClickJS() {
        //JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
        //js.executeScript("window.scrollBy(0,3000)");
        //js.executeScript("arguments[0].click();", buyNowBtn);
    //}

    public void provideCountryJS(String deliveryCountry) {
        $(By.xpath("//*[@id=\"delivery-CountryDropdown\"]/option[77]"));
        //*[@id="delivery-CountryDropdown"]/option[77]
        JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,3000)");
        js.executeScript("arguments[0].click();", deliveryCountryDropdown);
        js.executeScript("arguments[0].click();", $(By.xpath("//*[@id=\"delivery-CountryDropdown\"]/option[77]")));

    }

}

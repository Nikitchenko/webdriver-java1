package com.cucumber.junit.pages;

import com.cucumber.junit.driver.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.cucumber.junit.constants.Constants.PDP_URL;

public class PDPPAge extends AbstractPage {


    @FindBy(xpath = "//div[@class = 'price-info-wrap']//span[@class = 'sale-price']")
    private WebElement salePriceElem;

    @FindBy(xpath = "//a[@class = 'btn btn-primary add-to-basket' and contains(text(), 'Add to basket')]")
    private WebElement addToBasketBtn;

    @FindBy(className = "modal-content")
    private WebElement modalContent;

    @FindBy(xpath = "//h3[@class = 'modal-title']")
    private WebElement itemAddedMessageElem;

    @FindBy(xpath = "//a[contains(text(), 'Basket / Checkout')]")
    private WebElement basketCheckoutBtn;

    public void openPDPPage(String isbn) {
        WebDriverManager.getDriver().get(PDP_URL + isbn);
    }

    public WebElement getSalePriceElem() {
        return salePriceElem;
    }

    public String getSalePrice() {
        return salePriceElem.getText();
    }

    public String getItemAddedMessage() {
        return itemAddedMessageElem.getText();
    }

    public WebElement getAddToBasketBtn() {
        return addToBasketBtn;
    }

    public void addToBasketBtnClick() {
        addToBasketBtn.click();
    }

    public WebElement getBasketCheckoutBtn() {
        return basketCheckoutBtn;
    }

    public BasketPage basketCheckoutBtnClick() {
        waitExplicit.until(ExpectedConditions.elementToBeClickable(basketCheckoutBtn));
        basketCheckoutBtn.click();
        return new BasketPage();
    }

}

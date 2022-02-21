package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.cucumber.junit.constants.Constants.PDP_URL;

public class PDPPAge extends AbstractPage {


    @FindBy(xpath = "//div[@class = 'price-info-wrap']//span[@class = 'sale-price']")
    private WebElement salePriceElem;

    @FindBy(xpath = "//a[@class = 'btn btn-primary add-to-basket' and contains(text(), 'Add to basket')]")
    private WebElement addToBasketBtn;

    @FindBy(xpath = "//h3[@class = 'modal-title']")
    private WebElement itemAddedMessageElem;

    @FindBy(xpath = "//a[contains(text(), 'Basket / Checkout')]")
    private WebElement basketCheckoutBtn;

    public void openPDPPage(String isbn) {
        DriverManager.getDriver().get(PDP_URL + isbn);
    }

    public WebElement getSalePriceElem() {
        return salePriceElem;
    }

    public String getSalePrice() {
        return salePriceElem.getText();
    }

    public WebElement getItemAddedMessageElem() {
        waitExplicit.until(ExpectedConditions.textToBePresentInElement(itemAddedMessageElem, "Item"));
        return itemAddedMessageElem;
    }

    public WebElement getAddToBasketBtn() {
        return addToBasketBtn;
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

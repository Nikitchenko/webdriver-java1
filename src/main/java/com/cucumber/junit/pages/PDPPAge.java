package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.cucumber.junit.constants.Constants.PDP_URL;

public class PDPPAge extends AbstractPage {

    @FindBy(xpath = "//div[@class = 'price-info-wrap']//span[@class = 'sale-price']")
    private WebElement salePriceElem;

    @FindBy(xpath = "//a[@class = 'btn btn-primary add-to-basket' and contains(text(), 'Add to basket')]")
    private WebElement addToBasketBtn;

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

    public WebElement getAddToBasketBtn() {
        return addToBasketBtn;
    }

    public WebElement getBasketCheckoutBtn() {
        return basketCheckoutBtn;
    }

    public BasketPage basketCheckoutLinkClick() {
        basketCheckoutBtn.click();
        return new BasketPage();
    }

}

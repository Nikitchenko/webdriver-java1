package com.cucumber.junit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        openSitePage(PDP_URL + isbn);
    }

    public void addToBasketBtnClick() {
        elementClick(addToBasketBtn);
    }

    public BasketPage basketCheckoutBtnClick() {
        elementClick(basketCheckoutBtn);
        return new BasketPage();
    }

}

package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.cucumber.junit.constants.Constants.PDP_URL;

public class PDPPAge extends AbstractPage {

    //WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 15 );

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
        wait.until(ExpectedConditions.visibilityOf(basketCheckoutBtn));
        basketCheckoutBtn.click();
        return new BasketPage();
    }

}

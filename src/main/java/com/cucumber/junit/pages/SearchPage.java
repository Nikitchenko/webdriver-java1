package com.cucumber.junit.pages;

import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.cucumber.junit.constants.Constants.SEARCH_URL;

public class SearchPage extends AbstractPage {

    @FindBy(xpath = "//a[@class = 'btn btn-sm btn-primary add-to-basket']")
    private List<WebElement> bookAddToBasketLinks;

    @FindBy(xpath = "//div[@class = 'book-item']")
    private List<WebElement> bookItems;

    @FindBy(xpath = "//meta[@itemprop = 'name']")
    private List<WebElement> metaNames;

    @FindBy(xpath = "//meta[@itemprop = 'isbn']")
    private List<WebElement> metaIsbns;

    @FindBy(xpath = "//div[@class = 'item-info']/h3/a")
    private List<WebElement> first30SearchedResults;

    @FindBy(xpath = "//select[@id = 'filterPrice']")
    private WebElement filterPrice;

    @FindBy(xpath = "//select[@id = 'filterAvailability']")
    private WebElement filterAvailability;

    @FindBy(xpath = "//select[@id = 'filterLang']")
    private WebElement filterLang;

    @FindBy(xpath = "//select[@id = 'filterFormat']")
    private WebElement filterFormat;

    @FindBy(xpath = "//button[contains(text(),'Refine results')]")
    private WebElement btnRefineResults;

    @FindBy(xpath = "//a[contains(text(), 'Basket / Checkout')]")
    private WebElement basketCheckoutBtn;

    public void openSearchPage() {
        DriverManager.getDriver().get(SEARCH_URL);
    }

    public List<String> getListOfFirst30SearchedResults() {
        waitExplicit.until(ExpectedConditions.visibilityOfAllElements(first30SearchedResults));
        waitExplicit.until(ExpectedConditions.visibilityOfAllElements(bookAddToBasketLinks));
        waitExplicit.until(ExpectedConditions.elementToBeClickable(btnRefineResults));
        List<String> namesOfFirst30SearchedBooks = new ArrayList<>();

        for (WebElement element : first30SearchedResults) {
            namesOfFirst30SearchedBooks.add(element.getText());
            //System.out.println(element.getText());
        }
        return namesOfFirst30SearchedBooks;
    }

    public void selectByPrice(String price) {
        waitExplicit.until(ExpectedConditions.elementToBeClickable(filterPrice));
        Select selectPrice = new Select(filterPrice);
        selectPrice.selectByVisibleText(price);
    }

    public void selectByAvailability(String availability) {
        waitExplicit.until(ExpectedConditions.elementToBeClickable(filterAvailability));
        Select selectAvailability = new Select(filterAvailability);
        selectAvailability.selectByVisibleText(availability);
    }

    public void selectByLang(String lang) {
        waitExplicit.until(ExpectedConditions.elementToBeClickable(filterLang));
        Select selectLang = new Select(filterLang);
        selectLang.selectByVisibleText(lang);
    }

    public void selectByFormat(String format) {
        waitExplicit.until(ExpectedConditions.elementToBeClickable(filterFormat));
        Select selectFormat = new Select(filterFormat);
        selectFormat.selectByVisibleText(format);
    }

    public void refineSearchResults() {
        waitExplicit.until(ExpectedConditions.elementToBeClickable(btnRefineResults));
        btnRefineResults.click();
    }

    public void bookAddToBasket(String bookName) {

        for (int i = 0; i < metaNames.size(); i++) {

            if (metaNames.get(i).getAttribute("content").equals(bookName)) {
                //System.out.println(metaNames.get(i).getAttribute("content"));
                //System.out.println(metaIsbns.get(i).getAttribute("content"));
                bookAddToBasketLinks.get(i).click();

            }
        }
    }

    public void basketCheckout() {
        waitExplicit.until(ExpectedConditions.elementToBeClickable(basketCheckoutBtn));
        basketCheckoutBtn.click();
    }

    public BasketPage basketCheckoutBtnClick() {
        return new BasketPage();
    }

}

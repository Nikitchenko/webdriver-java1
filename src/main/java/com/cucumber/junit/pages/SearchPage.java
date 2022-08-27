package com.cucumber.junit.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.cucumber.junit.constants.Constants.SEARCH_URL;

public class SearchPage extends AbstractPage {

    private final String xFirst30SearchedResults = "//div[@class = 'item-info']/h3/a";

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

    private final String xMetaNames = "//meta[@itemprop = 'name']";

    private final String xBookAddToBasketLinks = "//a[@class = 'btn btn-sm btn-primary add-to-basket']";

    @FindBy(xpath = "//a[contains(text(), 'Basket / Checkout')]")
    private WebElement basketCheckoutBtn;


    public void openSearchPage() {
        DriverManager.getDriver().get(SEARCH_URL);
    }

    public List<String> getListOfFirst30SearchedResults() {

        List<String> namesOfFirst30SearchedBooks = new ArrayList<>();
        ElementsCollection first30SearchedResults = $$(By.xpath(xFirst30SearchedResults));

        for (WebElement element : first30SearchedResults) {
            namesOfFirst30SearchedBooks.add(element.getText());
        }
        return namesOfFirst30SearchedBooks;
    }

    public void selectByPrice(String price) {
        selectOptionInSelector(filterPrice, price);
    }

    public void selectByAvailability(String availability) {
        selectOptionInSelector(filterAvailability, availability);
    }

    public void selectByLang(String lang) {
        selectOptionInSelector(filterLang, lang);
    }

    public void selectByFormat(String format) {
        selectOptionInSelector(filterFormat, format);
    }

    public void refineSearchResults() {
        $(btnRefineResults).click();
    }

    public void bookAddToBasket(String bookName) {

        ElementsCollection metaNames = $$(By.xpath(xMetaNames));
        ElementsCollection bookAddToBasketLinks = $$(By.xpath(xBookAddToBasketLinks));

        for (int i = 0; i < metaNames.size(); i++) {

            if (metaNames.get(i).getAttribute("content").equals(bookName)) {

                bookAddToBasketLinks.get(i).click();
            }
        }
    }

    public void basketCheckoutBtnClick() {
        $(basketCheckoutBtn).shouldBe(Condition.enabled).click();
    }

    public BasketPage basketCheckout() {
        return new BasketPage();
    }

}

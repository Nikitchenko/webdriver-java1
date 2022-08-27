package com.cucumber.junit.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.cucumber.junit.constants.Constants.SEARCH_URL;

public class SearchPage extends AbstractPage {

    //@FindBy(xpath = "//a[@class = 'btn btn-sm btn-primary add-to-basket']")
    //private List<WebElement> bookAddToBasketLinks;

    //@FindBy(xpath = "//div[@class = 'book-item']")
    //private List<WebElement> bookItems;

    //@FindBy(xpath = "//meta[@itemprop = 'name']")
    //private List<WebElement> metaNames;

    //@FindBy(xpath = "//meta[@itemprop = 'isbn']")
    //private List<WebElement> metaIsbns;

    //@FindBy(xpath = "//div[@class = 'item-info']/h3/a")
    //private List<WebElement> first30SearchedResults;

    //@FindBy(xpath = "//select[@id = 'filterPrice']")
    //private WebElement filterPrice;

    //@FindBy(xpath = "//select[@id = 'filterAvailability']")
    //private WebElement filterAvailability;

    //@FindBy(xpath = "//select[@id = 'filterLang']")
    //private WebElement filterLang;

    //@FindBy(xpath = "//select[@id = 'filterFormat']")
    //private WebElement filterFormat;

    //@FindBy(xpath = "//button[contains(text(),'Refine results')]")
    //private WebElement btnRefineResults;

    //@FindBy(xpath = "//a[contains(text(), 'Basket / Checkout')]")
    //private WebElement basketCheckoutBtn;

    public void openSearchPage() {
        DriverManager.getDriver().get(SEARCH_URL);
    }

    public List<String> getListOfFirst30SearchedResults() {

        //waitExplicit.until(ExpectedConditions.visibilityOfAllElements(first30SearchedResults));
        List<String> namesOfFirst30SearchedBooks = new ArrayList<>();
        ElementsCollection first30SearchedResults = $$(By.xpath("//div[@class = 'item-info']/h3/a"));

        for (WebElement element : first30SearchedResults) {
            namesOfFirst30SearchedBooks.add(element.getText());
            //System.out.println(element.getText());
        }
        return namesOfFirst30SearchedBooks;
    }

    public void selectByPrice(String price) {
        //waitExplicit.until(ExpectedConditions.elementToBeClickable(filterPrice));
        //Select selectPrice = new Select(filterPrice);
        //selectPrice.selectByVisibleText(price);
        $(By.name("price")).selectOptionContainingText(price);

    }

    public void selectByAvailability(String availability) {
        //waitExplicit.until(ExpectedConditions.elementToBeClickable(filterAvailability));
        //Select selectAvailability = new Select(filterAvailability);
        //selectAvailability.selectByVisibleText(availability);
        $(By.name("availability")).selectOptionContainingText(availability);
    }

    public void selectByLang(String lang) {
        //waitExplicit.until(ExpectedConditions.elementToBeClickable(filterLang));
        //Select selectLang = new Select(filterLang);
        //selectLang.selectByVisibleText(lang);
        $(By.name("searchLang")).selectOptionContainingText(lang);
    }

    public void selectByFormat(String format) {
        //waitExplicit.until(ExpectedConditions.elementToBeClickable(filterFormat));
        //Select selectFormat = new Select(filterFormat);
        //selectFormat.selectByVisibleText(format);
        $(By.name("format")).selectOptionContainingText(format);
    }

    public void refineSearchResults() {
        //waitExplicit.until(ExpectedConditions.elementToBeClickable(btnRefineResults));
        //btnRefineResults.click();
        $(By.xpath("//button[contains(text(),'Refine results')]")).click();
    }

    public void bookAddToBasket(String bookName) {

        ElementsCollection metaNames = $$(By.xpath("//meta[@itemprop = 'name']"));
        ElementsCollection bookAddToBasketLinks = $$(By.xpath("//a[@class = 'btn btn-sm btn-primary add-to-basket']"));

        for (int i = 0; i < metaNames.size(); i++) {

            if (metaNames.get(i).getAttribute("content").equals(bookName)) {

                bookAddToBasketLinks.get(i).click();
            }
        }
    }

    public void basketCheckoutBtnClick() {
        //waitExplicit.until(ExpectedConditions.elementToBeClickable(basketCheckoutBtn));
        //basketCheckoutBtn.click();
        $(By.xpath("//a[contains(text(), 'Basket / Checkout')]")).shouldBe(Condition.enabled).click();
    }

    public BasketPage basketCheckout() {
        return new BasketPage();
    }

}

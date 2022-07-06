package com.cucumber.junit.pages;


import com.cucumber.junit.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


import java.util.ArrayList;
import java.util.List;

import static com.cucumber.junit.constants.Constants.SEARCH_URL;

public class SearchPage extends AbstractPage {


    public void openSearchPage(){
        DriverManager.getDriver().get(SEARCH_URL);
    }

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

    public List<String> getListOfFirst30SearchedResults(){
        List<String> namesOfFirst30SearchedBooks = new ArrayList<>();

        for (WebElement element: first30SearchedResults) {
            namesOfFirst30SearchedBooks.add(element.getText());
            //System.out.println(element.getText());
        }
        return namesOfFirst30SearchedBooks;
    }

    public void selectByPrice(String price){
        Select selectPrice = new Select(filterPrice);
        selectPrice.selectByVisibleText(price);
    }
    public void selectByAvailability(String availability){
        Select selectAvailability = new Select(filterAvailability);
        selectAvailability.selectByVisibleText(availability);
    }
    public void selectByLang(String lang){
        Select selectLang = new Select(filterLang);
        selectLang.selectByVisibleText(lang);
    }
    public void selectByFormat(String format){
        Select selectFormat= new Select(filterFormat);
        selectFormat.selectByVisibleText(format);
    }
    public void refineSearchResults(){
        btnRefineResults.click();
    }

    public void printReceived(String string){
        System.out.println(string);
    }




}

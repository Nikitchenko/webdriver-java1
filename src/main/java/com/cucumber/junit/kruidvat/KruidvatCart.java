package com.cucumber.junit.kruidvat;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KruidvatCart extends KruidvatAbstract {

    @FindBy(xpath = "//a[@class = 'product-summary__img-link']")
    private WebElement product;

    public String getSKUNumber() {
        return product.getAttribute("href");
    }

}

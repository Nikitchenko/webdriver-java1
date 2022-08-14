package com.cucumber.junit.kruidvat;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KruidvatApp extends KruidvatAbstract {


    @FindBy(xpath = "//div[@class = 'minicart__basket']")
    private WebElement miniCartBasket;


    public KruidvatCart miniCartBtnClick() {
        miniCartBasket.click();
        return new KruidvatCart();
    }


}

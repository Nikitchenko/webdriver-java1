import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWebDriverHW {

    public static final String DRIVER_PATH = "src/drivers/chromedriver.exe";
    public static final String THE_URL = "https://www.bookdepository.com/";
    public static final String THE_BOOK_NAME = "Selenium Design Patterns and Best Practices";
    // XPATHes:
    public static final String LOGO_XPATH = "//a[@href = '/' and @class = 'brand-link']/img[@src = 'https://d3ogvdx946i4sr.cloudfront.net/assets/v2.25.14/img/logo.svg']";
    public static final String SIGN_IN_REGISTER_XPATH = "//div[@class = 'user-nav']//a[@href = '/account/login/to/account']";
    public static final String ICON_BASKET_XPATH = "//div[@class = 'right-section']//a[@href = '/basket']/i[@class = 'icon-basket']";
    public static final String SEARCH_FIELD_XPATH = "//input[@name ='searchTerm']";
    public static final String NAV_MENU_XPATH = "//div[@class = 'secondary-header']/ul[@class = 'page-links mobile-nav-content']";
    public static final String BANNER_XPATH = "//a[@href = '/books-and-movies']/img[@src = 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/content/Books_on_Screen_919.jpg']";

    public static final String BASKET_PRODUCT_COUNTER_XPATH = "//div[@class = 'right-section']//a[@href = '/basket']/span[@class = 'item-count']";
    public static final String ITEM_ADDED_MESSAGE_XPATH = "//h3[@class = 'modal-title' and text() = 'Item added to your basket']";


    public static void main(String[] args) {

        //Set Chromedriver
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        // Set Page
        driver.get(THE_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        WebElement logo = locateBY(driver, LOGO_XPATH);
        System.out.println("Logo: " + logo.getAttribute("alt"));

        WebElement signInRegister = locateBY(driver, SIGN_IN_REGISTER_XPATH);
        System.out.println("Sign in/Register text: " + signInRegister.getText());

        WebElement iconBasket = locateBY(driver, ICON_BASKET_XPATH);
        System.out.println("Icon Basket class: " + iconBasket.getAttribute("class"));

        WebElement searchField = locateBY(driver, SEARCH_FIELD_XPATH);
        System.out.println("Search field placeholder: " + searchField.getAttribute("placeholder"));

        WebElement navMenu = locateBY(driver, NAV_MENU_XPATH);
        System.out.println("Navigation Menu tag: " + navMenu.getTagName());

        WebElement banner = locateBY(driver, BANNER_XPATH);
        System.out.println("Banner: " + banner.getAttribute("alt"));


        System.out.println("\nStart adding the Book to the Basket");

        WebElement basketProductCounterBefore = locateBY(driver, BASKET_PRODUCT_COUNTER_XPATH);
        System.out.println("Products in the Basket before: " + basketProductCounterBefore.getText());
        int expectedQuantityOfProductsInTheBasketBefore = 0;
        assertTrue(expectedQuantityOfProductsInTheBasketBefore == Integer.valueOf(basketProductCounterBefore.getText().trim()),
                "Not expected quantity of a products in the Basket.");

        searchTheBook(driver);

        WebElement addTheBookToBasketBtn = driver.findElement(By
                .xpath("//a[contains(text(),'Selenium Design Patterns and Best Practices')]" +
                        "/parent::h3/parent::div/following-sibling::div[@class = 'item-actions']" +
                        "//a[contains(text(),'Add to basket')]"));
        addTheBookToBasketBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_ADDED_MESSAGE_XPATH)));
        WebElement itemAddedMessage = locateBY(driver, ITEM_ADDED_MESSAGE_XPATH);
        System.out.println(itemAddedMessage.getText());

        WebElement continueShoppingBtn = driver.findElement(By.xpath("//a[ text() = 'Continue Shopping']"));
        continueShoppingBtn.click();

        WebElement basketProductCounterAfter = locateBY(driver, BASKET_PRODUCT_COUNTER_XPATH);
        System.out.println("Products in the Basket after: " + basketProductCounterAfter.getText());
        int expectedQuantityOfProductsInTheBasketAfter = 1;
        assertTrue(expectedQuantityOfProductsInTheBasketAfter == Integer.valueOf(basketProductCounterAfter.getText().trim()),
                "Not expected quantity of the products in the Basket.");

        searchTheBook(driver);

        WebElement theBook = driver.findElement(By.xpath("//a[ contains(text(), 'Selenium Design Patterns and Best Practices')]"));
        theBook.click();

        System.out.println(driver.getCurrentUrl());
        driver.quit();
    }

    public static void searchTheBook(WebDriver driver) {
        WebElement search = locateBY(driver, SEARCH_FIELD_XPATH);
        search.clear();
        search.sendKeys(THE_BOOK_NAME);
        search.sendKeys(Keys.ENTER);
    }

    public static WebElement locateBY(WebDriver driver, String xPath) {
        WebElement webElement = driver.findElement(By.xpath(xPath));
        return webElement;
    }

}

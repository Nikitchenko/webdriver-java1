import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class TestCheckout {


    public static final String DRIVER_PATH = "src/drivers/chromedriver.exe";
    public static final String THE_URL = "https://www.bookdepository.com/";

    public static final String THE_BOOK_NAME = "Selenium Design Patterns and Best Practices";
    public static final String THE_BOOK_ISBN13 = "9781783982707";
    public static final String THE_BOOK_PRICE = "27,86 €";
    // XPATHes:
    public static final String ICON_BASKET_XPATH = "//div[@class = 'right-section']//a[@href = '/basket']/i[@class = 'icon-basket']";
    public static final String SEARCH_FIELD_XPATH = "//input[@name ='searchTerm']";

    public static final String BASKET_PRODUCT_COUNTER_XPATH = "//div[@class = 'right-section']//a[@href = '/basket']/span[@class = 'item-count']";
    public static final String ITEM_ADDED_MESSAGE_XPATH = "//h3[@class = 'modal-title' and text() = 'Item added to your basket']";
    public static final String BASKET_ITEM_SUBTOTAL_XPATH = "//p[@class = 'item-total']";
    public static final String BASKET_ITEM_TOTAL_XPATH = "//dl[@class = 'total']/dd";


    private WebDriver driver;

    @BeforeClass
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
    }

    public void searchTheBook(WebDriver driver) {
        WebElement search = locateByXpath(driver, SEARCH_FIELD_XPATH);
        //search.clear();
        search.sendKeys(THE_BOOK_NAME);
        search.sendKeys(Keys.ENTER);
    }

    public WebElement locateByXpath(WebDriver driver, String xPath) {
        WebElement webElement = driver.findElement(By.xpath(xPath));
        return webElement;
    }

    @Test
    public void testCheckoutFlow() {

//        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
//        driver = new ChromeDriver();
//        driver.manage().deleteAllCookies();

        // Set Page
        driver.get(THE_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        System.out.println("\nStart adding the Book to the Basket");

        // Check1
        WebElement basketProductCounterBefore = locateByXpath(driver, BASKET_PRODUCT_COUNTER_XPATH);
        System.out.println("Products in the Basket before: " + basketProductCounterBefore.getText());
        int expectedQuantityOfProductsInTheBasketBefore = 0;
        assertTrue(expectedQuantityOfProductsInTheBasketBefore == Integer.valueOf(basketProductCounterBefore.getText().trim()),
                "Not expected quantity of a products in the Basket.");
        //end check1

        searchTheBook(driver);

        WebElement linkToTheBook = driver.findElement(By
                .xpath("//meta[@content = '9781783982707']" +
                        "/..//h3//a[contains(text(),'Selenium Design Patterns and Best Practices')]"));
        linkToTheBook.click();

        boolean isTheNeededPDP;
        isTheNeededPDP = driver.getCurrentUrl().contains(THE_BOOK_ISBN13);
        System.out.println("Is the needed pdp? " + isTheNeededPDP);

        WebElement addToBasket = driver.findElement(By
                .xpath("//a[@class = 'btn btn-primary add-to-basket' and @data-isbn = '9781783982707'  and contains(text(),'Add to basket')]"));
        addToBasket.click();

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_ADDED_MESSAGE_XPATH)));
        WebElement itemAddedMessage = locateByXpath(driver, ITEM_ADDED_MESSAGE_XPATH);
        System.out.println(itemAddedMessage.getText());

        WebElement toTheBasketBtn = driver.findElement(By.xpath("//a[@href = '/basket'  and contains(text(),'Basket / Checkout')]"));
        toTheBasketBtn.click();

        System.out.println(driver.getCurrentUrl());

        // Check2
        WebElement basketProductCounterAfter = locateByXpath(driver, BASKET_PRODUCT_COUNTER_XPATH);
        System.out.println("Products in the Basket after: " + basketProductCounterAfter.getText());
        int expectedQuantityOfProductsInTheBasketAfter = 1;
        assertTrue(expectedQuantityOfProductsInTheBasketAfter == Integer.valueOf(basketProductCounterAfter.getText().trim()),
                "Not expected quantity of the products in the Basket.");
        // end Check2


        WebElement basketSubtotal = locateByXpath(driver, BASKET_ITEM_SUBTOTAL_XPATH);
        System.out.println(basketSubtotal.getText());
        WebElement basketTotal = locateByXpath(driver, BASKET_ITEM_TOTAL_XPATH);
        System.out.println(basketTotal.getText());


        System.out.println("\nStart adding the Book to the Basket");
        int expect = 1;
        assertEquals(expect, 1, "Not equal");


    }

    @AfterClass
    public void driverQuit() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
        driver = null;
    }

}

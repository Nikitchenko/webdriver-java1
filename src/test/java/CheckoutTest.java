import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class CheckoutTest {

    public static final String DRIVER_PATH = "src/drivers/chromedriver.exe";
    public static final String THE_URL = "https://www.bookdepository.com/";
    public static final String THE_BASKET_URL = "https://www.bookdepository.com/basket";
    public static final String THE_CHECKOUT_URL = "https://www.bookdepository.com/payment/guest";

    public static final String THE_BOOK_NAME = "Selenium Design Patterns and Best Practices";
    public static final String THE_BOOK_ISBN13 = "9781783982707";
    public static final String THE_BOOK_PRICE = "27,86 €";
    public static final String THE_BOOK_VAT = "0,00 €";
    public static final String THE_EMAIL_ADDRESS = "test@user.com";

    // XPATHes:
    public static final String SEARCH_FIELD_XPATH = "//input[@name ='searchTerm']";
    public static final String BASKET_PRODUCT_COUNTER_XPATH = "//div[@class = 'right-section']//a[@href = '/basket']/span[@class = 'item-count']";
    public static final String ITEM_ADDED_MESSAGE_XPATH = "//h3[@class = 'modal-title' and text() = 'Item added to your basket']";

    public static final String BASKET_ITEM_TOTAL_XPATH = "//p[@class = 'item-total']";
    public static final String BASKET_TOTAL_XPATH = "//dl[@class = 'total']/dd";
    public static final String BASKET_CHECKOUT_BTN_XPATH = "//a[@href ='/payment/guest'  and text() = 'Checkout']";

    public static final String CHECKOUT_SUBTOTAL_XPATH = "//strong[text() = 'Sub-total']/../../dd[@class = 'text-right']";
    public static final String CHECKOUT_VAT_XPATH = "//strong[text() = 'VAT']/../../dd[@class = 'text-right total-tax']";
    public static final String CHECKOUT_TOTAL_XPATH = "//strong[text() = 'Total']/../../dd[@class = 'text-right total-price']";

    public static final String CHECKOUT_EMAIL_ADDRESS_FIELD_XPATH = "//input[@name='emailAddress']";
    public static final String CHECKOUT_EMAIL_ADDRESS_FIELD_ERROR_XPATH = "//input[@name='emailAddress']/following-sibling::div[@class = 'error-block']";
    public static final String CHECKOUT_BUY_NOW_BTN_XPATH = "//button[text() = 'Buy now']";


    static WebDriver driver;

    @BeforeAll
    static void driverSetup() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        System.out.println("Before All");
    }

     void searchTheBook(WebDriver driver) {
        WebElement search = locateByXpath(driver, SEARCH_FIELD_XPATH);
        //search.clear();
        search.sendKeys(THE_BOOK_NAME);
        search.sendKeys(Keys.ENTER);
    }

     WebElement locateByXpath(WebDriver driver, String xPath) {
        WebElement webElement = driver.findElement(By.xpath(xPath));
        return webElement;
    }

    @Test
    public void testCheckoutFlow() {

        // Set Page
        driver.get(THE_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        System.out.println("\nStart theProcess of adding the Book to the Basket");

        searchTheBook(driver);

        WebElement linkToTheBook = driver.findElement(By
                .xpath("//meta[@content = '9781783982707']" +
                        "/..//h3//a[contains(text(),'Selenium Design Patterns and Best Practices')]"));
        linkToTheBook.click();

        boolean isTheNeededPDP;
        isTheNeededPDP = driver.getCurrentUrl().contains(THE_BOOK_ISBN13);
        //Assert
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
        WebElement basketProductCounterAfterAddingTheBook = locateByXpath(driver, BASKET_PRODUCT_COUNTER_XPATH);
        System.out.println("Products in the Basket after: " + basketProductCounterAfterAddingTheBook.getText());
        int expectedQuantityOfProductsInTheBasketAfter = 1;

        // end Check2


        WebElement basketItemTotal = locateByXpath(driver, BASKET_ITEM_TOTAL_XPATH);
        System.out.println(basketItemTotal.getText());
        WebElement basketTotal = locateByXpath(driver, BASKET_TOTAL_XPATH);
        System.out.println(basketTotal.getText());

        assertAll("Check The Basket",
                () -> assertEquals(THE_BASKET_URL , driver.getCurrentUrl(),
                        "Not in the Basket."),
                () -> assertTrue(expectedQuantityOfProductsInTheBasketAfter == Integer.valueOf(basketProductCounterAfterAddingTheBook.getText().trim()),
                        "Not expected quantity of the products in the Mini Basket After the Book added."),
                () -> assertEquals(THE_BOOK_PRICE , basketItemTotal.getText(),
                        "Not correct Subtotal in the Basket."),
                () -> assertEquals(THE_BOOK_PRICE , basketTotal.getText(),
                        "Not correct Subtotal in the Basket.")
        );

        WebElement basketStartCheckoutBtn = locateByXpath(driver, BASKET_CHECKOUT_BTN_XPATH );
        basketStartCheckoutBtn.click();


        WebElement checkoutSubtotal = locateByXpath(driver, CHECKOUT_SUBTOTAL_XPATH);
        System.out.println(checkoutSubtotal.getText());
        WebElement checkoutTotal = locateByXpath(driver, CHECKOUT_TOTAL_XPATH);
        System.out.println(checkoutTotal.getText());
        WebElement checkoutVAT = locateByXpath(driver, CHECKOUT_VAT_XPATH);
        System.out.println(checkoutVAT.getText());

        WebElement emailAddress = locateByXpath(driver, CHECKOUT_EMAIL_ADDRESS_FIELD_XPATH);
        emailAddress.sendKeys(THE_EMAIL_ADDRESS);

        WebElement buyNowBtn = locateByXpath(driver, CHECKOUT_BUY_NOW_BTN_XPATH);
        buyNowBtn.click();

        WebElement emailError = locateByXpath(driver, CHECKOUT_EMAIL_ADDRESS_FIELD_ERROR_XPATH);
        System.out.println(emailError.getText());


        assertAll("Check The Checkout",
                () -> assertEquals(THE_CHECKOUT_URL , driver.getCurrentUrl(),
                        "Not in the Checkout page."),
                () -> assertEquals(THE_BOOK_PRICE , checkoutSubtotal.getText(),
                        "Not correct Subtotal in the Checkout page."),
                () -> assertEquals(THE_BOOK_VAT , checkoutVAT.getText(),
                        "Not correct VAT in the Checkout page."),
                () -> assertEquals(THE_BOOK_PRICE , checkoutTotal.getText(),
                        "Not correct Total in the Checkout page."),
                () -> assertEquals("" , emailError.getText(),
                        "Email error appeared.")
        );

    }

    @AfterAll
    static void driverQuit() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
        driver = null;
        System.out.println("After All");
    }

}

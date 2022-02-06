import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


class CheckoutTest {

    public static final String DRIVER_PATH = "src/drivers/chromedriver.exe";
    public static final String THE_URL = "https://www.bookdepository.com";
    public static final String THE_BASKET_URL = THE_URL + "/basket";
    public static final String THE_CHECKOUT_URL = THE_URL + "/payment/guest";

    public static final String THE_BOOK_NAME = "Selenium Design Patterns and Best Practices";
    public static final String THE_BOOK_ISBN13 = "9781783982707";
    public static final String THE_BOOK_PRICE = "27,86 €";
    public static final String THE_BOOK_VAT = "0,00 €";
    public static final String THE_EMAIL_ADDRESS = "test@user.com";

    // XPATHes:
    public static final String SEARCH_FIELD_XPATH = "//input[@name ='searchTerm']";
    public static final String BASKET_PRODUCT_COUNTER_XPATH = "//div[@class = 'right-section']//a[@href = '/basket']/span[@class = 'item-count']";
    public static final String LINK_TO_THE_BOOK_FROM_SEARCH_RESULT_XPATH = "//meta[@content = '9781783982707']/..//h3//a[contains(text(),'Selenium Design Patterns and Best Practices')]";

    public static final String PDP_H1_TEXT_XPATH = "//h1[text() = 'Selenium Design Patterns and Best Practices']";
    public static final String PDP_ISBN13_XPATH = "//label[text() = 'ISBN13']/following-sibling::span[text() = '9781783982707']";
    public static final String ADD_TO_BASKET_FROM_PDP_XPATH = "//a[@class = 'btn btn-primary add-to-basket' and @data-isbn = '9781783982707'  and contains(text(),'Add to basket')]";
    public static final String ITEM_ADDED_MESSAGE_XPATH = "//h3[@class = 'modal-title' and text() = 'Item added to your basket']";
    public static final String TO_BASKET_FROM_POPUP_XPATH = "//a[@href = '/basket'  and contains(text(),'Basket / Checkout')]";

    public static final String BASKET_ITEM_TOTAL_XPATH = "//p[@class = 'item-total']";
    public static final String BASKET_TOTAL_XPATH = "//dl[@class = 'total']/dd";
    public static final String BASKET_CHECKOUT_BTN_XPATH = "//a[@href ='/payment/guest'  and text() = 'Checkout']";

    public static final String CHECKOUT_SUBTOTAL_XPATH = "//strong[text() = 'Sub-total']/../../dd[@class = 'text-right']";
    public static final String CHECKOUT_VAT_XPATH = "//strong[text() = 'VAT']/../../dd[@class = 'text-right total-tax']";
    public static final String CHECKOUT_TOTAL_XPATH = "//strong[text() = 'Total']/../../dd[@class = 'text-right total-price']";

    public static final String CHECKOUT_EMAIL_ADDRESS_FIELD_XPATH = "//input[@name='emailAddress']";
    public static final String CHECKOUT_EMAIL_ADDRESS_FIELD_ERROR_XPATH = "//input[@name='emailAddress']/following-sibling::div[@class = 'error-block']";
    public static final String CHECKOUT_BUY_NOW_BTN_XPATH = "//button[text() = 'Buy now']";

    private static WebDriver driver;

    @BeforeAll
    static void driverSetup() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    static void driverQuit() {
        driver.quit();
        driver = null;
    }

    void searchTheBook() {
        WebElement search = locateByXpath(SEARCH_FIELD_XPATH);
        search.sendKeys(THE_BOOK_NAME);
        search.sendKeys(Keys.ENTER);
    }

    WebElement locateByXpath(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }

    @Test
    void testCheckoutFlow() {

        driver.get(THE_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        System.out.println("Start the process of adding the Book to the Basket.");

        searchTheBook();

        WebElement linkToTheBook = locateByXpath(LINK_TO_THE_BOOK_FROM_SEARCH_RESULT_XPATH);
        linkToTheBook.click();

        WebElement h1PDPText = locateByXpath(PDP_H1_TEXT_XPATH);
        WebElement isbn13 = locateByXpath(PDP_ISBN13_XPATH);
        WebElement addToBasketBtn = locateByXpath(ADD_TO_BASKET_FROM_PDP_XPATH);

        assertAll("Check the PDP",
                () -> assertTrue(driver.getCurrentUrl().contains(THE_BOOK_ISBN13),
                        "Not expected PDP."),
                () -> assertEquals(THE_BOOK_ISBN13, isbn13.getText(),
                        "Not expected ISBN13."),
                () -> assertEquals(THE_BOOK_NAME, h1PDPText.getText(),
                        "Not expected the Book Name on PDP."),
                () -> assertNotNull(addToBasketBtn,
                        "Add to the Basket Button not exist (probably the the Book is OOS).")
        );

        addToBasketBtn.click();


        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_ADDED_MESSAGE_XPATH)));
        WebElement itemAddedMessage = locateByXpath(ITEM_ADDED_MESSAGE_XPATH);
        System.out.println(itemAddedMessage.getText());

        WebElement toTheBasketBtn = locateByXpath(TO_BASKET_FROM_POPUP_XPATH);
        toTheBasketBtn.click();


        WebElement basketItemTotal = locateByXpath(BASKET_ITEM_TOTAL_XPATH);
        WebElement basketTotal = locateByXpath(BASKET_TOTAL_XPATH);
        WebElement basketProductCounterAfterAddingTheBook = locateByXpath(BASKET_PRODUCT_COUNTER_XPATH);
        int expectedQuantityOfProductsInTheBasketAfterAdding = 1;

        assertAll("Check the Basket",
                () -> assertEquals(THE_BASKET_URL, driver.getCurrentUrl(),
                        "Not in the Basket."),
                () -> assertEquals(expectedQuantityOfProductsInTheBasketAfterAdding,
                                Integer.parseInt(basketProductCounterAfterAddingTheBook.getText().trim()),
                        "Not expected quantity of the products in the Mini Basket After the Book added."),
                () -> assertEquals(THE_BOOK_PRICE, basketItemTotal.getText(),
                        "Not correct Subtotal in the Basket."),
                () -> assertEquals(THE_BOOK_PRICE, basketTotal.getText(),
                        "Not correct Subtotal in the Basket.")
        );

        WebElement basketStartCheckoutBtn = locateByXpath(BASKET_CHECKOUT_BTN_XPATH);
        basketStartCheckoutBtn.click();


        WebElement checkoutSubtotal = locateByXpath(CHECKOUT_SUBTOTAL_XPATH);
        WebElement checkoutTotal = locateByXpath(CHECKOUT_TOTAL_XPATH);
        WebElement checkoutVAT = locateByXpath(CHECKOUT_VAT_XPATH);

        WebElement emailAddress = locateByXpath(CHECKOUT_EMAIL_ADDRESS_FIELD_XPATH);
        emailAddress.sendKeys(THE_EMAIL_ADDRESS);
        WebElement buyNowBtn = locateByXpath(CHECKOUT_BUY_NOW_BTN_XPATH);
        buyNowBtn.click();
        WebElement emailError = locateByXpath(CHECKOUT_EMAIL_ADDRESS_FIELD_ERROR_XPATH);

        assertAll("Check the Checkout Page",
                () -> assertEquals(THE_CHECKOUT_URL, driver.getCurrentUrl(),
                        "Not in the Checkout page."),
                () -> assertEquals(THE_BOOK_PRICE, checkoutSubtotal.getText(),
                        "Not correct Subtotal in the Checkout page."),
                () -> assertEquals(THE_BOOK_VAT, checkoutVAT.getText(),
                        "Not correct VAT in the Checkout page."),
                () -> assertEquals(THE_BOOK_PRICE, checkoutTotal.getText(),
                        "Not correct Total in the Checkout page."),
                () -> assertEquals("", emailError.getText(),
                        "Email error appeared.")
        );

        System.out.println("The process is finished.");
    }

}
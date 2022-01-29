import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumHW {

    public static void main(String[] args) throws InterruptedException {

        //Set Chromedriver
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        driver.get("https://www.bookdepository.com/");
        Thread.sleep(2000);

        driver.manage().window().maximize();
        //Deleting all the cookies
        driver.manage().deleteAllCookies();

        WebElement logo = driver.findElement(By
                .xpath("//a[@href = '/' and @class = 'brand-link']/img[@src = 'https://d3ogvdx946i4sr.cloudfront.net/assets/v2.25.14/img/logo.svg' and @alt = 'Bookdepository.com']"));
        WebElement signInRegister = driver.findElement(By.xpath("//a[@href = '/account/login/to/account']"));
        //signInRegister.click();
        WebElement navMenu = driver.findElement(By.xpath("//div[@class = 'secondary-header']/ul[@class = 'page-links mobile-nav-content']"));
        WebElement banner = driver.findElement(By
                .xpath("//a[@href = '/books-and-movies']/img[@alt = 'Books on Screen' and @src = 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/content/Books_on_Screen_919.jpg' ]"));

        Thread.sleep(2000);
        driver.quit();
    }
}

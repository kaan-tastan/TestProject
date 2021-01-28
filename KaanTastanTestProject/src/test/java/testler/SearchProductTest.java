package testler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchProductTest {
    public WebDriver driver;


    @Before
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kaant\\Downloads\\KaanTastanTestProject\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        //Gittigidiyor.com'a giriş yapıldı.
        String url = "https://www.gittigidiyor.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void TestSearch() throws InterruptedException {

        // Bilgisayar kelimesi arandı.
        WebElement search = driver.findElement(By.xpath(".//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input"));
        search.click();
        search.sendKeys("Bilgisayar");
        driver.findElement(By.xpath(".//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button")).click();

        //Birinci sayfanın en altına inmek için kullanıldı.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        //İkinci sayfaya geçiş yapıldı ve bir ürüne tıklandı.
        driver.findElement(By.linkText("2")).click();
        driver.findElement(By.xpath(".//*[@id=\"item-info-block-634894616\"]")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //Seçilen ürünün detayları görüntülendi ve sepete eklendi.
        String jsquery = "document.querySelector('#add-to-basket').getBoundingClientRect().top";
        js.executeScript("window.scrollBy(0," + jsquery + ")");

        // Ürün ilk fiyatı alındı.
        WebElement price = driver.findElement(By.xpath(".//*[@id=\"sp-price-highPrice\"]"));
        String priceText = price.getText();
        System.out.println("İlk fiyat: " + priceText);

        WebElement basketBtn = driver.findElement(By.id("add-to-basket"));
        basketBtn.click();
        Thread.sleep(2000);

        //Sepeti görüntüleme butonuna tıklandı.
        driver.findElement(By.xpath(".//*[@id=\"header_wrapper\"]/div[4]/div[3]/a/div[1]")).click();
        Thread.sleep(2000);

        //Sepet fiyatı alındı.
        WebElement priceBasket = driver.findElement(By.xpath(".//*[@id=\"cart-price-container\"]/div[3]/p"));
        String priceText2 = priceBasket.getText();
        System.out.println("Sepet fiyatı: " + priceText2);

        //Fiyat karşılaştırılması yapıldı.
        if (priceText.equals(priceText2)) {
            System.out.println("Ürün fiyatı ile sepet fiyatı birbirine eşittir.");
        }
        //Sepetteki ürün sayısı bir arttırıldı.
        driver.findElement(By.cssSelector(".plus")).click();
        Thread.sleep(2000);

        //Sepetteki ürünler silindi.
        driver.findElement(By.cssSelector(".row > .btn-delete > .hidden-m")).click();
        Thread.sleep(2000);

        System.out.println("Test başarılı bir şekilde çalıştı.");


    }

    @After
    public void quitDriver() {
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit();
    }

}
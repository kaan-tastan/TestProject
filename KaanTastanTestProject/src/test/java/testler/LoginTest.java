package testler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class LoginTest {public WebDriver driver;

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
    public void TestHome() throws InterruptedException {

        WebElement signbtn = driver.findElement(By.xpath(".//*[@id=\"main-header\"]/div[3]/div/div/div/div[3]/div/div[1]/div/div[2]"));
        signbtn.click();
        driver.findElement(By.xpath(".//*[@id=\"main-header\"]/div[3]/div/div/div[1]/div[3]/div/div[1]/div[2]/div/div/div")).click();
        ;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //Deneme amacıyla hesap oluşturuldu ve bu bilgilerle giriş yapıldı.
        WebElement mailadress = driver.findElement(By.id("L-UserNameField"));
        mailadress.click();
        mailadress.sendKeys("kaan.tastan.deneme@gmail.com");

        WebElement userPassword = driver.findElement(By.xpath(".//*[@id=\"L-PasswordField\"]"));
        userPassword.click();
        userPassword.sendKeys("Deneme2021.");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//*[@id=\"gg-login-enter\"]")).click();
        Thread.sleep(2000);
        System.out.println("Başarılı bir şekilde giriş yapıldı.");
    }

    @After
    public void quitDriver() {
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit();
    }
}

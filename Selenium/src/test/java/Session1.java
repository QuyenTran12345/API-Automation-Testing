import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Session1 {
    WebDriver driver;
    @BeforeTest
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/v4");
    }

    @Test
    public void TC_001() {
        driver.findElement( By.xpath("//input[@name='uid']")).sendKeys("mngr327791");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("yzebage");
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

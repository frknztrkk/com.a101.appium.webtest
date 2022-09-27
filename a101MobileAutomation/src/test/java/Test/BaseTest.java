package Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import static java.lang.Math.round;


public class BaseTest {
    public AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:platformVersion", "11.0");
        desiredCapabilities.setCapability("appium:deviceName", "Nexus 5 API 30");
        desiredCapabilities.setCapability("appium:automationName", "UIAutomator2");
        desiredCapabilities.setCapability("appium:udid", "emulator-5554");
        desiredCapabilities.setCapability("appium:browserName", "Chrome");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        driver.get("https://www.a101.com.tr/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

@Test
public void Test(){

    driver.pressKey(new KeyEvent(AndroidKey.BACK));
    click(By.cssSelector("#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
    click(By.cssSelector(".icon-hamburger"));
    click(By.cssSelector("a[title='GİYİM & AKSESUAR']"));
    click(By.xpath("//a[contains(text(),'Kadın İç Giyim')]"));
    click(By.xpath("//a[contains(text(),'Dizaltı Çorap')]"));
    findAll(By.cssSelector(".product-card.js-product-wrapper")).get(0).click();
    //Renk Kontrolü Yapıyoruz.
    String Color=find(By.cssSelector("div[class='selected-variant-text'] span")).getText();
    Assert.assertEquals(Color,"SİYAH","Urun Siyah Degil.");
    if (Color.contains("SİYAH")){System.out.println("Sectiginiz Urun Siyah");
    }else {System.out.println("Sectiginiz Urun Renk:"+Color);}
    click(By.cssSelector(".add-to-basket.button.green.block.with-icon.js-add-basket"));
    click(By.cssSelector(".go-to-shop"));
    click(By.cssSelector(".button.green.checkout-button.block.js-checkout-button"));
    click(By.xpath("//a[normalize-space()='ÜYE OLMADAN DEVAM ET']"));
    type(By.cssSelector("input[name='user_email']"),"youremailadrress@mail.com");
    click(By.cssSelector("button[class='button block green']"));
    click(By.xpath("(//a[@title='Yeni adres oluştur'])[1]"));
    //Adres bilgilerini giriyoruz.
    type(By.cssSelector("input[placeholder='Ev adresim, iş adresim vb.']"),"Your Home"+round(Math.random()*1000));
    type(By.cssSelector("input[name='first_name']"),"Your Name");
    type(By.cssSelector("input[name='last_name']"),"Your Lastname");
    click(By.cssSelector("input[name='phone_number']"));
    type(By.cssSelector("input[name='phone_number']"),"111111111111111111");selectOpt(By.cssSelector("select[name='city']"),"40");
    selectOpt(By.cssSelector("select[name='township']"),"470");
    selectOpt(By.cssSelector("select[name='district']"),"35910");
    type(By.cssSelector("textarea[name='line']"),"Your Home Adrress City Town District");
    click(By.cssSelector("button[class='button green js-set-country js-prevent-emoji']"));
    //    Sayfada olan ödeme alanına yönlendirildimi diye kontrol ediyoruz
    click(By.cssSelector(".button.block.green.js-proceed-button"));
   Assert.assertTrue( isDisplayed(By.cssSelector("div[class='payment-area js-payment-tab-content active'] div[class='card'] div[class='section-hero']")),"Sayfaya Yonlendirilemedi.");
   if (isDisplayed(By.cssSelector("div[class='payment-area js-payment-tab-content active'] div[class='card'] div[class='section-hero']"))){
        System.out.println("Odeme Sayfasina Yonlendirildiniz");
    }





}
    public WebElement find(By locator){
        return driver.findElement(locator);
    }
    public List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }
    public void click(By locator){
        find(locator).click();
    }
    public void type(By locator,String text){
        find(locator).sendKeys(text);
        driver.hideKeyboard();


    }
    public void selectOpt(By locator,String value){
        WebElement selectElement = driver.findElement(locator);
        Select selectObject = new Select(selectElement);
        selectObject.selectByValue(value);

    }
    public Boolean isDisplayed(By locator){
       return find(locator).isDisplayed();

    }




    @After
    public void tearDown() {
        driver.quit();

    }
}
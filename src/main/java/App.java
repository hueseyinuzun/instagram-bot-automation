import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App {
    WebDriver driver ;
    String BASE_URL = "https://www.instagram.com/";
    By acceptCookiesLocator = new By.ByXPath("//button[text()='Alle Cookies erlauben']");

    By usernameLocator = new By.ByCssSelector("input[name='username']");
    By passwordLocator = new By.ByCssSelector("input[name='password']");
    By loginButtonLocator = new By.ByCssSelector("button[type='submit']");
    By instagramLogoLocator = By.cssSelector("svg[aria-label='Instagram']");
    By infoLocator = By.className("_ac2a");

    By postImageLocator = By.className("_aagw");
    By htmlTag = By.tagName("html");
    By likeButtonLocator = new By.ByCssSelector("span[class='x1rg5ohu xp7jhwk']");
    public void acceptCookies(){
        if (driver.findElement(acceptCookiesLocator).isDisplayed()){
            driver.findElement(acceptCookiesLocator).click();
        }
    }
    public App(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        acceptCookies();
    }
    public void loginWith(String username , String password){
        waitFor(usernameLocator);
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        waitFor(instagramLogoLocator);
    }
    public void navigateToTargetProfile(String profileName){
        driver.navigate().to(BASE_URL.concat(profileName));
    }

    public void clickFirstPost(){
        waitFor(instagramLogoLocator);
        driver.findElements(postImageLocator).get(0).click();
    }
    public void likeAllPost(){
        int count = getPostCount();
        while (count > 0){
            waitFor(likeButtonLocator);
            driver.findElement(likeButtonLocator).click();
            driver.findElement(htmlTag).sendKeys(Keys.ARROW_RIGHT);
            count -- ;
        }
    }
    public int getPostCount(){
        String count = driver.findElements(infoLocator).get(0).getText();
        return Integer.parseInt(count);
    }
    private void waitFor(By locator){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void tearDown(){
        driver.quit();
    }
}
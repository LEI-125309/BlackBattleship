package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//button[contains(.,'Play vs robot') or contains(.,'robot')]")
    public WebElement playVsRobotBtn;

    @FindBy(xpath = "//button[contains(.,'Play with a friend') or contains(.,'friend')]")
    public WebElement playWithFriendBtn;

    @FindBy(css = "input[placeholder*='ickname'], input[placeholder*='ame'], input[type='text']")
    public WebElement nicknameInput;

    @FindBy(xpath = "//button[contains(translate(., 'PLAYCONTINUESTARTSUBMIT', 'playcontinuestartsubmit'), 'play') or contains(translate(., 'PLAYCONTINUESTARTSUBMIT', 'playcontinuestartsubmit'), 'continue')]")
    public WebElement confirmNicknameBtn;

    @FindBy(xpath = "//button[normalize-space()='Accept all']")
    public WebElement cookieButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void handleCookies() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement btn = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Accept all']")));

            try {
                btn.click();
            } catch (Exception e) {
                System.out.println("A forçar o clique nos cookies com JavaScript...");
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", btn);
            }

            System.out.println("Cookies aceites com sucesso.");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Nenhum popup de cookies encontrado ou já foi fechado.");
        }
    }

    public void clickPlayVsRobot() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(.,'Play vs robot') or contains(.,'robot')]")));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(playVsRobotBtn)).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Popup detetado. A forçar o clique com JavaScript...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", playVsRobotBtn);
        }
    }


    public void clickPlayWithFriend() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(.,'Play with a friend') or contains(.,'friend')]")));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(playWithFriendBtn)).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Popup detetado. A forçar o clique no botão 'Amigo' com JavaScript...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", playWithFriendBtn);
        }
    }


    public void enterNickname(String nickname) {
        wait.until(ExpectedConditions.visibilityOf(nicknameInput));
        nicknameInput.clear();
        nicknameInput.sendKeys(nickname);

        try {
            confirmNicknameBtn.click();
        } catch (Exception e) {
            nicknameInput.sendKeys(org.openqa.selenium.Keys.ENTER);
        }

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://papergames.io/en/battleship")));
    }
}
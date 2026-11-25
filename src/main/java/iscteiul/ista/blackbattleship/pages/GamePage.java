package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GamePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "canvas, [class*='board'], [id*='board'], [class*='grid']")
    public WebElement gameBoard;

    public GamePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isBoardVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(gameBoard)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGameActive() {
        try {
            By activeIndicator = By.cssSelector(".timer, .turn-indicator, .status, .clock, [class*='timer'], [class*='turn']");
            return wait.until(ExpectedConditions.presenceOfElementLocated(activeIndicator)) != null;
        } catch (Exception e) {
            return isBoardVisible();
        }
    }


    public void fireAtOpponent() {
        try {
            // Espera que o tabuleiro esteja clicável
            WebElement board = wait.until(ExpectedConditions.elementToBeClickable(gameBoard));
            Actions actions = new Actions(driver);
            // Clica num ponto do tabuleiro (ajustado para bater numa célula, não na borda)
            actions.moveToElement(board, 50, 50).click().perform();
            System.out.println("Disparo efetuado!");
        } catch (Exception e) {
            System.out.println("Falha ao disparar: " + e.getMessage());
        }
    }
}
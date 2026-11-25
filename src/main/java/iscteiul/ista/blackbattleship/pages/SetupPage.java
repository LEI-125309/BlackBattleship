package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SetupPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Procura por uma caixa de texto que contenha o link gerado (normalmente é um input readonly)
    @FindBy(css = "input[readonly], input[value*='http'], .invite-link, .share-link input")
    public WebElement inviteLinkInput;

    public SetupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getInviteLink() {
        try {
            // Tenta apanhar o link a partir de uma caixa de texto (se o site mostrar uma)
            wait.until(ExpectedConditions.visibilityOf(inviteLinkInput));
            return inviteLinkInput.getAttribute("value");
        } catch (Exception e) {
            // Se não encontrar a caixa de texto, assume que o URL atual do browser JÁ É o link
            return driver.getCurrentUrl();
        }
    }
}
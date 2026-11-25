package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.HomePage;
import iscteiul.ista.blackbattleship.pages.SetupPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.*;

public class US02_PlayWithFriendTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://papergames.io/en/battleship");
    }

    @Test
    @DisplayName("US02 - Gerar link de convite para amigo")
    void deveGerarLinkDeConvite() {
        HomePage home = new HomePage(driver);

        home.handleCookies();
        home.clickPlayWithFriend();
        home.enterNickname("TestBot_Friend");

        SetupPage setup = new SetupPage(driver);
        String linkDeConvite = setup.getInviteLink();

        assertNotNull(linkDeConvite, "O link de convite nao deve ser nulo.");
        assertFalse(linkDeConvite.isEmpty(), "O link de convite nao deve estar vazio.");
        assertTrue(linkDeConvite.startsWith("http"), "O link deve comecar por http.");
        assertTrue(linkDeConvite.contains("papergames.io"), "O link deve pertencer ao dominio do jogo.");

        System.out.println("Link de convite gerado com sucesso: " + linkDeConvite);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.HomePage;
import iscteiul.ista.blackbattleship.pages.GamePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.*;

public class US01_PlayVsRobotTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // Configura o GeckoDriver (o driver do Firefox) automaticamente
        io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();

        // Inicia o browser Firefox
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.get("https://papergames.io/en/battleship");
    }

    @Test
    @DisplayName("US01 - Iniciar partida contra o robot com sucesso")
    void deveIniciarPartidaContraRobot() {
        HomePage home = new HomePage(driver);

        // 1. Tratar dos cookies
        home.handleCookies();

        // 2. Clicar no robot
        home.clickPlayVsRobot();

        // 3. Meter o nickname
        home.enterNickname("TestBot_01");

        // 4. Verificar se o jogo abriu
        GamePage game = new GamePage(driver);
        assertTrue(game.isBoardVisible(), "O tabuleiro deve estar visível após entrar no jogo");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}
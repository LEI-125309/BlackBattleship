package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.HomePage;
import iscteiul.ista.blackbattleship.pages.GamePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste de aceitação para a User Story US03:
 * "Como jogador, ao iniciar a partida, quero que os meus 5 navios
 * sejam posicionados no tabuleiro 10x10, para que o jogo possa começar."
 */
public class US03_FleetPlacementTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://papergames.io/en/battleship");
    }

    @Test
    @DisplayName("US03 - Frota posicionada e jogo pronto a começar")
    void devePosicionarFrotaEIniciarJogo() {
        HomePage home = new HomePage(driver);
        GamePage game = new GamePage(driver);

        // 1. Aceitar cookies
        home.handleCookies();

        // 2. Iniciar partida contra o robot (para não depender de outro jogador)
        home.clickPlayVsRobot();

        // 3. Inserir nickname
        home.enterNickname("TestBot_Fleet");

        // 4. Verificar se o tabuleiro 10x10 está visível
        assertTrue(game.isBoardVisible(), "O tabuleiro 10x10 deve estar visível apos o inicio.");

        // 5. Verificar se a frota foi posicionada e o jogo já arrancou (estado ativo)
        assertTrue(game.isGameActive(), "O jogo deve estar ativo, indicando que a frota foi posicionada.");

        System.out.println("US03: Frota posicionada e jogo iniciado com sucesso!");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
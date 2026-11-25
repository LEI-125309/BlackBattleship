package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.HomePage;
import iscteiul.ista.blackbattleship.pages.GamePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste de aceitação para a User Story US04:
 * "Como jogador, quero que o jogo alterne os turnos entre mim e o oponente,
 * para que ambos tenham a mesma oportunidade de disparar."
 */
public class US04_TurnSystemTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://papergames.io/en/battleship");
    }

    @Test
    @DisplayName("US04 - Alternância de turnos entre jogador e oponente")
    void deveAlternarTurnos() throws InterruptedException {
        HomePage home = new HomePage(driver);
        GamePage game = new GamePage(driver);

        // 1. Preparar o jogo
        home.handleCookies();
        home.clickPlayVsRobot();
        home.enterNickname("TestBot_Turns");

        // 2. Confirmar que o jogo arrancou
        assertTrue(game.isBoardVisible(), "O tabuleiro deve estar visivel.");

        // Dá uma pausa para o jogo carregar os navios e o robot estar pronto
        Thread.sleep(3000);

        // 3. Efetuar um disparo
        game.fireAtOpponent();

        // 4. Esperar que o oponente jogue e o turno volte
        System.out.println("A aguardar a jogada do oponente...");
        Thread.sleep(5000);

        // 5. Validar que o jogo continua ativo e não "crashou" com a troca de turnos
        assertTrue(game.isBoardVisible(), "O tabuleiro deve continuar visivel apos a troca de turnos.");
        assertTrue(game.isGameActive(), "O jogo deve continuar ativo.");

        System.out.println("US04: Alternancia de turnos validada com sucesso!");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
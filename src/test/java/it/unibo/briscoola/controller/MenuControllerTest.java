package it.unibo.briscoola.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.briscoola.controller.api.GameController;
import it.unibo.briscoola.controller.api.MenuController;
import it.unibo.briscoola.controller.impl.MenuControllerImpl;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.view.api.View;
import it.unibo.briscoola.view.api.popup.Popups;

/**
 * Test class created to check the correct funcitioning of {@link MenuControllerImpl}.
 * 
 * @author Andrea Reggiani
 */
class MenuControllerTest {

    private MenuController testMenuController;
    private View testView;

    @BeforeEach
    final void init() {

        this.testView = new View() {

            @Override 
            public void setMenuController(final MenuController menuControllerTest) { }

            @Override
            public void setGameController(final GameController gameControllerTest) { }

            @Override
            public void start() { }

            @Override
            public void initGame() { }

            @Override
            public void updateHand(final int playerIdTest, final List<Card> handCardsTest) { }

            @Override
            public void updatePile(final int cardsCountTest, final boolean playerTest) { }

            @Override
            public void displayMessage(final String messageTest) { }

            @Override
            public void updateBriscola(final String seedTest, final String valueTest) { }

            @Override
            public void quit() { }

            @Override
            public void updateTable(final String playerSeedTest, final String playerValuetest, 
                                        final String cpuSeedTest, final String cpuValueTest) { }

            @Override
            public void triggerPopup(final Popups type, final String message) { }
        };
        this.testMenuController = new MenuControllerImpl(null, this.testView);
    }

    @Test
    void testStartGameWithInvalidPlayers() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.testMenuController.startGame(1, Difficulty.EASY);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            this.testMenuController.startGame(3, Difficulty.MEDIUM);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            this.testMenuController.startGame(4, Difficulty.HARD);
        });
    }

    @Test
    void testStartGameWithNullDifficulty() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.testMenuController.startGame(2, null);
        });
    }
}

package it.unibo.briscoola.view.impl;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.view.impl.menu.DifficultySelectionPanel;
import it.unibo.briscoola.view.impl.menu.MainMenu;
import it.unibo.briscoola.view.impl.menu.PlayerSelectionsPanel;
import java.awt.event.ActionListener;
import java.util.function.BiConsumer;

/**
 * Screen is the component that manages the configuration before starting the game.
 * Uses a {@link CardLayout} to change between the main menu, the player count selection,
 * and the difficulty selection panels.
 */
public final class StartScreen extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final String MAIN_MENU = "MAIN";
    private static final String PLAYER_SELECTION = "SELECTION";
    private static final String DIFFICULTY_SELECTION = "DIFFICULTY";

    private static final int DEFAULT_PLAYERS = 2;
    private static final int BG_R = 30;
    private static final int BG_G = 100;
    private static final int BG_B = 72;

    private final CardLayout cardLayout;
    private int temporarySelectedPlayers = DEFAULT_PLAYERS;

    /**
     * creates a new StartScreen.
     * 
     * @param onSetupComplete callback triggered when both players count and difficulty are choosen
     * @param onQuit callback triggered when the user wants to exit the application
     */
    public StartScreen(final BiConsumer<Integer, Difficulty> onSetupComplete, final ActionListener onQuit) {
        this.cardLayout = new CardLayout();
        this.initScreenLayout(onSetupComplete, onQuit);

    }

    private void initScreenLayout(final BiConsumer<Integer, Difficulty> onSetupComplete, final ActionListener onQuit) {
        this.setLayout(this.cardLayout);
        this.setBackground(new Color(BG_R, BG_G, BG_B));

        final JPanel mainMenu = new MainMenu(
            e -> cardLayout.show(this, PLAYER_SELECTION), 
            onQuit
        );

        final JPanel playerSelection = new PlayerSelectionsPanel(
            num -> {
                this.temporarySelectedPlayers = num; 
                cardLayout.show(this, DIFFICULTY_SELECTION); 

            }, 
            e -> cardLayout.show(this, MAIN_MENU)
        );

        final JPanel difficultySelection = new DifficultySelectionPanel(
            diff -> onSetupComplete.accept(this.temporarySelectedPlayers, diff),
            e -> cardLayout.show(this, PLAYER_SELECTION)
        );

        this.add(mainMenu, MAIN_MENU);
        this.add(playerSelection, PLAYER_SELECTION);
        this.add(difficultySelection, DIFFICULTY_SELECTION);
    }
}

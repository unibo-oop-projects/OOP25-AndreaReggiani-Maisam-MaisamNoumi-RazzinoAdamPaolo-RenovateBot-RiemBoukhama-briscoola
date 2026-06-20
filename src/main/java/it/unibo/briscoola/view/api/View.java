package it.unibo.briscoola.view.api;

import java.util.List;

import it.unibo.briscoola.controller.api.GameController;
import it.unibo.briscoola.controller.api.MenuController;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.view.api.popup.Popups;

/**
 * Interface View for the graphic setting.
 * 
 * @author Andrea Reggiani
 */
public interface View {

    /**
     * Set the menuController fopr handling initial configuration.
     * 
     * @param menuController istance 
     */
    void setMenuController(MenuController menuController);

    /**
     * Set the gameController for handling the round mechanics and turns.
     * 
     * @param gameController istance 
     */
    void setGameController(GameController gameController);

    /**
     * Shows the inital screen.
     */
    void start();

    /**
     * Initialize the game table.
     */
    void initGame();

    /**
     * Updates the card in the player's hand after the draw.
     * 
     * @param playerID the ID of the player
     * @param handCards the list of the cards in the player hand
     */
    void updateHand(int playerID, List<Card> handCards);

    /**
     * Updates the card count in a player's deck.
     * 
     * @param cardsCount the number of the cards in the player Pile
     * @param player the player
     */
    void updatePile(int cardsCount, boolean player);

    /**
     * Shows amessage for the end of the game or the turn.
     *
     * @param type type of message
     * @param message error message
     */
    void displayMessage(Popups type, String message);

    /**
     * Returns an unmodifiable list of the player hand component views.
     * 
     * @return the hand component views
     */
    List<CardView> getPlayerHandCards();

    /**
     * Updates the graphical representation of the card.
     * 
     * @param seed the seed of the card
     * @param value the value of the card
     */
    void updateBriscola(String seed, String value);

    /**
     * Close the app.
     */
    void quit();

    /**
     * Update the table after the player chose the card.
     * 
     * @param playerSeed the seed of the card played by the player
     * @param playerValue the value of the card played by the player
     * @param cpuSeed the seed of the card played by the CPU
     * @param cpuValue the value of the card played by the CPU
     * @param deckSize the current number of cards in the deck
     */
    void updateTable(String playerSeed, String playerValue, String cpuSeed, String cpuValue, final int deckSize);

}

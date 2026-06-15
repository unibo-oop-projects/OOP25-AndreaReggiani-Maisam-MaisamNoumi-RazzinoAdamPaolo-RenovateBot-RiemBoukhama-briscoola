package it.unibo.briscoola.controller.api;

public interface GameController {
    
    /**
     * Starts the game.
     */
    void startGame();

    /**
     * Handles the current turn
     */
    void manageTurn();

    /**
     * Handles the card selected by the human player.
     * The index corresponds to the card's position in the player's hand.
     * @param selectedIndex the position of the selected card in the player's hand
     */
    void handlesHumanCardSelection(int selectedIndex);
}

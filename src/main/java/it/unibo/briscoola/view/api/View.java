package it.unibo.briscoola.view.api;

public interface View {
    
    /**
     * Shows the inital screen
     */
    void start();

    /**
     * Initialize the game table
     */
    void initGame();

    /**
     * Updates the players hand
     */
    void updateHand();

    /**
     * Updates the card count in a player's deck
     * @param cardsCount
     * @param player
     */
    void updatePile(int cardsCount, boolean player);

    /**
     * Shows amessage for the end of the game or the turn 
     * @param message
     */
    void displayMessage(String message);

    /*
     * Close the app
     */
    void quit();
}

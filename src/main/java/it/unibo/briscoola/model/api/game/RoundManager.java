package it.unibo.briscoola.model.api.game;

import it.unibo.briscoola.model.api.player.Player;

public interface RoundManager {

    /**
     * Sends a request to the given player and puts the card on the table.
     *
     * @param player the player who is being asked to play a card
     */
    void requestCard(Player player);

    /**
     * Determines the winner based on the cards on the table.
     *
     * @return the winning {@link Player}
     */
    Player determineWinner();
}

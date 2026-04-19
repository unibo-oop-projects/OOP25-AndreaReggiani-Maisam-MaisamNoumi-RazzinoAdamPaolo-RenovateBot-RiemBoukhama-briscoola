package it.unibo.briscoola.model.api.game;

import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.RoundPlay;
import it.unibo.briscoola.model.impl.game.RoundWinner;

import java.util.Map;

public interface RoundManager {

    /**
     * Rotates between the players in game.
     *
     * @return true if the round completed successfully, false otherwise
     *
     */
    Boolean nextPlayerSwitch();

    /**
     * Sends a request to the given player and puts the card on the table.
     *
     * @param player the player who is being asked to play a card
     */
    void requestCard(Player player);

    /**
     * Determines the winner based on the cards on the table.
     *
     * @return a {@link RoundPlay} with the winning player and the amount of points won
     *
     */
    RoundWinner determineWinner();
}

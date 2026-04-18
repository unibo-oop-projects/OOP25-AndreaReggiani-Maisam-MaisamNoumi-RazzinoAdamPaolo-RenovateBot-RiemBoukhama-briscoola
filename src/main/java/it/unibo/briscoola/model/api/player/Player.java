package it.unibo.briscoola.model.api.player;

import java.util.List;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.RoundState;

public interface Player {

    /**
     * Plays a card from players hand
     * @return played card
     */
    Card playCard(RoundState state);

    /**
     * Plays the card at the given index from the human player's hand
     * @param index is the position of the card in hand (0,1 or 2)
     * @return the played card
     */
    Card playCard(int index);

    /**
     * Picks a card from the deck
     * @param card card picked up from the deck
     */
    void receiveCard(Card card);

    /**
     * @return list of cards in player's hand
     */
    List<Card> getHand();

    /**
     * Adds to the player's total points at the end of each round
     * @param points points to add
     */
    void addPoints(int points);

    /**
     * @return player's identifier
     */
    int getId();

    /**
     * @return player's current points
     */
    int getPoints();
}
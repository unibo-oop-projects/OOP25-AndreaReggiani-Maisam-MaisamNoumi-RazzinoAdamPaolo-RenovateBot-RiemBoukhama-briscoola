package it.unibo.briscoola.model.api.player;

import java.util.List;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;

/**
 * Interface representing a player in the Briscola game.
 * 
 * @author Riem Boukhama
 */
public interface Player {

    /**
     * Plays a card from player's hand based on the current round state.
     * 
     * @param state the current state of the round
     * @return played card
     */
    Card playCard(RoundStateImpl state);

    /**
     * Plays the card at the given index from the human player's hand.
     * 
     * @param index is the position of the card in hand (0,1 or 2)
     * @return the played card
     */
    Card playCard(int index);

    /**
     * Picks a card from the deck.
     * 
     * @param card card picked up from the deck
     */
    void receiveCard(Card card);

    /**
     * Removes the specified card from the hand.
     *
     * @param card to remove from the hand
     */
    void removeCard(Card card);

    /**
     * @return list of cards in player's hand.
     */
    List<Card> getHand();

    /**
     * Adds a taken card to the player's pile.
     * 
     * @param card the card taken in the round
     */
    void addtoPile(Card card);

    /**
     * @return the pile 
     */
    List<Card> getPile();

    /**
     * Empties the player's pile.
     */
    void clearPile();

    /**
     * @return player's identifier
     */
    int getId();

    /**
     * @return player's current points
     */
    int getPoints();

    /**
     * @return A copy of the instanced {@link Player}
     */
    Player copy();
}

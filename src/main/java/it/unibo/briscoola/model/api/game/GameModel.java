package it.unibo.briscoola.model.api.game;

import java.util.List;
import java.util.Optional;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;

public interface GameModel {

    /**
     * Starts the match, giving 3 cards to each player.
     */
    void startMatch();
    
    /**
     * @return the Card defined as Briscola, used to determine the dominant seed.
     */
    Optional<Card> getBriscolaSeed();

     /**
      * Checks if the game is over.
      * The game ends when all players have used all their cards
      * and their hands are empty.
      * @return true if the game is over, false otherwise.
      */
    boolean isGameOver();

    /**
     * Picks the first card from the deck as the Briscola,
     * and puts it at the bottom of the deck.
     */
    void assignBriscola();

    /**
     * Deals 3 cards to each player, alternating between them.
     */
    void dealInitialCards();

    /**
     * After a trick, players draw cards following the given order.
     * If the deck becomes empty, remaining players will not draw.
     * 
     * @param orderedPlayers refers to the order of players
     */
    void drawAfterTrick(List<Player> orderedPlayers);

    /**
     * Plays the card at the given index from the given player's hand.
     * @param player refers to the player who is playing the card
     * @param index is the position of the card in hand
     * @return the played card
     */
    Card playCard(Player player, int index);
}

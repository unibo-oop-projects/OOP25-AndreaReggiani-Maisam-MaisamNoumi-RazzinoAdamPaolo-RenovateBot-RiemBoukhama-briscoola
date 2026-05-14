package it.unibo.briscoola.model.api.game;

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
     * After a trick, the winner draws first, then the loser. 
     * If the deck is empty, nobody will draw.
     * @param winner the player who won the trick
     * @param loser the player who lost the trick
     */
    void drawAfterTrick(Player winner, Player loser);

    /**
     * Plays the card at the given index (from the human player's hand)
     * @param index is the position of the card in hand (0, 1 or 2)
     * @return the played card
     */
    Card playCard(int index);
}

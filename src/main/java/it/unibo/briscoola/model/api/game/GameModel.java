package it.unibo.briscoola.model.api.game;

import java.util.List;
import java.util.Optional;

import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;
import it.unibo.briscoola.model.impl.game.RoundWinner;

/**
 * Interface, representing the model of the Briscola game, that manages the game state, the deck, the players and the rounds.
 */
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
     * 
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
     * 
     * @param player refers to the player who is playing the card
     * @param index is the position of the card in hand
     * @return the played card
     */
    Card playCard(Player player, int index);

    /**
     * Method to get the player in charge of the turn.
     * 
     * @return the {@link Player} in charge of the turn
     */
    Player getCurrentPlayer();

    /**
     * Returns the list of players currently participating in the game.
     *
     * @return an immutable list containing all {@link Player}s in the game.
     */
    List<Player> getPlayers();

    /**
     * Boolean indicating if the round is completed or not.
     *
     * @return true if the round is completed, false otherwise.
     */
    boolean isRoundOver();

    /**
     * Rotates the {@link Player}s {@link List} so that the startingPlayer plays first.
     *
     * @param startingPlayer {@link Player} winner of last round
     */
    void computeNextTurnOrder(Player startingPlayer);

    /**
     * Handles the closure of a round and the decision of a winner.
     *
     * @return the {@link RoundWinner} of the latest round
     */
    RoundWinner endRound();

    /**
     * Plays the {@link Card} chosen by the {@link Player}.
     *
     * @param player that chose the card
     * @param card chosen by the player
     */
    void makeMove(Player player, Card card);

    /**
     * Returns the state of the table in an exact moment.
     *
     * @return the current {@link RoundStateImpl}
     */
    RoundStateImpl getCurrentRoundState();

    /**
     * Returns the {@link Difficulty} of the gamemodel.
     *
     * @return the {@link Difficulty} of the game
     */
    Difficulty getDifficulty();
}

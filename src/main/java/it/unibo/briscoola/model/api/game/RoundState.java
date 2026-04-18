package it.unibo.briscoola.model.api.game;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;

import java.util.Map;

public interface RoundState {

    /**
     * Retrieves the Cards on the table in an exact moment.
     *
     * @return the {@link Card}s on the table
     */
    Map<Player, Card> getPlayedCards();

    /**
     * Retrieves the briscola seed of the game.
     *
     * @return the {@link CardSeed} that represent the briscola
     */
    public CardSeed getBriscola();
}

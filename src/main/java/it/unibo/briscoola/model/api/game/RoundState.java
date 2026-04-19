package it.unibo.briscoola.model.api.game;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.RoundPlay;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoundState {

    /**
     * Retrieves the Cards on the table in an exact moment and the player associated.
     *
     * @return the {@link Card}s on the table
     */
    List<RoundPlay> playedCards();

    /**
     * Retrieves the briscola seed of the game.
     *
     * @return the {@link CardSeed} that represent the briscola
     */
    public CardSeed briscola();

    /**
     * Retrieves the seed of the first card played in the round.
     *
     * @return the {@link Optional} of the first card played, empty if it is the first player
     */
    Optional<CardSeed> leadSeed();
}

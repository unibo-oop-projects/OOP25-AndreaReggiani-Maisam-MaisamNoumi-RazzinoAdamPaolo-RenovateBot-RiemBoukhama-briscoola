package it.unibo.briscoola.model.api.player;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.RoundState;

import java.util.List;

/**
 * Interface to handle the different CPU difficulties strategies.
 */
@FunctionalInterface
public interface PlayStrategy {

    /**
     * Returns the card to be played by the CPU.
     *
     * @param hand cards in hand of the CPU
     * @return the card to be played
     */
    int cardIndex(List<Card> hand, RoundState state);
}

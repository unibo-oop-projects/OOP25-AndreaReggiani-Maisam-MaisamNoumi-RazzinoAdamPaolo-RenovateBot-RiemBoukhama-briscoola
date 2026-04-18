package it.unibo.briscoola.model.impl.player.cpu.strategies;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.RoundState;
import it.unibo.briscoola.model.api.player.PlayStrategy;

import java.util.List;

/**
 * Strategy of the CPU for choosing the card to be played in Hard Difficulty
 */
public class HardStrategy implements PlayStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public int cardIndex(final List<Card> hand, RoundState state) {
        return 0;
    }
}

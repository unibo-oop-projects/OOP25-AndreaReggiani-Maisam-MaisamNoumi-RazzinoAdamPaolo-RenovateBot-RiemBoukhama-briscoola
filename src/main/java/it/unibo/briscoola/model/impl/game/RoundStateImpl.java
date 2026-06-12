package it.unibo.briscoola.model.impl.game;

import it.unibo.briscoola.model.api.attributes.CardSeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record RoundStateImpl(List<RoundPlay> playedCards, CardSeed briscola, Optional<CardSeed> leadSeed) {

    /**
     * Compact constructor to defend against external mutation.
     */
    public RoundStateImpl {
        playedCards = List.copyOf(playedCards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoundPlay> playedCards() {
        return new ArrayList<>(playedCards);
    }
}

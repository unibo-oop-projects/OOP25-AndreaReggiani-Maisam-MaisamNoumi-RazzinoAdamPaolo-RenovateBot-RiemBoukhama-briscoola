package it.unibo.briscoola.model.impl.game;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.RoundState;
import it.unibo.briscoola.model.api.player.Player;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record RoundStateImpl(List<RoundPlay> playedCards, CardSeed briscola, Optional<CardSeed> leadSeed) implements RoundState {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoundPlay>  playedCards() {
        return List.copyOf(this.playedCards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardSeed briscola() {
        return this.briscola;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<CardSeed> leadSeed(){
        return this.leadSeed;
    }
}

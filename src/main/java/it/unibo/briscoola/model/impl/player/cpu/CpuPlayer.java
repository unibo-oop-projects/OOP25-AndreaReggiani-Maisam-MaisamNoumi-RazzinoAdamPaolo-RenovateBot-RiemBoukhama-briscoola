package it.unibo.briscoola.model.impl.player.cpu;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.PlayStrategy;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;
import it.unibo.briscoola.model.impl.player.PlayerImpl;

/**
 * Unextendable class that represent a singular CpuPlayer.
 * Extends {@link PlayerImpl}
 *
 * @author Adam Paolo Razzino
 */
public final class CpuPlayer extends PlayerImpl {

    private final PlayStrategy strategy;

    /**
     * Constructor of a CPU player with its id and the strategy to follow.
     *
     * @param id id of the player
     * @param strategy Strategy to adapt based on the {@link StrategyFactory}
     */
    public CpuPlayer(final int id, final PlayStrategy strategy) {
        super(id);
        this.strategy = strategy;
    }

    /**
     * Constructor that creates a new {@link CpuPlayer} based
     * on the parameter cpu player.
     *
     * @param cpu {@link CpuPlayer} to copy
     */
    public CpuPlayer(final CpuPlayer cpu) {
        super(cpu.getId(), cpu.getPoints(), cpu.getHand(), cpu.getPile());
        this.strategy = cpu.strategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CpuPlayer copy() {
        return new CpuPlayer(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card playCard(final RoundStateImpl state) {
        final int index = strategy.cardIndex(this.getHand(), state);
        return this.getHand().get(index);
    }

}

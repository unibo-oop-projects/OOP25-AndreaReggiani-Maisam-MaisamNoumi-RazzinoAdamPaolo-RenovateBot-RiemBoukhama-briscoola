package it.unibo.briscoola.model.impl.game;

import java.util.ArrayList;
import java.util.List;

import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.deck.Deck;
import it.unibo.briscoola.model.api.game.GameBuilder;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.player.PlayStrategy;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.deck.DeckImpl;
import it.unibo.briscoola.model.impl.player.PlayerImpl;
import it.unibo.briscoola.model.impl.player.cpu.CpuPlayer;
import it.unibo.briscoola.model.impl.player.cpu.StrategyFactory;

/**
 * Builder class that allows a simple and fast creation of a {@link GameModel}
 * through a builder design.
 *
 * @author Adam Paolo Razzino
 */
public class GameBuilderImpl implements GameBuilder {

    private final List<Player> players = new ArrayList<>();
    private PlayStrategy playStrategy;
    private int id;

    /**
     * Basic constructor in which it instantiates the human
     * {@link Player} with the id equals to 0.
     */
    public GameBuilderImpl() {
        players.add(new PlayerImpl(id++));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameBuilderImpl setDifficulty(final Difficulty difficulty) {
        this.playStrategy = StrategyFactory.create(difficulty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameBuilderImpl addPlayer() {
        this.players.add(new CpuPlayer(id++, playStrategy));
        return this;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GameModel build() {
        final Deck<Card> deck = new DeckImpl();
        return new GameModelImpl(players, deck);
    }
}

package it.unibo.briscoola.model.impl.game;

import java.util.List;
import java.util.Optional;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.deck.Deck;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.game.RoundManager;
import it.unibo.briscoola.model.api.player.Player;

public class GameModelImpl implements GameModel{

    private final Deck<Card> deck;
    private final List<Player> players;
    private Card briscolaCard;
    private final RoundManager roundManager;

    public GameModelImpl(final List<Player> players, final Deck<Card> deck, final RoundManager roundManager) {
        this.players = players;
        this.deck = deck;
        this.roundManager = roundManager;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void startMatch() {
        this.assignBriscola();
        this.dealInitialCards();
        this.roundManager.startRound(this.players);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Optional<Card> getBriscolaSeed() {
        return Optional.ofNullable(this.briscolaCard);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void assignBriscola() {
        this.briscolaCard= this.deck.getBriscolaSeed().orElseThrow();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void dealInitialCards() {
        for(int i = 0; i < 3; i++){
            for(final Player p : players){
                p.receiveCard(deck.draw().orElseThrow());
            }
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void drawAfterTrick(final List<Player> orderedPlayers){
        for(final Player player: orderedPlayers){
            final Optional<Card> card = this.deck.draw();
            if(card.isPresent()){
                player.receiveCard(card.get());
            }
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Card playCard(final Player player, final int index) {
        return player.playCard(index);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        for (final Player p : this.players) {
            if (!p.getHand().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

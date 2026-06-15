package it.unibo.briscoola.model.impl.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import it.unibo.briscoola.model.api.attributes.CardSeed;
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

    public GameModelImpl(final List<Player> players, final Deck<Card> deck) {
        this.players = new ArrayList<>(players);
        this.deck = deck;
        this.init();
        this.roundManager = new RoundManagerImpl(briscolaCard.getCardSeed());
    }

    /**
     * Initializes the table state.
     */
    private void init(){
        this.assignBriscola();
        this.dealInitialCards();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void startMatch() {

        this.roundManager.startRound(this.players);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Optional<CardSeed> getBriscolaSeed() {
        return Optional.ofNullable(this.briscolaCard.getCardSeed());
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
            card.ifPresent(player::receiveCard);
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

    /**
     *{@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer(){
        return this.roundManager.getCurrentPlayer();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean isRoundOver(){
        return this.roundManager.isRoundOver();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void computeNextTurnOrder(Player startingPlayer){
        int index = this.players.indexOf(startingPlayer);
        Collections.rotate(this.players, index*(-1));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public RoundWinner endRound(){
        RoundWinner winner = this.roundManager.determineWinner();
        // winner.player().addPoints(winner.points());
        computeNextTurnOrder(winner.player());
        this.drawAfterTrick(this.players);
        if(!this.isGameOver()){
            this.roundManager.startRound(this.players);
        }
        return winner;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void makeMove(Player player, Card card){
        player.getHand().remove(card);
        this.roundManager.playTurn(player, card);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public RoundStateImpl getCurrentRoundState(){
        return this.roundManager.getRoundState();
    }
}

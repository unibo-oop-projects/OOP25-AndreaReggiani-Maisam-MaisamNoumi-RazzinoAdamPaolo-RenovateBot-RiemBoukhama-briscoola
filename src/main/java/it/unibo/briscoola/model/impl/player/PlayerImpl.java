package it.unibo.briscoola.model.impl.player;

import java.util.ArrayList;
import java.util.List;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;

public class PlayerImpl implements Player {

    private final int id;
    private final List<Card> hand;
    private final List<Card> pile;
    private int points;

    public PlayerImpl(int id) {
        this.id = id;
        this.points = 0;
        this.hand = new ArrayList<>();
        this.pile = new ArrayList<>();
    }

    public PlayerImpl(int id, int points, List<Card> hand, List<Card> pile){
        this.id = id;
        this.points = points;
        this.hand = new ArrayList<>(hand);
        this.pile = new ArrayList<>(pile);
    }
    

    @Override
    public Card playCard(RoundStateImpl state) {
        return this.hand.removeFirst();
    }

    @Override
    public Card playCard(int index){
        return this.hand.remove(index);
    }

    @Override
    public void receiveCard(Card card) {
        this.hand.add(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCard(Card card) {
        this.hand.remove(card);
    }

    @Override
    public List<Card> getHand() {
        return List.copyOf(this.hand);
    }

    
    @Override
    public void addtoPile(Card card) {
        this.pile.add(card);
        this.points = this.points + card.getCardPoints();
    }


    @Override
    public List<Card> getPile() {
        return this.pile;
    }


    @Override
    public void clearPile() {
        this.pile.clear();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
     public int getPoints(){
        return this.points;
    }

    @Override
    public PlayerImpl copy(){
        return new PlayerImpl(this.id, this.points, this.hand, this.pile);
    }

}

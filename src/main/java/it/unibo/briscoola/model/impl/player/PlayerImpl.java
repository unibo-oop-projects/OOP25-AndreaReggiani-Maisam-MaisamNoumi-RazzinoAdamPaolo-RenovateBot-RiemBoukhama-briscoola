package it.unibo.briscoola.model.impl.player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.RoundState;
import it.unibo.briscoola.model.api.player.Player;

public class PlayerImpl implements Player {

    private final int id;
    protected final List<Card> hand;
    private int points;

    public PlayerImpl(int id) {
        this.id = id;
        this.points = 0;
        this.hand = new ArrayList<>();
    }
    

    @Override
    public Card playCard(RoundState state, Consumer<Card> callback) {
        return this.hand.remove(0);
    }

    @Override
    public Card playCard(int index){
        return this.hand.remove(index);
    }

    @Override
    public void receiveCard(Card card) {
        this.hand.add(card);
    }

    @Override
    public List<Card> getHand() {
        return List.copyOf(this.hand);
    }
    
    @Override
    public void addPoints(int points) {
        this.points = this.points + points;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
     public int getPoints(){
        return this.points;
    }

}

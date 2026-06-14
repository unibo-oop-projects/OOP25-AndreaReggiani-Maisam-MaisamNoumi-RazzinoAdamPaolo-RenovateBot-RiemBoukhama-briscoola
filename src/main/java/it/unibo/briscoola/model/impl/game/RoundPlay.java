package it.unibo.briscoola.model.impl.game;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;

public record RoundPlay(Player player, Card card){

    public RoundPlay(Player player, Card card) {
        this.player = player.copy();
        this.card = card;
    }

    @Override
    public Player player() {
        return this.player.copy();
    }
}

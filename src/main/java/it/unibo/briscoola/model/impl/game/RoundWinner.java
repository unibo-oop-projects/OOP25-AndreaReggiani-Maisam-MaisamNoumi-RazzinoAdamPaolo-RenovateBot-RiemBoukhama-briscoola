package it.unibo.briscoola.model.impl.game;

import it.unibo.briscoola.model.api.player.Player;

public record RoundWinner(Player player, int points) {
    public RoundWinner(Player player, int points){
        this.player = player.copy();
        this.points = points;
    }

    @Override
    public Player player() {
        return this.player.copy();
    }
}

package it.unibo.briscoola.model.api.game;


import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.impl.game.GameBuilderImpl;

public interface GameBuilder {

    /**
     * Method to set the difficulty of the game.
     *
     * @param difficulty of {@link Difficulty} type
     * @return a reference to this object
     */
    GameBuilderImpl setDifficulty(Difficulty difficulty);

    /**
     * Method to add a player to the table.
     *
     * @return a reference to this object
     */
    GameBuilderImpl addPlayer();

    /**
     * Builds the {@link GameModel} based on the current configuration
     *
     * @return a {@link GameModel} item with the selected configuration
     */
    GameModel build();
}

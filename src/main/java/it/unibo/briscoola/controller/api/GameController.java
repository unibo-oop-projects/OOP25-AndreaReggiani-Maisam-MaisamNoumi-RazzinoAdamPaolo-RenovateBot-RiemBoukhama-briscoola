package it.unibo.briscoola.controller.api;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.view.api.View;

public interface GameController {

    /**
     * Function that initiates the game
     */
    void startGame();

    /**
     * Handles a singular turn making requests to the model.
     */
    void manageTurn();

    /**
     * Handles the Human {@link Player} selected card
     *
     * @param selectedCard {@link Card} selected by the player in the {@link View}
     */
    void handleHumanCardSelection(Card selectedCard);



}

package it.unibo.briscoola.controller.impl;

import it.unibo.briscoola.controller.api.GameController;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.game.RoundManager;
import it.unibo.briscoola.model.impl.game.RoundManagerImpl;
import it.unibo.briscoola.view.api.View;
import it.unibo.briscoola.view.impl.GameViewImpl;

public class GameControllerImpl implements GameController {

    private final GameModel model;
    private final RoundManager roundModel = new RoundManagerImpl();
    private final View view;

    public GameControllerImpl(GameModel model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {

    }

    /**
     * Handles a singular turn making requests to the model.
     */
    @Override
    public void manageTurn() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleHumanCardSelection(Card selectedCard) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCardPlayed(int index) {

    }
}

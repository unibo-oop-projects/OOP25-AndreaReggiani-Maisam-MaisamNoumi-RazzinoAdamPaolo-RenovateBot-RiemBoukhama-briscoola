package it.unibo.briscoola.controller.impl;

import it.unibo.briscoola.view.api.View;
import it.unibo.briscoola.view.impl.GameViewImpl;
import it.unibo.briscoola.controller.api.GameController;
import it.unibo.briscoola.controller.api.MenuController;
import it.unibo.briscoola.model.api.attributes.CardValue;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.GameBuilderImpl;

/**
 * implementation of the MenuConroller
 * This class has the role to handle the initial setUp
 * and starts the match.
 */
public class MenuControllerImpl implements MenuController {

    private static final int MAX_PLAYERS = 2;
    private final View view;

    /**
     * Constructs a new MenuControllerImpl.
     * 
     * @param model the game model istance
     * @param view the application view
     */
    public MenuControllerImpl(final GameModel model, final View view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame(final int numPlayers, final Difficulty difficulty) {
        if (numPlayers != MAX_PLAYERS) {
            throw new IllegalArgumentException("Il gioco supporta solo modalità a 2 giocatori");
        }
        if (difficulty == null) {
            throw new IllegalArgumentException("La difficolta non puo essere nulla");
        }

        final GameBuilderImpl builder = new GameBuilderImpl();
        builder.setDifficulty(difficulty);

        for (int i = 1; i < numPlayers; i++) {
            builder.addPlayer();
        }
        final GameModel model = builder.build();

        model.startMatch();
        this.view.initGame(); 

        final Player human = model.getCurrentPlayer(); 
        this.view.updateHand(0, human.getHand());

        if (model.getBriscolaSeed().isPresent()) { 
            final String briscolaSeedStr = model.getBriscolaSeed().get().getCardSeed().name();
            final String briscolaValueStr = model.getBriscolaSeed().get().getCardValue().name();

            if (this.view instanceof GameViewImpl gameView) {
                gameView.updateBriscola(briscolaSeedStr, briscolaValueStr);
            }
        }

        final GameController gameController = new GameControllerImpl(model, this.view);
        this.view.setGameController(gameController);
        gameController.startGame();
    }
}

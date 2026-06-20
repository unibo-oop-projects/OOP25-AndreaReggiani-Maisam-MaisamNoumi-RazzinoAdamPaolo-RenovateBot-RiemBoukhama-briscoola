package it.unibo.briscoola;

import it.unibo.briscoola.controller.api.MenuController;
import it.unibo.briscoola.controller.impl.MenuControllerImpl;
import it.unibo.briscoola.view.impl.GameViewImpl;

/**
 * import it.unibo.briscoola.model.api.game.GameModel;.
 * Class that handles the launch of the game.
 */
public final class BriscOOla {

    private BriscOOla() { }

    /**
     * Launches the application.
     * 
     * @param args a string with the movie/series name.
     */
    public static void main(final String[] args) {

            /*
             * final MenuController menuController = new MenuControllerImpl(initialMode, gameView);
             */
            final MenuController menuController = new MenuControllerImpl();

            /*
             * final GameModel initialMode = null;
             */
            final GameViewImpl gameView = new GameViewImpl(null);

            gameView.setMenuController(menuController);

            gameView.start(); 

    }
}

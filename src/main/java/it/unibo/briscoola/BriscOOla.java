package it.unibo.briscoola;

import it.unibo.briscoola.view.api.View;
import it.unibo.briscoola.view.impl.GameViewImpl;

/**
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
            
             View gameView = new GameViewImpl();
             gameView.start(); 
             
    }
}

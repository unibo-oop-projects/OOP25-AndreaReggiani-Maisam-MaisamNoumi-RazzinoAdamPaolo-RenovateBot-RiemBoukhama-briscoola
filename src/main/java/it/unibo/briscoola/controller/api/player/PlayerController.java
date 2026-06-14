package it.unibo.briscoola.controller.api.player;
import it.unibo.briscoola.model.api.card.Card;

public interface PlayerController {
    
    /**
     * 
     * @param index
     */
    void handlePlayerInput(int index);

    /**
     * 
     * @param index
     * @return
     */
    Card playCard(int index);
}

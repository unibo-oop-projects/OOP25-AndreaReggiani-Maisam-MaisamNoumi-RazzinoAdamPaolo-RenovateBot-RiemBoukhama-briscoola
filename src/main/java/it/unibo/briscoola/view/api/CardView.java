package it.unibo.briscoola.view.api;

import java.awt.event.ActionListener;

/**
 * Interface representing the graphical representation of a card.
 * 
 * @author Riem Boukhama
 * @author Maisam Noumi
 */
public interface CardView {

    /**
     * Renders the card on the screen. 
     * If one of the parameters is null, it displays the back of the card.
     * 
     * @param seed the card seed
     * @param value the card value
     */
    void renderCard(String seed, String value);

    /**
     * Adds a listener for managing card clicks.
     * 
     * @param listener the action to be executed upon clicking
     */
    void addCardClickListener(ActionListener listener);
}

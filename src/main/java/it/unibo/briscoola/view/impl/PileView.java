package it.unibo.briscoola.view.impl;

import javax.swing.*;
import java.awt.*;

/**
 * represents the view of the deck of cards taken by the player.
 */
public class PileView extends JPanel {

    /**
     * card counting graphic label. --> Initialized to "0", private for security ù
     */
    private final JLabel labelForCount = new JLabel("0");

    /**
     * builds a specific panel for displaying the cards.
     * @param ownerName The name of the deck owner : "CPU" or "Player".
     */
    public PileView(String ownerName) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(ownerName + "Pile "));
        setPreferredSize(new Dimension(150, 100));

        /**
         * representation of the deck 
         */
        JPanel cardBack = new JPanel();
        cardBack.setBackground(new Color(60, 60, 150));
        cardBack.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cardBack.add(new JLabel());

        labelForCount.setHorizontalAlignment(SwingConstants.CENTER);
        labelForCount.setFont(new Font("Arial", Font.BOLD, 20));

        add(cardBack, BorderLayout.CENTER);
        add(labelForCount, BorderLayout.SOUTH);
    }


    /**
     * updates the number of cards displayed
     * transform the entire "count" into a string and set it in the label
     * @param count hte new number of the cards won to show
     */
    public void updateCount(int count) {
        labelForCount.setText(String.valueOf(count));
    }
}
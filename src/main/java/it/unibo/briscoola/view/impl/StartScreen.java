package it.unibo.briscoola.view.impl;

import javax.swing.*;
import java.awt.*;

import it.unibo.briscoola.view.impl.menu.MainMenu;
import it.unibo.briscoola.view.impl.menu.PlayerSelectionsPanel;

import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class StartScreen extends JPanel{
    /**
     * it simply includes the two sub-panels 
     * and manages the exchange via CardLayout
     */
    private static final String MAIN_MENU = "MAIN";
    private static final String PLAYER_SELECTION = "SELECTION";

    private final CardLayout cardLayout;

    public StartScreen(final Consumer<Integer> onStart,final ActionListener onQuit){
        this.cardLayout = new CardLayout();
        this.setLayout(this.cardLayout);
        this.setBackground(new Color(30,100, 72));

        // Istanziamo i sotto-pannelli specialisti (ora visibili grazie alle import)
        final JPanel mainMenu = new MainMenu(
            e -> cardLayout.show(this, PLAYER_SELECTION), 
            onQuit
        );

        final JPanel playerSelection = new PlayerSelectionsPanel(
            onStart, 
            e -> cardLayout.show(this, MAIN_MENU)
        );

        this.add(mainMenu, MAIN_MENU);
        this.add(playerSelection, PLAYER_SELECTION);

    }
    
}

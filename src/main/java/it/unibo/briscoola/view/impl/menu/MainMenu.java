package it.unibo.briscoola.view.impl.menu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    public MainMenu(final ActionListener choosePlay, final ActionListener chooseExit){

        /**
         * separate class that deals only the 
         * 2 main buttons and the label for the TITLE.
         */

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        final GridBagConstraints gbc = new GridBagConstraints();

        /**
         * define empty space around the buttons 
         */
        gbc.insets = new Insets(15, 15, 15, 15); 
        
        /**
         * all in one central column 
         */
        gbc.gridx = 0; 
        
        final JLabel title = new JLabel("BRISCOOLA");
        title.setFont(new Font("Serif", Font.BOLD, 70));
        title.setForeground(Color.YELLOW);
        gbc.gridy = 0;
        this.add(title, gbc);

        final JButton btnPlay = new JButton("Play");
        btnPlay.setPreferredSize(new Dimension(200, 50));
        btnPlay.addActionListener(choosePlay);

        /**
         * remove the focus from btn play 
         */
        btnPlay.setFocusPainted(false);
        gbc.gridy = 1;
        add(btnPlay, gbc);



        JButton btnQuit = new JButton("Exit");
        btnQuit.setPreferredSize(new Dimension(200, 50));
        btnQuit.addActionListener(chooseExit);

        /**
         * remove the focus from btn exit 
         */
        btnQuit.setFocusPainted(false);
        gbc.gridy = 2;
        add(btnQuit, gbc);
    }
}

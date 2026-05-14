package it.unibo.briscoola.view.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartScreen extends JPanel{
    public StartScreen(ActionListener onStart, ActionListener onQuit){
        setLayout(new GridBagLayout());
        setBackground(new Color(30,100,30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); /* this define empty space around the components / buttons */
        gbc.gridx = 0; /*all in one central column */

        /**
         * to be sure.
         * "gbc.gridy" defines the row (Y) of the grid in which to place the component.
         * By increasing "gridy", components are stacked vertically. 
         * "gridy" = 0 -> "title" , "gridy" = 1 -> "Button : Play" , "gridy" = 2 -> "Button : Exit"
         */

        
        JLabel title = new JLabel("BRISCOOLA");
        title.setFont(new Font("Serif", Font.BOLD, 70));
        title.setForeground(Color.YELLOW);
        gbc.gridy = 0;
        add(title, gbc);



        JButton btnPlay = new JButton("Play");
        btnPlay.setPreferredSize(new Dimension(200, 50));
        btnPlay.addActionListener(onStart);
        gbc.gridy = 1;
        add(btnPlay, gbc);



        JButton btnQuit = new JButton("Exit");
        btnQuit.setPreferredSize(new Dimension(200, 50));
        btnQuit.addActionListener(onQuit);
        gbc.gridy = 2;
        add(btnQuit, gbc);
    }
    
}

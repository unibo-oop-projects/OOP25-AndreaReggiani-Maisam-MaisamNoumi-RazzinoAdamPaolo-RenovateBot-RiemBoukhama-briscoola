package it.unibo.briscoola.view.impl.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel inside the startup sequence that allows the user to select
 * the number of players for the match.
 * 
 * @author Andrea Reggiani
 */
public final class PlayerSelectionsPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int INSET_PADDING = 15;
    private static final int FONT_SIZE = 70;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final int TWO_PLAYERS_MODE = 2;

    private static final int GRIDX0 = 0;
    private static final int GRIDY0 = 0;
    private static final int GRIDY1 = 1;
    private static final int GRIDY2 = 2;

    /**
     * is only showing the options for the number of players.
     * letting choose the type of match: 
     * 2 players.
     * 
     * @param chosePlayer selection of the number of players
     * @param backClicked selection of the difficulty of the other players
     */
    public PlayerSelectionsPanel(final Consumer<Integer> chosePlayer, final ActionListener backClicked) {

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_PADDING, INSET_PADDING, INSET_PADDING, INSET_PADDING);
        gbc.gridx = GRIDX0;

        final JLabel title = new JLabel("Select Number of Players");
        title.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        title.setForeground(Color.WHITE);
        gbc.gridy = GRIDY0;
        this.add(title, gbc);

        final JButton btn2Players = new JButton("2 Players");
        btn2Players.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btn2Players.addActionListener(e -> chosePlayer.accept(TWO_PLAYERS_MODE)); 
        gbc.gridy = GRIDY1;
        this.add(btn2Players, gbc);

        final JButton btnQuit = new JButton("Back");
        btnQuit.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnQuit.addActionListener(backClicked);
        gbc.gridy = GRIDY2;
        this.add(btnQuit, gbc);
    }
}

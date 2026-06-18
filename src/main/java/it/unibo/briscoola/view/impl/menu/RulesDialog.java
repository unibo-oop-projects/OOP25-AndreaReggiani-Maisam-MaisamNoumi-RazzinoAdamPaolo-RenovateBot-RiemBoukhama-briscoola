package it.unibo.briscoola.view.impl.menu;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

/**
 * Component that extends {@link JDialog}.
 * It's used to show to the user the rules of the game on screen.
 */
public class RulesDialog extends JDialog {

    /**
     * Builds an element of {@link JDialog} over the parent Frame.
     * It contains the rules of the game.
     *
     * @param parentFrame {@link JFrame} owner of the {@link JDialog}
     */
    public RulesDialog(JFrame parentFrame) {
        super(parentFrame, "Rules of the Game", true);
        final int WIDTH = 550;
        final int HEIGHT = 600;
        final int BG_R = 20;
        final int BG_G = 80;
        final int BG_B = 25;
        final int BORDER_RADIUS = 20;
        final int TEXT_FONT_SIZE = 14;
        final int BUTTON_TEXT_SIZE = 16;
        final int BUTTON_BORDER_THICKNESS = 1;
        final int BUTTON_VERTICAL_BORDER = 10;
        final int BUTTON_HORIZONTAL_BORDER = 30;
        final int HGAP = 0;
        final int VGAP = 15;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(parentFrame);
        final Color greenBg = new Color(BG_R, BG_G, BG_B);
        JTextArea textArea = new JTextArea(getRulesText());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.BOLD, TEXT_FONT_SIZE));
        textArea.setBackground(greenBg);
        textArea.setForeground(Color.BLACK);
        textArea.setBorder(BorderFactory.createEmptyBorder(BORDER_RADIUS, BORDER_RADIUS, BORDER_RADIUS, BORDER_RADIUS));
        final JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        final JButton closeButton = new JButton("CLOSE");
        closeButton.setFont(new Font("Arial", Font.BOLD, BUTTON_TEXT_SIZE));
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, BUTTON_BORDER_THICKNESS),
                BorderFactory.createEmptyBorder(BUTTON_VERTICAL_BORDER,
                        BUTTON_HORIZONTAL_BORDER,
                        BUTTON_VERTICAL_BORDER,
                        BUTTON_HORIZONTAL_BORDER)
        ));
        closeButton.addActionListener(e -> dispose());
        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, HGAP, VGAP));
        buttonPanel.setBackground(greenBg);
        buttonPanel.add(closeButton);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private String getRulesText() {
        return """
                BRISCOLA GAME RULES
                
                1. SETUP
                • The player enters an optional name.
                • The player is required to choose the game Difficulty.
                • Each player is given 3 cards.
                • The Briscola card gets chosen and put on the table face up.
                
                2. CARD EXPLANATION:
                The Cards follow a power logic following the order of the sequent list
                In which there will be listed the points of each card as well
                TWO -> 0
                FOUR -> 0
                FIVE -> 0
                SIX -> 0
                SEVEN -> 0
                EIGHT/SOLDIER -> 2
                NINE/HORSE -> 3
                TEN/KING -> 4
                THREE -> 10
                ONE/ACE -> 11
                3. YOUR TURN
                Select the card to be played based on:
                
                 - Cards on the table:
                       Remember that the briscola seed rules over every other
                       If there is no briscola on the table the leadSeed rules
                
                4. CPU TURN
                The CPU elaborates what is on the table and makes its move
                based on the chosen difficulty\
                5. DETERMINE WINNER
                The game decides the winner of the round and starts a new round
                The next round is started by the winner of the latest
                This process repeats until the deck and every player's hand is empty
                6. LEADERBOARD SCORING
                The player will see his points on the leaderboard
                If high enough
                """;
    }
}

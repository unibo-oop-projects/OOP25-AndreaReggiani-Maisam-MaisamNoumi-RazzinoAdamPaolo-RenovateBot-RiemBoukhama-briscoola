package it.unibo.briscoola.view.impl.leaderboard;

import it.unibo.briscoola.controller.impl.utils.Pair;
import it.unibo.briscoola.view.api.leaderboard.Leaderboard;
import it.unibo.briscoola.model.api.player.Player;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

/**
 * The LeaderboardView is a {@link JPanel} that shows to screen
 * the list of the players with the best highscore on screen.
 * It needs a {@link List} of {@link Pair} of {@link String}
 * passed to the constructor which represent the list of players
 * paired with their high scores.
 *
 * @author Adam Paolo Razzino
 */
public final class LeaderboardView extends JPanel implements Leaderboard {

    private static final long serialVersionUID = 1L;

    private static final String FONT_FAMILY = "Serif";
    private static final Font TITLE_FONT = new Font(FONT_FAMILY, Font.BOLD, 22);
    private static final Font LEADER_FONT = new Font(FONT_FAMILY, Font.BOLD, 16);
    private static final Font REGULAR_FONT = new Font(FONT_FAMILY, Font.PLAIN, 16);

    private static final Color GOLD_BG = new Color(255, 215, 0);
    private static final Color ROW_BG = new Color(220, 220, 220);

    private static final int VERTICAL_BORDER = 15;
    private static final int HORIZONTAL_BORDER = 20;

    /**
     * Constructor of the LeaderboardView and displays the input in a
     * {@link JPanel} with a {@link GridLayout}.
     *
     * @param scoreboard the list of {@link Pair}s associating the {@link Player} name with his score
     */
    public LeaderboardView(final List<Pair<String, String>> scoreboard) {
        init();

        final JLabel titleLabel = new JLabel("LEADERBOARD", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(VERTICAL_BORDER, 0, VERTICAL_BORDER, 0));
        this.add(titleLabel);

        for (int i = 0; i < scoreboard.size(); i++) {
            final JLabel playerLabel = getJLabel(scoreboard, i);

            final JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
            rowPanel.setBackground(Color.GRAY);
            rowPanel.add(playerLabel);
            rowPanel.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));

            this.add(rowPanel);
        }
    }

    private static JLabel getJLabel(final List<Pair<String, String>> scoreboard, final int i) {
        final Pair<String, String> pair = scoreboard.get(i);
        final String text = (i + 1) + "° " + pair.x() + " - " + pair.y();

        final JLabel playerLabel = new JLabel(text, SwingConstants.CENTER);
        playerLabel.setAlignmentX(CENTER_ALIGNMENT);
        playerLabel.setOpaque(true);

        if (i == 0) {
            playerLabel.setFont(LEADER_FONT);
            playerLabel.setBackground(GOLD_BG);
            playerLabel.setForeground(Color.BLACK);
            playerLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.ORANGE, 2),
                    BorderFactory.createEmptyBorder(10, HORIZONTAL_BORDER, 10, HORIZONTAL_BORDER)
            ));
        } else {
            playerLabel.setFont(REGULAR_FONT);
            playerLabel.setBackground(ROW_BG);
            playerLabel.setForeground(Color.BLACK);
            playerLabel.setBorder(BorderFactory.createEmptyBorder(8, HORIZONTAL_BORDER, 8, HORIZONTAL_BORDER));
        }
        return playerLabel;
    }

    private void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.GRAY);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final int screenRatio = 4;
        this.setMinimumSize(new Dimension(
                toolkit.getScreenSize().width / screenRatio,
                toolkit.getScreenSize().height / screenRatio));
        this.setPreferredSize(new Dimension(
                toolkit.getScreenSize().width / screenRatio,
                toolkit.getScreenSize().height / screenRatio));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLeaderboard() {
        this.setVisible(true);
    }
}

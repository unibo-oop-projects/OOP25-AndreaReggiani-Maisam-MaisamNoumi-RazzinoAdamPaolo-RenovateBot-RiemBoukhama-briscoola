package it.unibo.briscoola.view.impl.leaderboard;

import it.unibo.briscoola.controller.impl.utils.Pair;
import it.unibo.briscoola.view.api.leaderboard.Leaderboard;
import it.unibo.briscoola.model.api.player.Player;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
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
public class LeaderboardView extends JPanel implements Leaderboard {

    /**
     * Constructor of the LeaderboardView and displays the input in a
     * {@link JPanel} with a {@link GridLayout}.
     *
     * @param scoreboard the list of {@link Pair}s associating the {@link Player} name with his score
     */
    public LeaderboardView(final List<Pair<String, String>> scoreboard) {
        final int cols = 1;
        final int rows = 0;
        this.setLayout(new GridLayout(rows, cols));
        this.setBackground(Color.GRAY);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final int screenRatio = 5;
        this.setSize(toolkit.getScreenSize().width / screenRatio, toolkit.getScreenSize().height / screenRatio);
        scoreboard.forEach(pair ->
                this.add(new JLabel(scoreboard.indexOf(pair) + "°: " + pair.x() + " - " + pair.y()), SwingConstants.CENTER)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLeaderboard() {
        this.setVisible(true);
    }
}

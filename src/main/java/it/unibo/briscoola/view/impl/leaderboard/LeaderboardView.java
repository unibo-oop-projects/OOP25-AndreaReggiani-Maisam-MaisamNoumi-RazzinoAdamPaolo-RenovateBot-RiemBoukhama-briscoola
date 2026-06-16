package it.unibo.briscoola.view.impl.leaderboard;

import it.unibo.briscoola.controller.impl.utilis.Pair;
import it.unibo.briscoola.view.api.Leaderboard;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LeaderboardView extends JPanel implements Leaderboard {

    public LeaderboardView(List<Pair<String,String>> scoreboard){
        List<Pair<String, String>> scoreboard1 = List.copyOf(scoreboard);
        this.setLayout(new GridLayout(0,1));
        this.setBackground(Color.GRAY);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int SCREEN_RATIO = 5;
        this.setSize(toolkit.getScreenSize().width/ SCREEN_RATIO, toolkit.getScreenSize().height/ SCREEN_RATIO);
        scoreboard.forEach(pair->
                this.add(new JLabel(pair.x() + " - " + pair.y()), SwingConstants.CENTER)
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

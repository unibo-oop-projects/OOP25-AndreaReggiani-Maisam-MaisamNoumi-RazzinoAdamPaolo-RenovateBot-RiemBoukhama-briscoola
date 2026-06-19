package it.unibo.briscoola.controller.impl;

import java.util.List;

import javax.swing.SwingUtilities;

import it.unibo.briscoola.controller.api.GameController;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.leaderboard.Leaderboard;
import it.unibo.briscoola.model.api.leaderboard.ScoreFileManager;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.RoundPlay;
import it.unibo.briscoola.model.impl.game.RoundWinner;
import it.unibo.briscoola.model.impl.leaderboard.LeaderboardImpl;
import it.unibo.briscoola.model.impl.leaderboard.ScoreEntryImpl;
import it.unibo.briscoola.model.impl.leaderboard.ScoreFileManagerImpl;
import it.unibo.briscoola.model.impl.player.cpu.CpuPlayer;
import it.unibo.briscoola.view.api.View;

/**
 * Implementation of the {@link GameController}.
 * This class  manages the game flow, the turn switching, 
 * and the communication between the Model and the View.
 */
public class GameControllerImpl implements GameController {

    private static final int ROUND_END_DELAY_MS = 1500;
    private static final int CPU_THINK_DELAY_MS = 800;

    private final GameModel model;
    private final View view;

    private Player humanPlayer;
    private Player cpuPlayer;

    /**
     * Creates a new GameControllerImpl associating it with the model and the view.
     *
     * @param model the logical model of the game
     * @param view  the graphical interface of the game
     */
    public GameControllerImpl(final GameModel model, final View view) {
        this.model = model;
        this.view = view;
        this.view.setGameController(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        model.startMatch();
        view.initGame();

        if (model.getCurrentPlayer().getId() == 0) {
            this.humanPlayer = model.getCurrentPlayer();
        }

        updateAllHands();
        manageTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void manageTurn() {

        if (model.isGameOver()) {
            final int humanPoints = this.humanPlayer != null ? this.humanPlayer.getPoints() : 0;
            final int cpuPoints = this.cpuPlayer != null ? this.cpuPlayer.getPoints() : 0;

            String finalMsg = "GAME OVER! ";
            if (humanPoints > cpuPoints) {
                final String leaderboardFile = "leaderboard.json";
                final ScoreFileManager manager = new ScoreFileManagerImpl(leaderboardFile);
                final Leaderboard leaderboard = new LeaderboardImpl(manager);
                leaderboard.addEntry(new ScoreEntryImpl("Player", (int) (humanPoints * this.model.getDifficulty().value)));
                leaderboard.saveScores();
                finalMsg += "You Won!";
            } else if (cpuPoints > humanPoints) {
                finalMsg += "CPU Won!";
            } else {
                finalMsg += "It's a Tie";
            }

            final String message = finalMsg + " Score -> Player: " + humanPoints + " | CPU: " + cpuPoints;

            SwingUtilities.invokeLater(() -> {
                view.displayMessage(message);
                view.start();
            });
            return;
        }

        if (model.isRoundOver()) {
            new Thread(() -> {
                try {
                    Thread.sleep(ROUND_END_DELAY_MS); 
                } catch (final InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                SwingUtilities.invokeLater(() -> {
                    final RoundWinner winner = model.endRound();
                    view.displayMessage("Round won by: " + (winner.player().getId() == 0 ? "Player" : "CPU"));

                    if (this.humanPlayer != null) {
                        view.updatePile(this.humanPlayer.getPile().size(), true);
                    }
                    if (this.cpuPlayer != null) {
                        view.updatePile(this.cpuPlayer.getPile().size(), false);
                    }

                    view.updateTable(null, null, null, null); 
                    updateAllHands();
                    manageTurn();
                });
            }).start();
            return;
        }

        final Player currentPlayer = model.getCurrentPlayer();

        if (currentPlayer.getId() == 0) {
            this.humanPlayer = currentPlayer;
        } else if (currentPlayer instanceof CpuPlayer) {
            this.cpuPlayer = currentPlayer;
        }

        if (currentPlayer instanceof CpuPlayer cpu) {
            new Thread(() -> {
                try {
                    Thread.sleep(CPU_THINK_DELAY_MS); 
                } catch (final InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                SwingUtilities.invokeLater(() -> {
                    final Card chosenCard = cpu.playCard(model.getCurrentRoundState());
                    updateTableGraphics(currentPlayer, chosenCard);
                    model.makeMove(cpu, chosenCard);
                    view.updateHand(cpu.getId(), cpu.getHand());
                    manageTurn();
                });
            }).start();
        } else {
            view.updateHand(0, currentPlayer.getHand());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handlesHumanCardSelection(final int selectedIndex) {

        if (model.isGameOver() || model.isRoundOver()) {
            return;
        }

        if (model.getCurrentPlayer().getId() != 0) {
            return; 
        }

        final Player human = model.getCurrentPlayer();
        this.humanPlayer = human;
        final Card card = human.getHand().get(selectedIndex);

        updateTableGraphics(human, card);
        model.makeMove(human, card);
        view.updateHand(0, human.getHand()); 

        manageTurn();
    }

    private void updateTableGraphics(final Player player, final Card card) {
        String pSeed = null;
        String pValue = null;
        String cSeed = null;
        String cValue = null;

        final List<RoundPlay> currentPlays = model.getCurrentRoundState().playedCards();
        for (final RoundPlay play : currentPlays) {
            if (play.player().getId() == 0) {
                pSeed = play.card().getCardSeed().name();
                pValue = play.card().getCardValue().name();
            } else {
                cSeed = play.card().getCardSeed().name();
                cValue = play.card().getCardValue().name();
            }
        }

        if (player.getId() == 0) {
            pSeed = card.getCardSeed().name();
            pValue = card.getCardValue().name();
        } else {
            cSeed = card.getCardSeed().name();
            cValue = card.getCardValue().name();
        }

        view.updateTable(pSeed, pValue, cSeed, cValue);
    }

    private void updateAllHands() {
        for (final Player p : model.getPlayers()) {
            view.updateHand(p.getId(), p.getHand());
        }
    }
}

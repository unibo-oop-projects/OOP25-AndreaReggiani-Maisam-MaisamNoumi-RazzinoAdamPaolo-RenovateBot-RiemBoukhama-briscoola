package it.unibo.briscoola.controller.impl;

import java.util.List;

import it.unibo.briscoola.controller.api.GameController;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.RoundPlay;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;
import it.unibo.briscoola.model.impl.game.RoundWinner;
import it.unibo.briscoola.model.impl.player.cpu.CpuPlayer;
import it.unibo.briscoola.view.api.View;

/**
 * Implementation of the {@link GameController}.
 * This class  manages the game flow, the turn switching, 
 * and the communication between the Model and the View.
 */
public class GameControllerImpl implements GameController {

    private final GameModel model;
    private final View view;

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
     * Starts the match by preparing the model, initializing the graphics,
     * displaying the initial hands and starting the first turn.
     */
    @Override
    public void startGame() {
        model.startMatch();
        view.initGame();
        updateAllHands();
        manageTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void manageTurn() {

        if (model.isGameOver()) {
            view.displayMessage("The game is finished.");
            return;
        }

        if (model.isRoundOver()) {
            final RoundWinner winner = model.endRound();
            final boolean isHuman = winner.player().getId() == 0;

            view.displayMessage("Round won by: " + (isHuman ? "Player" : "CPU") 
                    + " with " + winner.wonCards().size() + " cards!");
            view.updatePile(winner.wonCards().size(), isHuman);

            view.updateTable(null, null, null, null);

            updateAllHands();
            manageTurn();
            return;
        }

        final Player currentPlayer = model.getCurrentPlayer();
        view.displayMessage("It's " + (currentPlayer.getId() == 0 ? "Player" : "CPU") + "'s turn");

        if (currentPlayer instanceof CpuPlayer cpu) {
            final Card chosenCard = cpu.playCard(model.getCurrentRoundState());
            model.makeMove(cpu, chosenCard);
            refreshTable();
            view.updateHand(cpu.getId(), cpu.getHand());
            manageTurn();
        } else {
            // The GUI is waiting for a user click.
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handlesHumanCardSelection(final int selectedIndex) {
        final Player human = model.getCurrentPlayer();

        if (human instanceof CpuPlayer) {
            throw new IllegalStateException("User input received during the CPU's turn.");
        }

        final Card card = human.getHand().get(selectedIndex);
        model.makeMove(human, card);
        refreshTable();
        view.updateHand(0, human.getHand());
        manageTurn();
    }

    private void refreshTable() {
        final RoundStateImpl state = model.getCurrentRoundState();
        final List<RoundPlay> plays = state.playedCards(); 

        String hSeed = null;
        String hValue = null;
        String cSeed = null;
        String cValue = null;

        for (final RoundPlay play : plays) {
            final Player p = play.player(); 
            final Card c = play.card();

            if (p.getId() == 0) { 
                hSeed = c.getCardSeed().name();
                hValue = c.getCardValue().name();
            } else {
                cSeed = c.getCardSeed().name();
                cValue = c.getCardValue().name();
            }
        }

        view.updateTable(hSeed, hValue, cSeed, cValue);
    }

    private void updateAllHands() {
        for (final Player p : model.getPlayers()) {
            view.updateHand(p.getId(), p.getHand());
        }
    }
}

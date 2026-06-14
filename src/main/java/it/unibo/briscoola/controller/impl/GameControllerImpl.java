package it.unibo.briscoola.controller.impl;

import it.unibo.briscoola.controller.api.GameController;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.RoundWinner;
import it.unibo.briscoola.model.impl.player.cpu.CpuPlayer;
import it.unibo.briscoola.view.api.View;

public class GameControllerImpl implements GameController {

    private final GameModel model;
    private final View view;

    public GameControllerImpl(final GameModel model,final View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        model.startMatch();
        manageTurn();
    }

    /**
     * Handles a singular turn making requests to the model.
     */
    @Override
    public void manageTurn() {
        if(model.isGameOver()){
            // TODO: View shows the end of the game screen
            return;
        }
        if(model.isRoundOver()){
            RoundWinner winner = model.endRound();
            // TODO: View shows to screen the RoundWinner and the points
            manageTurn();
            return;
        }

        Player currentPlayer = model.getCurrentPlayer();
        // TODO: View gets updated with model.getRoundState() the change of player

        if(currentPlayer instanceof CpuPlayer cpu){
            Card chosenCard = cpu.playCard(model.getCurrentRoundState());
            model.makeMove(cpu, chosenCard);
            manageTurn();
        }else{
            //TODO: View handles the player's need of an input
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handlesHumanCardSelection(final int selectedIndex) {
        final Player human = model.getCurrentPlayer();
        final Card card = human.getHand().get(selectedIndex);
        model.makeMove(human, card);
        manageTurn();
    }

}

package it.unibo.briscoola.game;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.attributes.CardValue;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.game.RoundManager;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.card.StandardCardImpl;
import it.unibo.briscoola.model.impl.game.RoundManagerImpl;
import it.unibo.briscoola.model.impl.game.RoundWinner;
import it.unibo.briscoola.model.impl.player.cpu.CpuPlayer;
import it.unibo.briscoola.model.impl.player.cpu.StrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RoundManagerTest {
    private List<Player> playerList;
    private RoundManager manager;
    @BeforeEach
    void init(){
        this.playerList = new ArrayList<>();
        this.playerList.add(new CpuPlayer(0, StrategyFactory.create(Difficulty.EASY)));
        this.playerList.add(new CpuPlayer(1, StrategyFactory.create(Difficulty.EASY)));

        playerList.getFirst().receiveCard(new StandardCardImpl(CardValue.ACE,CardSeed.CUP));
        playerList.getFirst().receiveCard(new StandardCardImpl(CardValue.JACK,CardSeed.SWORD));

        playerList.getLast().receiveCard(new StandardCardImpl(CardValue.ACE,CardSeed.CUP));
        playerList.getLast().receiveCard(new StandardCardImpl(CardValue.ACE,CardSeed.CUP));


        this.manager = new RoundManagerImpl(CardSeed.SWORD);
        this.manager.startRound(List.copyOf(playerList));
    }

    /*@Test
    void roundCheck(){
        assertTrue(this.manager.nextPlayerSwitch());
    }*/

    /*@Test
    void winnerCheck(){
        playerList.forEach(ignored -> this.manager.nextPlayerSwitch());
        assertEquals(this.manager.determineWinner(), new RoundWinner(this.playerList.getFirst(), 13));
    }*/
}

package it.unibo.briscoola.player;


import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.attributes.CardValue;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.card.StandardCardImpl;
import it.unibo.briscoola.model.impl.player.cpu.CpuPlayer;
import it.unibo.briscoola.model.impl.player.cpu.StrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the behaviour of the CpuPlayer and the Strategy Factory
 */
class CpuPlayerTest {

    private Player cpu1;
    private Player cpu2;

    final void init() {

        Stream.of(cpu1, cpu2).forEach(a -> {
            if (a != null) {
                a.receiveCard(new StandardCardImpl(CardValue.HORSE, CardSeed.STAFF));
                a.receiveCard(new StandardCardImpl(CardValue.THREE, CardSeed.CUP));
            }
        });
    }

    @Test
    void TestCopy() {
        cpu1 = new CpuPlayer(1, StrategyFactory.create(Difficulty.MEDIUM));
        init();
        final Player cpuCopy = cpu1.copy();
        assertEquals(cpu1, cpuCopy);
        assertEquals(cpu1.hashCode(), cpuCopy.hashCode());
    }

}

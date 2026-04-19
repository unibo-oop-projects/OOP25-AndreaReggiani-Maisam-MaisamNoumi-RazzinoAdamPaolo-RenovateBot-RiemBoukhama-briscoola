package it.unibo.briscoola.model.impl.game;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.game.RoundManager;
import it.unibo.briscoola.model.api.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class RoundManagerImpl implements RoundManager {

    private final List<RoundPlay> table;
    private final CardSeed briscola;
    private final List<Player> playersList;
    private CardSeed leadSeed;
    private final Logger logger = LoggerFactory.getLogger(RoundManagerImpl.class);

    public RoundManagerImpl(CardSeed briscola, List<Player> playersList){
        this.playersList = playersList;
        this.table = new ArrayList<>();
        this.briscola = briscola;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public Boolean nextPlayerSwitch(){
        if(!playersList.isEmpty()){
            requestCard(playersList.getFirst());
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestCard(Player player) {
        RoundStateImpl state = new RoundStateImpl(this.table, this.briscola, Optional.ofNullable(this.leadSeed));
        player.playCard(state, card -> {
            if(table.isEmpty()){
                this.leadSeed = card.getCardSeed();
            }
            table.add(new RoundPlay(player, card));
        });
        if(playersList.getFirst()!=player){
            throw new RuntimeException();
        }
        playersList.removeFirst();
        nextPlayerSwitch();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public RoundWinner determineWinner() {
        if(this.table.isEmpty()){
            throw new IllegalStateException();
        }
        final RoundPlay winningEntry = this.table.stream()
                .max(Comparator.comparingInt(e -> e.card().getCardPower() ))
                .orElseThrow(() -> new IllegalStateException("No winner could be determined"));
        return new RoundWinner(winningEntry.player(), this.table.stream().map(a-> a.card().getCardPoints()).reduce(Integer::sum)
                .orElseThrow(()->new IllegalStateException("No possible points calculated")));
    }
}


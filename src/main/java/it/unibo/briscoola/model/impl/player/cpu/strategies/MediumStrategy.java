package it.unibo.briscoola.model.impl.player.cpu.strategies;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.PlayStrategy;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;

/**
 * Strategy of the CPU for choosing the card to be played in Medium Difficulty.
 * Points to use the best fit for the card present on the table regarding the power.
 *
 * @author Adam Paolo Razzino
 */
public class MediumStrategy implements PlayStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public int cardIndex(final List<Card> hand, final RoundStateImpl state) {
        final Optional<Card> canBeat = hand.stream()
                .filter(card -> state.playedCards().stream()
                        .allMatch(it -> card.getCardPower() > it.card().getCardPower()))
                .findFirst();
        if (canBeat.isPresent()) {
            return hand.indexOf(canBeat.get());
        }
        final Card worstCard = hand.stream()
                .min(Comparator.comparingInt(Card::getCardPower))
                .orElseThrow();

        return hand.indexOf(worstCard);
    }
}

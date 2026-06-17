package it.unibo.briscoola.model.impl.card;
import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.attributes.CardValue;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.impl.game.RoundPlay;

import java.util.Objects;

public class StandardCardImpl implements Card {

    private final CardSeed seed;
    private final CardValue value;
    
    public StandardCardImpl(final CardValue value, final CardSeed seed){
        this.value=value;
        this.seed=seed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardSeed getCardSeed() {
        return this.seed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardValue getCardValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCardPoints() {
        return this.value.getPointCard();
        /**
         * getPointCard is from enum CardValue
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCardPower() {
        return this.value.getPowerCard();
        /**
         * getPowerCard is from enum CardValue
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card that = (Card) o;
        return Objects.equals(this.seed, that.getCardSeed()) && Objects.equals(this.value, that.getCardValue());
    }
    
}

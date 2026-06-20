package it.unibo.briscoola.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.deck.Deck;
import it.unibo.briscoola.model.impl.deck.DeckImpl;

/**
 * Test to check the correct working of {@link DeckImpl}.
 * 
 * @author Andrea Reggiani
 */
class DeckImplTest {

    /*
     * a deck of Briscola contains 4 seeds x 10 values ​: 40 cards 
     */
    private static final int EXPECTED_NUM_CARDS = 40;

    private Deck<Card> testDeck;

    @BeforeEach
    final void init() {
        this.testDeck = new DeckImpl();
    }

    /*
     * Check that the new instantiated deck is full and contains all 40 cards
     */
    @Test
    void testInitialDeckSize() {
        assertEquals(EXPECTED_NUM_CARDS, this.testDeck.getCurrentSize());
    }

    /*
     * Asking for trumps must not decrease the number of cards in the deck
     */
    @Test
    void testGetBriscolaSeedDoesNotRemoveCard() {
        final Optional<Card> briscola = this.testDeck.getBriscolaSeed();
        assertTrue(briscola.isPresent());
        assertEquals(EXPECTED_NUM_CARDS, this.testDeck.getCurrentSize());
    }

    /*
     * Draw while there are cards available
     */
    @Test
    void testDrawAllCards() {
        int cardsDrawn = 0;
        while (this.testDeck.getCurrentSize() > 0) {
            final Optional<Card> drawnCard = this.testDeck.draw();
            assertTrue(drawnCard.isPresent());
            cardsDrawn++;
            assertEquals(EXPECTED_NUM_CARDS - cardsDrawn, this.testDeck.getCurrentSize());
        }

        /*
         * Check that exactly 40 cards have been drawn
         */
        assertEquals(EXPECTED_NUM_CARDS, cardsDrawn);

        /*
         * Once done, more drawing should return an empty Optional
         */
        final Optional<Card> emptyDraw = this.testDeck.draw();
        assertFalse(emptyDraw.isPresent());
    }
}

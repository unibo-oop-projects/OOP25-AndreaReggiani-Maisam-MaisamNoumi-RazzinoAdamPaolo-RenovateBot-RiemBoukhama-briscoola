package it.unibo.briscoola.model.impl.game;

import java.util.List;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.deck.Deck;
import it.unibo.briscoola.model.api.game.GameFactory;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.deck.DeckImpl;

public class GameFactoryImpl implements GameFactory {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public GameModel createStandardGame(List<Player> players) {
        Deck<Card> deck= new DeckImpl();
        CardSeed briscolaSeed = deck.getBriscolaSeed().orElseThrow().getCardSeed();

        return new GameModelImpl(players,deck);
    }

    
}

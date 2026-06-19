package it.unibo.briscoola.view.impl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import it.unibo.briscoola.controller.api.GameController;
import it.unibo.briscoola.controller.api.MenuController;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.view.api.CardView;
import it.unibo.briscoola.view.api.View;

/**
 * Implementazion of {@link  View} interface.
 * This class extends {@link JFrame} and implements {@link View}, giving
 * the visual container for the game board, the startup screen,
 * the player cards and the center match arena.
 */
public final class GameViewImpl extends JFrame implements View {

    private static final long serialVersionUID = 1L;

    private static final String MENU_ID = "MENU";
    private static final String GAME_ID = "GAME";
    private static final int NUMBER_OF_CARDS = 3;

    private static final int WINDOW_WIDTH = 1250;
    private static final int WINDOW_HEIGHT = 850;
    private static final int CARD_WIDTH = 120;
    private static final int CARD_HEIGHT = 170;
    private static final int BG_R = 20;
    private static final int BG_G = 80;
    private static final int BG_B = 25;
    private static final int BORDER_PADDING = 10;
    private static final int WEST_PADDING = 30;
    private static final int FLOW_GAP_HAND = 20;
    private static final int FLOW_GAP_DECK = 15;
    private static final int FLOW_GAP_TABLE = 30;

    private static final int FALLBACK_R = 100;
    private static final int FALLBACK_G = 149;
    private static final int FALLBACK_B = 237;

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel container = new JPanel(cardLayout);

    private final PileView playerPile = new PileView("Player");
    private final PileView cpuPile = new PileView("CPU");

    private CardViewImpl briscolaCardView;
    private CardViewImpl playerPlayedCardView;
    private CardViewImpl cpuPlayedCardView;


    private final CardViewImpl[] playerHandCards = new CardViewImpl[NUMBER_OF_CARDS];
    private final CardViewImpl[] cpuHandCards = new CardViewImpl[NUMBER_OF_CARDS];

    private MenuController menuController;
    private GameController gameController;

    /**
<<<<<<< HEAD
     * Constructs a new {@code GameViewImpl} with the specified Menu Controller.
     * 
=======
     * Constructs a new {@code GameViewImpl} with the specified menuController.
     *
>>>>>>> 0d87a0e4b085f851ffd9f9473021ac6dbfb7593a
     * @param menuController the controller with the role of handling the menu events
     */
    public GameViewImpl(final MenuController menuController) {
        super("BriscOOla");
        this.menuController = menuController;
        this.initLayoutConfiguration();
    }

    private void initLayoutConfiguration() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
                final StartScreen startScreen = new StartScreen(
            (players, diff) -> {
                if (this.menuController != null) {
                this.menuController.startGame(players, diff);
                }
            }, 
            e -> quit()
        ); 

        final JPanel gamePanel = createGamePanel();

        container.add(startScreen, MENU_ID);
        container.add(gamePanel, GAME_ID);

        this.add(container);
        this.pack();

        this.setLocationRelativeTo(null); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMenuController(final MenuController menuController) {
        this.menuController = menuController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameController(final GameController gameController) {
        this.gameController = gameController;
    }

    /** 
     * Creation of the board.
     *
     * @return gamePanel
     */
    private JPanel createGamePanel() {
        final JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(new Color(BG_R, BG_G, BG_B));

        final JPanel northArea = new JPanel(new BorderLayout());
        northArea.setOpaque(false); 
        northArea.setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));

        final JPanel cpuHandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, FLOW_GAP_HAND, 0));
        cpuHandPanel.setOpaque(false);
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            cpuHandCards[i] = new CardViewImpl();
            cpuHandCards[i].renderCard(null, null); 
            cpuHandPanel.add(cpuHandCards[i]);
        }

        northArea.add(cpuHandPanel, BorderLayout.CENTER);
        northArea.add(cpuPile, BorderLayout.EAST); 
        mainPanel.add(northArea, BorderLayout.NORTH);

        final JPanel southArea = new JPanel(new BorderLayout());
        southArea.setOpaque(false); 
        southArea.setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));

        final JPanel playerHandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, FLOW_GAP_HAND, 0));
        playerHandPanel.setOpaque(false);
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            playerHandCards[i] = new CardViewImpl();
            playerHandCards[i].renderCard(null, null);

            final int cardIndex = i;

            playerHandCards[i].addCardClickListener(e -> {
                if (this.gameController != null) {
                    this.gameController.handlesHumanCardSelection(cardIndex);
                }
            });
            playerHandPanel.add(playerHandCards[i]);
        }

        southArea.add(playerHandPanel, BorderLayout.CENTER);
        southArea.add(playerPile, BorderLayout.EAST); 
        mainPanel.add(southArea, BorderLayout.SOUTH);

        final JPanel westArea = new JPanel(new GridBagLayout());
        westArea.setOpaque(false);
        westArea.setBorder(BorderFactory.createEmptyBorder(0, WEST_PADDING, 0, 0));

        final JPanel deckBriscolaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, FLOW_GAP_DECK, 0));
        deckBriscolaPanel.setOpaque(false);

        final JLabel deckLabel = new JLabel();
        final URL deckUrl = getClass().getResource("/cards/deck.png");

        if (deckUrl != null) {
            final ImageIcon deckIcon = new ImageIcon(deckUrl);

            final Image img = deckIcon.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
            deckLabel.setIcon(new ImageIcon(img));
        } else {

            deckLabel.setText("Mazzo");
            deckLabel.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
            deckLabel.setOpaque(true);
            deckLabel.setBackground(new Color(FALLBACK_R, FALLBACK_G, FALLBACK_B));
            deckLabel.setHorizontalAlignment(SwingConstants.CENTER);
            deckLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }

        briscolaCardView = new CardViewImpl();
        briscolaCardView.renderCard(null, null);

        deckBriscolaPanel.add(deckLabel);
        deckBriscolaPanel.add(briscolaCardView);

        westArea.add(deckBriscolaPanel);
        mainPanel.add(westArea, BorderLayout.WEST);

        final JPanel tableCenter = new JPanel(new FlowLayout(FlowLayout.CENTER, FLOW_GAP_TABLE, 0));
        tableCenter.setOpaque(false);

        this.playerPlayedCardView = new CardViewImpl();
        this.cpuPlayedCardView = new CardViewImpl();

        this.playerPlayedCardView.setVisible(false);
        this.cpuPlayedCardView.setVisible(false);

        tableCenter.add(this.playerPlayedCardView);
        tableCenter.add(this.cpuPlayedCardView);

        mainPanel.add(tableCenter, BorderLayout.CENTER);

        return mainPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame() {
        cardLayout.show(container, GAME_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHand(final int playerID, final List<Card> handCards) {
        if (playerID == 0) {
            for (int i = 0; i < NUMBER_OF_CARDS; i++) {
                if (i < handCards.size()) {
                    final Card card = handCards.get(i);
                    final CardView cardComponent = this.playerHandCards[i];
                    final String seedStr = card.getCardSeed().name();
                    final String valueStr = card.getCardValue().name();
                    cardComponent.renderCard(seedStr, valueStr);

                    this.playerHandCards[i].setVisible(true);
                } else {
                    this.playerHandCards[i].setVisible(false);
                }
            }
        } else {
            for (int i = 0; i < NUMBER_OF_CARDS; i++) {
                if (i < handCards.size()) {
                    final CardView cardComponent = this.cpuHandCards[i];
                    cardComponent.renderCard(null, null);
                    this.cpuHandCards[i].setVisible(true);
                } else {
                    this.cpuHandCards[i].setVisible(false);
                }
            }
        }

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePile(final int cardsCount, final boolean player) {
        if (player) {
            this.playerPile.updateCount(cardsCount);
        } else {
            this.cpuPile.updateCount(cardsCount);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayMessage(final String message) {
        // I'd add an input of type Popups popup to specify the type of popup to show
        System.out.println(message);
        /*PopupFactory factory = new PopupFactoryImpl();
        factory.create(this.rootPane,popup, message).show();*/
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * Returns a safe clone of the player hand components array.
     *
     * @return an array containing the hand component views
     */
    public CardViewImpl[] getPlayerHandCards() {
        return this.playerHandCards.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBriscola(final String seed, final String value) {
        this.briscolaCardView.renderCard(seed, value);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTable(final String playerSeed, final String playerValue, final String cpuSeed, final String cpuValue) {
        if (playerSeed != null && playerValue != null) {
            this.playerPlayedCardView.renderCard(playerSeed, playerValue);
            this.playerPlayedCardView.setVisible(true);
        } else {
            this.playerPlayedCardView.setVisible(false);
        }

        if (cpuSeed != null && cpuValue != null) {
            this.cpuPlayedCardView.renderCard(cpuSeed, cpuValue);
            this.cpuPlayedCardView.setVisible(true);
        } else {
            this.cpuPlayedCardView.setVisible(false);
        }

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
}

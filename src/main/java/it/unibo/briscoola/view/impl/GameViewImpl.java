package it.unibo.briscoola.view.impl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.briscoola.view.api.View;

public class GameViewImpl extends JFrame implements View {
    
    private static final String MENU_ID = "MENU";
    private static final String GAME_ID = "GAME";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel container = new JPanel(cardLayout);
    
    private final PileView playerPile = new PileView("Player");
    private final PileView cpuPile = new PileView("CPU");

    private CardView briscolaCardView;
    private JLabel deckLabel;

    private final CardView[] playerHandCards = new CardView[3];
    private final CardView[] cpuHandCards = new CardView[3];

    public GameViewImpl() {
        super("BriscOOla");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1250, 850));

        StartScreen startScreen = new StartScreen(
            e -> initGame(), 
            e -> quit()
        );  

        JPanel gamePanel = createGamePanel();

        container.add(startScreen, MENU_ID);
        container.add(gamePanel, GAME_ID);

        this.add(container);
        this.pack();
        
        // Posiziona la finestra al centro dello schermo quando si avvia
        this.setLocationRelativeTo(null); 
    }


    /** * Crea il tavolo da gioco.
     * @return gamePanel 
     */
    private JPanel createGamePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        mainPanel.setBackground(new Color(20, 80, 25)); 

        /* --- CPU on North --- */
        JPanel northArea = new JPanel(new BorderLayout());
        northArea.setOpaque(false); 
        northArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel cpuHandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        cpuHandPanel.setOpaque(false);
        for (int i = 0; i < 3; i++) {
            cpuHandCards[i] = new CardView();
            cpuHandCards[i].renderCard(null); // Mostra il dorso
            cpuHandPanel.add(cpuHandCards[i]);
        }
        
        northArea.add(cpuHandPanel, BorderLayout.CENTER);
        northArea.add(cpuPile, BorderLayout.EAST); 
        mainPanel.add(northArea, BorderLayout.NORTH);
        
        /* --- Player Guest on South --- */
        JPanel southArea = new JPanel(new BorderLayout());
        southArea.setOpaque(false); 
        southArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel playerHandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        playerHandPanel.setOpaque(false);
        for (int i = 0; i < 3; i++) {
            playerHandCards[i] = new CardView();
            playerHandCards[i].renderCard(null); // Mostra il retro della carta
            playerHandPanel.add(playerHandCards[i]);
        }

        southArea.add(playerHandPanel, BorderLayout.CENTER);
        southArea.add(playerPile, BorderLayout.EAST); 
        mainPanel.add(southArea, BorderLayout.SOUTH);

        /* --- Deck and Briscola on West --- */
        JPanel westArea = new JPanel(new GridBagLayout());
        westArea.setOpaque(false);
        westArea.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0)); // Margini dal bordo sinistro
        
        JPanel deckBriscolaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        deckBriscolaPanel.setOpaque(false);

        deckLabel = new JLabel();
        URL deckUrl = getClass().getResource("/cards/deck.png");
        
        if (deckUrl != null) {
            ImageIcon deckIcon = new ImageIcon(deckUrl);
            
            Image img = deckIcon.getImage().getScaledInstance(120, 170, Image.SCALE_SMOOTH);
            deckLabel.setIcon(new ImageIcon(img));
        } else {
            // Fallback
            deckLabel.setText("Mazzo");
            deckLabel.setPreferredSize(new Dimension(120, 170));
            deckLabel.setOpaque(true);
            deckLabel.setBackground(new Color(100, 149, 237));
            deckLabel.setHorizontalAlignment(SwingConstants.CENTER);
            deckLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }

        briscolaCardView = new CardView();
        briscolaCardView.renderCard(null);

        deckBriscolaPanel.add(deckLabel);
        deckBriscolaPanel.add(briscolaCardView);
        
        westArea.add(deckBriscolaPanel);
        mainPanel.add(westArea, BorderLayout.WEST);

        /* --- Game Board in the middle --- */
        JPanel tableCenter = new JPanel(new GridBagLayout());
        tableCenter.setOpaque(false);
        
        mainPanel.add(tableCenter, BorderLayout.CENTER);

        return mainPanel;
    }


    /**
     * @InheritDoc
     */
    @Override
    public void start() {
        this.setVisible(true);
    }

    
    /**
     * @InheritDoc
     */
    @Override
    public void initGame() {
        cardLayout.show(container, GAME_ID);
    }

    
    /**
     * @InheritDoc
     */
    @Override
    public void updateHand() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateHand'");
    }

    
    /**
     * @InheritDoc
     */
    @Override
    public void updatePile(int cardsCount, boolean player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePile'");
    }


    /**
     * @InheritDoc
     */
    @Override
    public void displayMessage(String message) {
        System.out.println(message);    
    }


    /**
     * @InheritDoc
     */
    @Override
    public void quit() {
        System.exit(0);
    }
}
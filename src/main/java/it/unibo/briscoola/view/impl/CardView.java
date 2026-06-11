package it.unibo.briscoola.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.attributes.CardValue;
import it.unibo.briscoola.model.impl.card.StandardCardImpl;

public class CardView extends JPanel {

    private final JLabel cardLabel;

    public CardView() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 150));
        setBackground(Color.WHITE);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        cardLabel = new JLabel();
        cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(cardLabel, BorderLayout.CENTER);
    }

    public void renderCard(StandardCardImpl cardModel) {
        if (cardModel == null) {
            URL backUrl = getClass().getResource("/cards/backside.png");
            
            if (backUrl != null) {
                ImageIcon icon = new ImageIcon(backUrl);
                Image img = icon.getImage().getScaledInstance(
                        this.getPreferredSize().width, 
                        this.getPreferredSize().height, 
                        Image.SCALE_SMOOTH);
                cardLabel.setIcon(new ImageIcon(img));
                cardLabel.setText(""); 
                setBackground(Color.WHITE);
            } else {
                cardLabel.setIcon(null);
                cardLabel.setText("DORSO");
                setBackground(new Color(100, 149, 237)); 
            }
            
            revalidate();
            repaint();
            return;
        }

        // traduzione da ENUM a valore per la selezione del png
        String translatedSeed = mapSeedToFileName(cardModel.getCardSeed());
        int numericValue = mapValueToNumber(cardModel.getCardValue());
        
        String filename = translatedSeed + "_" + numericValue + ".png";
        URL imgUrl = getClass().getResource("/cards/" + filename);

        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image img = icon.getImage().getScaledInstance(
                    this.getPreferredSize().width, 
                    this.getPreferredSize().height, 
                    Image.SCALE_SMOOTH);
            cardLabel.setIcon(new ImageIcon(img));
            cardLabel.setText("");
            setBackground(Color.WHITE);
        } else {
            cardLabel.setIcon(null);
            cardLabel.setText(cardModel.getCardValue() + " di " + cardModel.getCardSeed());
            setBackground(Color.LIGHT_GRAY);
            System.err.println("(CardView): Immagine non trovata -> /cards/" + filename);
        }

        revalidate();
        repaint();
    }

    private String mapSeedToFileName(CardSeed seed) {
        return switch (seed) {
            case STAFF -> "bastoni";
            case CUP -> "coppe";
            case COIN -> "denari";
            case SWORD -> "spade";
        };
    }

    private int mapValueToNumber(CardValue value) {
        return switch (value) {
            case ACE -> 1;
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case JACK -> 8;
            case HORSE -> 9;
            case KING -> 10;
        };
    }
}
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

import it.unibo.briscoola.view.api.CardView;

public class CardViewImpl extends JPanel implements CardView {

    private final JLabel cardLabel;

    public CardViewImpl() {
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


    public void renderCard(String seed, String value) {
        
        if (seed == null || value == null) {
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

        String filename = seed + "_" + value + ".png";
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
            cardLabel.setText(value + " of " + seed);
            setBackground(Color.LIGHT_GRAY);
            System.err.println("(CardView): Immagine non trovata -> /cards/" + filename);
        }

        revalidate();
        repaint();
    }
}
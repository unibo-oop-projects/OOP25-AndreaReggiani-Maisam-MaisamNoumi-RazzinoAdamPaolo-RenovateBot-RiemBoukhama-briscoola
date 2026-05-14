package it.unibo.briscoola.view.impl;

import javax.swing.*;
import java.awt.*;

import it.unibo.briscoola.view.api.View;

public class GameViewImpl extends JFrame implements View{
    
    private static final String MENU_ID = "MENU";
    private static final String GAME_ID = "GAME";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel container = new JPanel(cardLayout);
    
    private final PileView playerPile = new PileView("Player");
    private final PileView cpuPile = new PileView("CPU");

    public GameViewImpl(){
        super("Briscola");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1000,700));

        StartScreen startScreen= new StartScreen(
            e -> initGame(), 
            e -> quit()
            );  

            /* creation of the game Board, only game part */
            JPanel gamePanel = createGamePanel();

            container.add(startScreen,MENU_ID);
            container.add(gamePanel,GAME_ID);

            this.add(container);
            this.pack();
    }


    /** 
     * create the gameBoard
     * @return gamePanel 
     */
    private JPanel createGamePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(new Color(35,140,35));
        /*here is necessary to devide the board in 3 Area:*/

        /*CPU on North*/
        JPanel northArea = new JPanel(new BorderLayout());
        northArea.setOpaque(false); /* to determine if component is completely non-transparent or transparen */
        northArea.add(new JLabel("CPU hand"),BorderLayout.CENTER);
        northArea.add(cpuPile,BorderLayout.EAST);
        mainPanel.add(northArea,BorderLayout.NORTH);
        
        /*Game Board in the middle*/
        JPanel tableCenter= new JPanel(new  BorderLayout());
        tableCenter.setOpaque(false);
        northArea.add(new JLabel("Board"));
        mainPanel.add(tableCenter,BorderLayout.CENTER);

        /*Player Guest on South */
        JPanel southArea = new JPanel(new BorderLayout());
        southArea.setOpaque(false); /* to determine if component is completely non-transparent or transparen */
        southArea.add(new JLabel("Your hand"),BorderLayout.CENTER);
        southArea.add(playerPile,BorderLayout.EAST);
        mainPanel.add(southArea,BorderLayout.SOUTH);

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

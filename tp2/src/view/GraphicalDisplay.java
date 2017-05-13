package view;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

import model.*;

/**
 * This program is a simple card game.  The user sees a card and
 * tries to predict whether the next card will be higher or 
 * lower.  Aces are the lowest-valued cards.  If the user makes
 * three correct predictions, the user wins.  If not, the
 * user loses.
 * 
 * This class defines a panel, but it also contains a main()
 * routine that makes it possible to run the program as a
 * stand-alone application.
 * 
 * This program depends on several additional source code files:
 * Card.java, Hand.java, and Deck.java.  It also requires the image
 * file, cards.png, which contains the images of the playing cards.
 * (The file cards.png is taken from the Gnome Desktop.)
 */
public class GraphicalDisplay extends JPanel implements Display{


    private static class GraphicalDisplayHolder{
        private static final GraphicalDisplay INSTANCE = new GraphicalDisplay();
    }

    public static GraphicalDisplay getInstance(){
        return GraphicalDisplayHolder.INSTANCE;
    }

    /**
     * The main routine simply opens a window that shows a HighLowWithImages panel.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Truco");
        GraphicalDisplay content = new GraphicalDisplay();
        window.setContentPane(content);
        window.pack();  // Set size of window to preferred size of its contents.
        window.setResizable(false);  // User can't change the window's size.
        window.setLocation(200,50);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
    }

    public void displayInitialScreen(){
        JFrame window = new JFrame("Truco");
        GraphicalDisplay content = new GraphicalDisplay();
        window.setContentPane(content);
        window.pack();  // Set size of window to preferred size of its contents.
        window.setResizable(false);  // User can't change the window's size.
        window.setLocation(200,50);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
    }

    public int getTeamSize(){
        return 2;
    }

    public void printString(String s){

    }

    /**
     * The constructor lays out the panel.  A CardPanel occupies the CENTER 
     * position of the panel (where CardPanel is a subclass of JPanel that is 
     * defined below).  On the bottom is a panel that holds three buttons.  
     * The CardPanel listens for ActionEvents from the buttons and does all 
     * the real work of the program.
     */
    public GraphicalDisplay() {

        setBackground( new Color(130,50,40) );

        setLayout( new BorderLayout(3,3) );

        CardPanel board = new CardPanel();
        add(board, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground( new Color(220,200,180) );
        add(buttonPanel, BorderLayout.SOUTH);   

        JButton higher = new JButton( "Aceitar" );
        higher.addActionListener(board);
        buttonPanel.add(higher);

        JButton lower = new JButton( "Correr" );
        lower.addActionListener(board);
        buttonPanel.add(lower);

        JButton newGame = new JButton( "Trucar" );
        newGame.addActionListener(board);
        buttonPanel.add(newGame);

        setBorder(BorderFactory.createLineBorder( new Color(130,50,40), 3) );
//        this.show();
    }  // end constructor



    /**
     * A nested class that displays the cards and does all the work
     * of keeping track of the state and responding to user events.
     */
    private class CardPanel extends JPanel implements ActionListener {

        Deck deck;       // A deck of cards to be used in the game.
        Hand hand;       // The cards that have been dealt.
        Table table;
        String message;  // A message drawn on the canvas, which changes
                         //    to reflect the state of the game.


        Font bigFont;      // Font that will be used to display the message.
        Font smallFont;      // Font that will be used to display the message.

        Image cardImages;  // Contains the image of all 52 cards 

        /**
         * Constructor creates fonts, sets the foreground and background
         * colors and starts the first game.  It also sets a "preferred
         * size" for the panel.  This size is respected when the program
         * is run as an application, since the pack() method is used to
         * set the size of the window.
         */
        CardPanel() {
            loadImage();
            setBackground( new Color(0,120,0) );
            setForeground( Color.BLACK );
            bigFont = new Font("Serif", Font.BOLD, 30);
            smallFont = new Font("Serif", Font.BOLD, 15);
            setPreferredSize( new Dimension(400+4*(15+79), 400));
            drawTable();
            // doNewGame();
        } // end constructor


        /**
         * Respond when the user clicks on a button by calling the appropriate 
         * method.  Note that the buttons are created and listening is set
         * up in the constructor of the HighLowPanel class.
         */
        public void actionPerformed(ActionEvent evt) {
            String command = evt.getActionCommand();
            if (command.equals("Aceitar"))
                doAceitar();
            else if (command.equals("Correr"))
                doCorrer();
            else if (command.equals("Trucar"))
                doNewGame();
        } // end actionPerformed()


        /**
         * Called by actionPerformed() when user clicks "Aceitar" button.
         * Check the user's prediction.  Game ends if user guessed
         * wrong or if the user has made three correct predictions.
         */
        void doAceitar() {

        } // end doAceitar()


        /**
         * Called by actionPerformed() when user clicks "Correr" button.
         * Check the user's prediction.  Game ends if user guessed
         * wrong or if the user has made three correct predictions.
         */
        void doCorrer() {
            
        } // end doCorrer()


        /**
         * Called by the constructor, and called by actionPerformed() if
         * the use clicks the "Trucar" button.  Start a new game.
         */
        void doNewGame() {

        } // end doNewGame()

        void drawTable() {
            System.out.println("hello");
            deck = new Deck();   // Create the deck and hand to use for this game.
            hand = new Hand();
            table = new Table(2,2);
            deck.shuffleDeck();
            hand.addCard( deck.getFirstCard() );  // Deal the first card into the hand.
            hand.addCard( deck.getFirstCard() );  // Deal the first card into the hand.
            hand.addCard( deck.getFirstCard() );  // Deal the first card into the hand.
            // hand.addCard( deck.getFirstCard() );  // Deal the first card into the hand.
        System.out.println("hello");
            table.addCard(deck.getFirstCard(), 0,0);
            table.addCard(deck.getFirstCard(), 1,0);
            table.addCard(deck.getFirstCard(), 2,1);
            table.addCard(deck.getFirstCard(), 3,1);
            repaint();
        } // end doNewGame()


        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (cardImages == null) {
                g.drawString("Error: Can't get card images!", 10,30);
                return;
            }

            paintScore(g);

            paintTable(g);

            paintHands(g);
        } // end paintComponent()

        private void paintScore(Graphics g){
            g.setFont(bigFont);
            g.drawString("Time 1",15,50);
            g.drawString(4+ "",50,80);
            g.drawString("Time 2",15,120);
            g.drawString(2+ "",50,150);

            g.setFont(smallFont);
            g.drawString("Valor Atual: " + "Truco(4)",850,50);
        }

        private void paintTable(Graphics g){
            //Table
            int ypos[] = {450,250,350,350};
            int xpos[] = {1,1,0,2};

            int i = 0;
            for(java.util.Map.Entry<Integer, Card> e : table){
                // System.out.println(e.getValue());
                drawCard(g, e.getValue(), 450 + (xpos[i]) * (15+79), ypos[i]);
                i++;
            }
            // System.out.println("hello");
        }

        private void paintHands(Graphics g){
            // hand.clearHand();
            // hand.addCard( deck.getFirstCard() );  // Deal the first card into the hand.
            // hand.addCard( deck.getFirstCard() );  // Deal the first card into the hand.
            // hand.addCard( deck.getFirstCard() );  // Deal the first card into the hand.
            //0
            int i = 0;
            for( Card c : hand){
                drawCard(g, c, 450 + i * (15+79), 650);
                // System.out.println(c);
                i++;
            }
            // drawCard(g, hand.getCard(5), 450 + 1 * (15+79), 650);
            // drawCard(g, hand.getCard(6), 450 + 2 * (15+79), 650);

            //2
            drawCard(g, null, 850, 200 + 0 * (130));
            drawCard(g, null, 850, 200 + 1 * (130));
            drawCard(g, null, 850, 200 + 2 * (130));


            //1
            drawCard(g, null, 450 + 0 * (15+79), 50);
            drawCard(g, null, 450 + 1 * (15+79), 50);
            drawCard(g, null, 450 + 2 * (15+79), 50);


            // //3
            drawCard(g, null, 250, 200 + 0 * (130));
            drawCard(g, null, 250, 200 + 1 * (130));
            drawCard(g, null, 250, 200 + 2 * (130));

        }

        /**
         * Draws a card in a 79x123 pixel picture of a card with its
         * upper left corner at a specified point (x,y).  Drawing the card 
         * requires the image file "cards.png".
         * @param g The non-null graphics context used for drawing the card.  If g is
         * null, a NullPointerException will be thrown.
         * @param card The card that is to be drawn.  If the value is null, then a
         * face-down card is drawn.
         * @param x the x-coord of the upper left corner of the card
         * @param y the y-coord of the upper left corner of the card
         */
        public void drawCard(Graphics g, Card card, int x, int y) {
            int cx;    // x-coord of upper left corner of the card inside cardsImage
            int cy;    // y-coord of upper left corner of the card inside cardsImage
            if (card == null) {
                cy = 4*123;   // coords for a face-down card.
                cx = 2*79;
            }
            else {
                int value = 0;
                switch(card.getValue()){
                    case 'K':
                        value = 13;break;
                    case 'Q':
                        value = 12;
                        break;
                    case 'J':
                        value = 11;
                        break;
                    default:
                        value = Character.getNumericValue(card.getValue());
                        break;
                }
                cx = (value-1)*79;
                switch (card.getSuit()) {
                    case CLUBS:
                        cy = 0; 
                        break;
                    case DIAMONDS:
                        cy = 123; 
                        break;
                    case HEARTS:
                        cy = 2*123; 
                        break;
                    default:  // spades
                        cy = 3*123; 
                        break;
                }
            }
            g.drawImage(cardImages,x,y,x+79,y+123,cx,cy,cx+79,cy+123,this);
        }


        /**
         * Load the image from the file "cards.png", which must be somewhere
         * on the classpath for this program.  If the file is found, then
         * cardImages will refer to the Image.  If not, then cardImages
         * will be null.
         */
        private void loadImage() {
            ClassLoader cl = getClass().getClassLoader();
            URL imageURL = cl.getResource("view/cards.png");
            if (imageURL != null)
                cardImages = Toolkit.getDefaultToolkit().createImage(imageURL);
        }


    } // end nested class CardPanel


}

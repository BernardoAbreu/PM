package controller;

import model.Card;
import model.Deck;
import model.Suits;
import view.Display;

import java.util.SplittableRandom;

/**
 * Created by math on 5/5/17.
 */
public class Match {

    public void run(Display d){
        d.printString("IN match, register display observer: ");
        Deck deck = new Deck();
        deck.printDeck();

        System.out.println("================= SHUFFLE =====================");
        deck.shuffleDeck();
//        deck.printDeck();
        System.out.println("=================== GETFIRST ===================");
        Card c = deck.getFirstCard();
        System.out.println("c suit:  " + String.valueOf(c.getSuit()) + "  c value: " + String.valueOf(c.getValue()));
//        deck.printDeck();

        Card c2 = deck.getFirstCard();
        System.out.println("c2 suit:  " + String.valueOf(c2.getSuit()) + "  c2 value: " + String.valueOf(c2.getValue()));
        int result = c.compareTo(c2);
        if(result == 0){
            System.out.println("igual");
        }else if(result == -1){
            System.out.println("menor");
        }else if(result ==1){
            System.out.println("maior");
        }else{
            System.out.println("ERROR");
        }
    }
}

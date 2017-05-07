package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by math on 5/7/17.
 */
public class Hand {
    private List<Card> cards;

    public Hand(){
        this.cards = new ArrayList<Card>(3);
    }

    public void addCard(Card c){
        this.cards.add(c);
    }

    public void printHand(){
        System.out.println("Printing cards: ");
        this.cards.forEach(x-> System.out.println("SUIT: " + String.valueOf(x.getSuit()) + "  VALUE:  " + String.valueOf(x.getValue())));
    }

    public List<Card> getCards(){
        return this.cards;
    }

    public void clearHand(){
        this.cards.clear();
    }

}

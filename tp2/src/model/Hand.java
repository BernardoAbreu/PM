package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;

/**
 * Created by math on 5/7/17.
 */


public class Hand implements Iterable<Card>{
    private List<Card> cards;

    public Hand(){
        this.cards = new ArrayList<Card>(3);
    }

    public void addCard(Card c){
        this.cards.add(c);
    }

    public Card removeCard(int index){
        return this.cards.remove(index);
    }

    // public void printHand(){
    //     System.out.println("Printing hand: ");
    //     // this.cards.forEach(x-> System.out.println("SUIT: " + String.valueOf(x.getSuit()) + "  VALUE:  " + String.valueOf(x.getValue())));
    //     this.cards.forEach(x-> System.out.print(x + " "));
    //     System.out.println();
    // }

    // public List<Card> getCards(){
    //     return this.cards;
    // }

    public void clearHand(){
        this.cards.clear();
    }

    public Card removeMaxCard(){
        Card max;
        if(this.cards.isEmpty()){
            throw new java.util.NoSuchElementException();
        }
        else{
            Iterator<Card> iter = this.cards.iterator();
            max = iter.next();

            while (iter.hasNext()) {
                Card c = iter.next();
                if(c.compareTo(max) > 0){
                    max = c;
                }
            }
        }
        return max;
    }

    private class HandIterator implements Iterator<Card> {
        private Iterator<Card> iterator;
        private Card card;

        public HandIterator(Hand hand) {
            this.iterator = hand.cards.iterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public Card next() {
            if (!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            
            return iterator.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Card> iterator() {
        return new HandIterator(this);
    }

}

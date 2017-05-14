package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Math on 5/6/2017.
 */
public class Deck {
    private List<Card> cards;
    private List<Card> givenOutCards;

    public Deck(){
        this.cards = new ArrayList<Card>();
        this.givenOutCards = new ArrayList<Card>();
        //MANILHAS
        this.cards.add(new Card(Suits.CLUBS, '4', 14));
        this.cards.add(new Card(Suits.HEARTS, '7', 13));
        this.cards.add(new Card(Suits.SPADES, '1', 12));
        this.cards.add(new Card(Suits.DIAMONDS, '7', 11));
        //FIRST CARDS
        this.cards.add(new Card(Suits.CLUBS, '3', 10));
        this.cards.add(new Card(Suits.HEARTS, '3', 10));
        this.cards.add(new Card(Suits.SPADES, '3', 10));
        this.cards.add(new Card(Suits.DIAMONDS, '3', 10));

        this.cards.add(new Card(Suits.CLUBS, '2', 9));
        this.cards.add(new Card(Suits.HEARTS, '2', 9));
        this.cards.add(new Card(Suits.SPADES, '2', 9));
        this.cards.add(new Card(Suits.DIAMONDS, '2', 9));

        this.cards.add(new Card(Suits.CLUBS, '1', 8));
        this.cards.add(new Card(Suits.HEARTS, '1', 8));
        this.cards.add(new Card(Suits.DIAMONDS, '1', 8));
        //King
        this.cards.add(new Card(Suits.CLUBS, 'K', 7));
        this.cards.add(new Card(Suits.HEARTS, 'K', 7));
        this.cards.add(new Card(Suits.SPADES, 'K', 7));
        this.cards.add(new Card(Suits.DIAMONDS, 'K', 7));
        //Knight
        this.cards.add(new Card(Suits.CLUBS, 'J', 6));
        this.cards.add(new Card(Suits.HEARTS, 'J', 6));
        this.cards.add(new Card(Suits.SPADES, 'J', 6));
        this.cards.add(new Card(Suits.DIAMONDS, 'J', 6));
        //Queen
        this.cards.add(new Card(Suits.CLUBS, 'Q', 5));
        this.cards.add(new Card(Suits.HEARTS, 'Q', 5));
        this.cards.add(new Card(Suits.SPADES, 'Q', 5));
        this.cards.add(new Card(Suits.DIAMONDS, 'Q', 5));

        this.cards.add(new Card(Suits.CLUBS, '7',4));
        this.cards.add(new Card(Suits.SPADES, '7',4));
        this.cards.add(new Card(Suits.CLUBS, '6',3));
        this.cards.add(new Card(Suits.SPADES, '6',3));
        this.cards.add(new Card(Suits.HEARTS, '6',3));
        this.cards.add(new Card(Suits.DIAMONDS, '6',3));
        this.cards.add(new Card(Suits.CLUBS, '5',2));
        this.cards.add(new Card(Suits.HEARTS, '5',2));
        this.cards.add(new Card(Suits.SPADES, '5',2));
        this.cards.add(new Card(Suits.DIAMONDS, '5',2));
        this.cards.add(new Card(Suits.HEARTS, '4',1));
        this.cards.add(new Card(Suits.SPADES, '4',1));
        this.cards.add(new Card(Suits.DIAMONDS, '4',1));
    }

    public Card getFirstCard(){
        Card c = this.cards.get(0);
        this.cards.remove(c);
        this.givenOutCards.add(c);
        return c;
    }

    public void shuffleDeck(){
        Collections.shuffle(this.cards);
    }

    public void resetDeck(){
        for(Card c : this.givenOutCards){
            this.cards.add(c);
        }
        this.givenOutCards.clear();
    }
}

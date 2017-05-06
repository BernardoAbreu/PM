package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Math on 5/6/2017.
 */
public class Card implements Comparable<Card>{
    private Suits suit;
    private char value;

    // private static final Map<Card, Integer> cardOrder = new HashMap<Card, Integer>(){{
    //     put(new Card());
    // }};



    public Card(Suits suit, char value) {
        this.suit = suit;
        this.value = value;
    }

    public Suits getSuit() {
        return suit;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public int compareTo(Card o) {
        return 0;
    }
}

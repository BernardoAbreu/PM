package model;

import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Math on 5/6/2017.
 */
public class Card implements Comparable<Card>{
    private Suits suit;
    private char value;
    private int trucoValue;

    public Card(Suits suit, char value, int trucoValue) {
        this.suit = suit;
        this.value = value;
        this.trucoValue = trucoValue;
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

    public int getTrucoValue() {
        return trucoValue;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public int compareTo(Card o) {
        return (this.trucoValue == o.getTrucoValue())? 0 : (this.trucoValue > o.getTrucoValue())? 1: -1;
    }
}

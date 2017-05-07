package model;

/**
 * Created by Math on 5/6/2017.
 */
public enum Suits {
    HEARTS(0x2665), CLUBS(0x2663), DIAMONDS(0x2666), SPADES(0x2660);

    private final int id;
    Suits(int id) { this.id = id; }

    public String getString() { return String.valueOf(Character.toChars(this.id)); }
}

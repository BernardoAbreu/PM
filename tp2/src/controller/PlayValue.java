package controller;


public enum PlayValue {
    NORMAL(2), TRUCO(4), SEIS(8), NOVE(10), DOZE(12);

    private final int id;
    private static PlayValue[] vals = values();

    PlayValue(int id) { this.id = id; }

    public int getValue() { return id; }

    public PlayValue next(){
        return vals[(this.ordinal()+1) % vals.length];
    }
}

package model;

/**
 * Created by math on 5/5/17.
 */
public abstract class Player {

    private int id;
    protected Hand hand;

    public Hand getHand() {
        return hand;
    }

    public Player(int id){
        this.id = id;
        this.hand = new Hand();
    }

    public int getId(){
        return this.id;
    }

    public abstract Option getOption();

    public abstract void playCard();
}

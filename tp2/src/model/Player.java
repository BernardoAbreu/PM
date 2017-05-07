package model;

/**
 * Created by math on 5/5/17.
 */
public abstract class Player {

    private int id;

    public Player(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public abstract Option getOption();

    public abstract void playCard();
}

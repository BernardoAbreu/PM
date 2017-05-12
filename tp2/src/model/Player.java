package model;

/**
 * Created by math on 5/5/17.
 */
public abstract class Player {

    private int id;
    protected Hand hand;
    private Team team;

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

    public void setTeam(Team t){
        this.team = t;
    }

    public Team getTeam(){
        return this.team;
    }

    public abstract Option getOption();
    public abstract Option getOption(boolean b);

    public abstract void playCard(Table t);
}

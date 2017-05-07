package model;

import java.util.List;
import java.util.ArrayList;


/**
 * Created by Math on 5/6/2017.
 */
public class Team {
    private int id;
    private List<Player> players;
    private int score;

    public Team(int id, int size){
        this.id = id;
    	this.players = new ArrayList<Player>(size);
    	this.score = 0;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getScore(){
    	return this.score;
    }

    public void addScore(int score){
    	this.score += score;
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

    public Player getPlayer(int index){
        return this.players.get(index);
    }

    public int getId() {
        return id;
    }
}

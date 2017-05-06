package model;

import java.util.Collection;
import java.util.ArrayList;


/**
 * Created by Math on 5/6/2017.
 */
public class Team {
    private Collection<Player> players;
    private int score;

    public Team(int size){
    	this.players = new ArrayList<Player>(size);
    	this.score = 0;
    }

    public int getScore(){
    	return this.score;
    }

    public void addScore(int score){
    	this.score += score;
    }

}

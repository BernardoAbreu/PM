package model;

import view.Display;
import view.MatchObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by math on 5/12/17.
 */
public class Match extends Observable{

    private int teamSize;

    private int firstPlayerIndex;

    private List<Team> teams;

    private Team winningTeam;

    private Display display;

    private Deck deck;

    public static final int NUMBER_OF_TEAMS = 2;

    public static final int MAX_SCORE = 12;

    private Observer observer;

    public Match(int teamSize, int firstPlayerIndex, Display display, Deck deck) {
        this.teamSize = teamSize;
        this.firstPlayerIndex = firstPlayerIndex;
        this.display = display;
        this.deck = deck;
        this.teams = new ArrayList<Team>(NUMBER_OF_TEAMS);
        this.display.printString("team size: " + teamSize);
        this.winningTeam = new Team(9999,0);
        this.observer = MatchObserver.getInstance();
    }

    public void addTeam(int index){
        this.teams.add(new Team(index, this.teamSize));
    }

    public Team getTeam(int index){
        return this.teams.get(index);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Display getDisplay(){
        return this.display;
    }
    public int getTeamSize() {
        return teamSize;
    }

    public int getFirstPlayerIndex() {
        return firstPlayerIndex;
    }

    public void setFirstPlayerIndex(int firstPlayerIndex) {
        this.firstPlayerIndex = firstPlayerIndex;
    }

    public Team getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(Team winningTeam) {
        this.winningTeam = winningTeam;
        this.notifyObservers();
    }

    public Deck getDeck() {
        return deck;
    }

    public void notifyObservers(){
        this.observer.update(this,null);
    }
}

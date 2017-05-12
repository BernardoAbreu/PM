package model;

import view.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by math on 5/12/17.
 */
public class Match {

    private int teamSize;

    private int firstPlayerIndex;

    private List<Team> teams;

    private Team winningTeam;

    private Display display;

    private Deck deck;

    public static final int NUMBER_OF_TEAMS = 2;

    public static final int MAX_SCORE = 12;

    public Match(int teamSize, int firstPlayerIndex, Display display, Deck deck) {
        this.teamSize = teamSize;
        this.firstPlayerIndex = firstPlayerIndex;
        this.display = display;
        this.deck = deck;
        this.teams = new ArrayList<Team>(NUMBER_OF_TEAMS);
        this.display.printString("team size: " + teamSize);
        this.winningTeam = new Team(9999,0);
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
    }

    public Deck getDeck() {
        return deck;
    }

}

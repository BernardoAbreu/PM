package controller;

import model.Team;
import model.Player;
import view.Display;
import java.util.Collection;


public class Play {

    private int firstPlayerIndex;

    private Collection<Team> teams;

    private Display d;

    private Team winnerTeam;

    public Play(Display d, Collection<Team> teams, int firstPlayerIndex){
        this.d = d;
        this.teams = teams;
        this.firstPlayerIndex = firstPlayerIndex;

    }

    public void run(){
        d.printString("Hello");

        winnerTeam = new Team(0);
        winnerTeam.addScore(12);

    }

    public Team getWinnerTeam(){
        return winnerTeam;
    }

}

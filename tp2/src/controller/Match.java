package controller;

import model.Team;
import model.Player;
import view.Display;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Created by math on 5/5/17.
 */
public class Match {

    private int teamSize;

    private int firstPlayerIndex;

    private Collection<Team> teams;

    private Team winningTeam;

    private Display d;

    private static final int numberOfTeams = 2;

    private static final int maxScore = 12;

    public Match(Display d){
        this.d = d;
        this.d.printString("IN match, register display observer: ");

        teamSize = this.d.getTeamSize();

        firstPlayerIndex = 0;

        this.d.printString("team size: " + teamSize);
        winningTeam = new Team(0);

        teams = new ArrayList<Team>(numberOfTeams);

        for (int i = 0; i < numberOfTeams; i++){
            teams.add(new Team(teamSize));
        }

    }

    public void run(){

        Play play;

        while(winningTeam.getScore() < maxScore){
            play = new Play(d, teams, firstPlayerIndex);

            play.run();

            Team winner = play.getWinnerTeam();

            if(winner.getScore() > winningTeam.getScore()){
                winningTeam = winner;
            }
        }

    }

}

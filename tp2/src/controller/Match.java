package controller;

import model.Team;
import model.Player;
import model.Card;
import model.Deck;
import model.Suits;
import view.Display;
import java.util.Collection;
import java.util.ArrayList;

import java.util.SplittableRandom;

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


        Deck deck = new Deck();
        deck.printDeck();

        System.out.println("================= SHUFFLE =====================");
        deck.shuffleDeck();
//        deck.printDeck();
        System.out.println("=================== GETFIRST ===================");
        Card c = deck.getFirstCard();
        System.out.println("c suit:  " + String.valueOf(c.getSuit()) + "  c value: " + String.valueOf(c.getValue()));
//        deck.printDeck();

        Card c2 = deck.getFirstCard();
        System.out.println("c2 suit:  " + String.valueOf(c2.getSuit()) + "  c2 value: " + String.valueOf(c2.getValue()));
        int result = c.compareTo(c2);
        if(result == 0){
            System.out.println("igual");
        }else if(result == -1){
            System.out.println("menor");
        }else if(result ==1){
            System.out.println("maior");
        }else{
            System.out.println("ERROR");
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

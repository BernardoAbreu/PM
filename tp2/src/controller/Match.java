package controller;

import model.*;

import view.Display;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import java.util.SplittableRandom;

/**
 * Created by math on 5/5/17.
 */
public class Match {

    private int teamSize;

    private int firstPlayerIndex;

    private List<Team> teams;

    private Team winningTeam;

    private Display d;

    private Deck deck;
    private static final int numberOfTeams = 2;

    private static final int maxScore = 12;

    public Match(Display d){
        this.d = d;
        this.d.printString("IN match, register display observer: ");

        teamSize = this.d.getTeamSize();

        firstPlayerIndex = 0;

        this.d.printString("team size: " + teamSize);
        winningTeam = new Team(9999,0);

        teams = new ArrayList<Team>(numberOfTeams);

        // Add Teams
        for (int i = 0; i < numberOfTeams; i++){
            teams.add(new Team(i, teamSize));
        }



        // Add players to teams
        int playerId = 0;

        teams.get(0).addPlayer(new RealPlayer(playerId++));

        for (int i = 1; i < teamSize; i++){
            teams.get(0).addPlayer(new AIPlayer(playerId++));
        }

        for (int i = 1; i < numberOfTeams; i++){
            for (int j = 0; j < teamSize; j++){
                teams.get(i).addPlayer(new AIPlayer(playerId++));
            }
        }

        // Set team for each player
        for(Team team : teams){
            for(Player player : team.getPlayers()){
                player.setTeam(team);
            }
        }

        this.deck = new Deck();

    }


    public void run(){

        Play play = new Play(d, teams, teamSize);

        while(winningTeam.getScore() < maxScore){
            this.deck.resetDeck();
            this.deck.shuffleDeck();
            teams.forEach(x-> giveOutCards(x));

            play.run(firstPlayerIndex);

            Team winner = play.getWinnerTeam();
            
            if(winner != null){
                if(winner.getScore() > winningTeam.getScore()){
                    winningTeam = winner;
                }
            }

            firstPlayerIndex = (firstPlayerIndex+1)%(teamSize*numberOfTeams);

            for(int i = 0; i < teams.size(); i++){
                d.printString("Team " + i + " - Score: " + teams.get(i).getScore());
            }
            d.printString("");

            for(Team team: teams){
                for(Player player: team.getPlayers()){
                    player.getHand().clearHand();
                }
            }
        }

        for(int i = 0; i < teams.size(); i++){
            d.printString("Team " + i + " - Score: " + teams.get(i).getScore());
            System.out.println("Players: ");
            for(int j = 0; j < teamSize; j++){
                System.out.println(teams.get(i).getPlayer(j).getId());
            }
        }

        d.printString("Winning Score: " + winningTeam.getScore());
    }

    private void giveOutCards(Team team){
        // this.deck.printDeck();
        Card card;
        for(Player player: team.getPlayers()){
            card = this.deck.getFirstCard();
            player.getHand().addCard(card);
            card = this.deck.getFirstCard();
            player.getHand().addCard(card);
            card = this.deck.getFirstCard();
            player.getHand().addCard(card);
        }
    }

}

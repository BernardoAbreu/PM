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

        for (int i = 0; i < numberOfTeams; i++){
            teams.add(new Team(i, teamSize));
        }

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



        this.deck = new Deck();
//        deck.printDeck();

//        System.out.println("================= SHUFFLE =====================");
        this.deck.shuffleDeck();
////        deck.printDeck();
//        System.out.println("=================== GETFIRST ===================");
//        Card c = deck.getFirstCard();
//        System.out.println("c suit:  " + String.valueOf(c.getSuit()) + "  c value: " + String.valueOf(c.getValue()));
////        deck.printDeck();
//
//        Card c2 = deck.getFirstCard();
//        System.out.println("c2 suit:  " + String.valueOf(c2.getSuit()) + "  c2 value: " + String.valueOf(c2.getValue()));
//        int result = c.compareTo(c2);
//        if(result == 0){
//            System.out.println("igual");
//        }else if(result == -1){
//            System.out.println("menor");
//        }else if(result ==1){
//            System.out.println("maior");
//        }else{
//            System.out.println("ERROR");
//        }

        // Table table = new Table(numberOfTeams,teamSize);

        // teams.forEach(x-> giveOutCards(x));
        // for(Team team: teams){
        //     System.out.println("Printing hand of team:  " + String.valueOf(team.getId()));
        //     for(Player player: team.getPlayers()){
        //         player.getHand().printHand();
        //         System.out.println(player.getHand().getCards());
        //     }
        // }

        // for(Team team: teams){
        //     for(Player player: team.getPlayers()){
        //         table.addCard(player.getHand().getCards().get(0),player.getId(), team.getId());
        //     }
        // }
        // System.out.println("Printing table:");
        // table.printTable();

        // System.out.println(table.tie());

        // table.clearTable();
        // table.printTable();

        // for(Team team: teams){
        //     for(Player player: team.getPlayers()){
        //         player.getHand().clearHand();
        //     }
        // }

    }

    public void run(){

        Play play = new Play(d, teams, teamSize);

        while(winningTeam.getScore() < maxScore){
            teams.forEach(x-> giveOutCards(x));
            for(Team team: teams){
                System.out.println("Printing hand of team:  " + String.valueOf(team.getId()));
                for(Player player: team.getPlayers()){
                    // player.getHand().printHand();
                    System.out.println("Player "+ player.getId() + ": " + player.getHand().getCards());
                }
            }

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
        this.deck.printDeck();
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

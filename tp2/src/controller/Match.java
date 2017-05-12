package controller;

import model.*;

import view.Display;

import java.util.List;
import java.util.ArrayList;

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

    private static final int NUMBER_OF_TEAMS = 2;

    private static final int MAX_SCORE = 12;

    public Match(Display d){
        this.d = d;

        this.teamSize = this.d.getTeamSize();

        this.firstPlayerIndex = 0;

        this.d.printString("team size: " + teamSize);
        this.winningTeam = new Team(9999,0);

        this.teams = new ArrayList<Team>(NUMBER_OF_TEAMS);
        registerTeams();

        // Add players to teams
        addPlayersToTeams();

        // Set team for each player
        setPlayersTeams();

        this.deck = new Deck();

    }

    private void setPlayersTeams() {
        for(Team team : teams){
            for(Player player : team.getPlayers()){
                player.setTeam(team);
            }
        }
    }

    private void addPlayersToTeams() {
        int playerId = 0;
        //First player of first team will be the RealPlayer
        this.teams.get(0).addPlayer(new RealPlayer(playerId++));

        //Fill up the first team with AI
        for (int i = 1; i < this.teamSize; i++){
            this.teams.get(0).addPlayer(new AIPlayer(playerId++));
        }

        //The second team will contain only AI players
        for (int i = 1; i < NUMBER_OF_TEAMS; i++){
            for (int j = 0; j < this.teamSize; j++){
                this.teams.get(i).addPlayer(new AIPlayer(playerId++));
            }
        }
    }

    private void registerTeams() {
        // Add Teams
        for (int i = 0; i < NUMBER_OF_TEAMS; i++){
            this.teams.add(new Team(i, this.teamSize));
        }
    }


    public void run(){

        Play play = new Play(this.d, this.teams, this.teamSize);

        while(this.winningTeam.getScore() < MAX_SCORE){
            this.deck.resetDeck();
            this.deck.shuffleDeck();
            this.teams.forEach(x-> giveOutCards(x));

            play.run(this.firstPlayerIndex);

            Team winner = play.getWinnerTeam();
            
            if(winner != null){
                if(winner.getScore() > this.winningTeam.getScore()){
                    this.winningTeam = winner;
                }
            }

            this.firstPlayerIndex = (this.firstPlayerIndex+1)%(this.teamSize* NUMBER_OF_TEAMS);

            for(int i = 0; i < teams.size(); i++){
                d.printString("Team " + i + " - Score: " + teams.get(i).getScore());
            }
            d.printString("");

            for(Team team: this.teams){
                for(Player player: team.getPlayers()){
                    player.getHand().clearHand();
                }
            }
        }

        for(int i = 0; i < this.teams.size(); i++){
            d.printString("Team " + i + " - Score: " + this.teams.get(i).getScore());
            System.out.println("Players: ");
            for(int j = 0; j < this.teamSize; j++){
                System.out.println(this.teams.get(i).getPlayer(j).getId());
            }
        }

        this.d.printString("Winning Score: " + this.winningTeam.getScore());
    }

    private void giveOutCards(Team team){
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

package controller;

import model.*;

import view.Display;
import view.MatchObserver;
import view.TerminalDisplay;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by math on 5/5/17.
 */
public class MatchControl {

    private Match match;

    public MatchControl(Display d){
        this.match = new Match(d.getTeamSize(), 0, new Deck());
        registerTeams();
        // Add players to teams
        addPlayersToTeams();
        // Set team for each player
        setPlayersTeams();
    }

    private void setPlayersTeams() {
        for(Team team : this.match.getTeams()){
            for(Player player : team.getPlayers()){
                player.setTeam(team);
            }
        }
    }

    private void addPlayersToTeams() {
        int playerId = 0;
        //First player of first team will be the RealPlayer
        this.match.getTeam(0).addPlayer(new RealPlayer(playerId++));

        //Fill up the first team with AI
        for (int i = 1; i < this.match.getTeamSize(); i++){
            this.match.getTeam(0).addPlayer(new AIPlayer(playerId++));
        }

        //The second team will contain only AI players
        for (int i = 1; i < Match.NUMBER_OF_TEAMS; i++){
            for (int j = 0; j < this.match.getTeamSize(); j++){
                this.match.getTeam(i).addPlayer(new AIPlayer(playerId++));
            }
        }
    }

    private void registerTeams() {
        // Add Teams
        for (int i = 0; i < Match.NUMBER_OF_TEAMS; i++){
            this.match.addTeam(i);
        }
    }


    public void run(){
        Display disp = TerminalDisplay.getInstance();
        PlayControl playControl = new PlayControl(disp, this.match.getTeams(), this.match.getTeamSize());

        while(this.match.getWinningTeam().getScore() < Match.MAX_SCORE){
            this.match.getDeck().resetDeck();
            this.match.getDeck().shuffleDeck();
            this.match.getTeams().forEach(x-> giveOutCards(x));

            playControl.run(this.match.getFirstPlayerIndex());

            Team winner = playControl.getWinnerTeam();

            //There is a winner
            if(winner != null){
                if(winner.getScore() > this.match.getWinningTeam().getScore()){
                    this.match.setWinningTeam(winner);
                }
            }

            this.match.setFirstPlayerIndex((this.match.getFirstPlayerIndex() + 1) % (this.match.getTeamSize() * Match.NUMBER_OF_TEAMS));

            //Clear players hands
            this.match.getTeams().forEach(x-> x.clearPlayersHands());
        }

        //Print teams and players scores
        for(int i = 0; i < this.match.getTeams().size(); i++){
            disp.printString("Team " + i + " - Score: " + this.match.getTeam(i).getScore());
            System.out.println("Players: ");
            for(int j = 0; j < this.match.getTeamSize(); j++){
                System.out.println(this.match.getTeam(i).getPlayer(j).getId());
            }
        }
    }

    private void giveOutCards(Team team){
        Card card;
        for(Player player: team.getPlayers()){
            card = this.match.getDeck().getFirstCard();
            player.getHand().addCard(card);
            card = this.match.getDeck().getFirstCard();
            player.getHand().addCard(card);
            card = this.match.getDeck().getFirstCard();
            player.getHand().addCard(card);
        }
    }

}

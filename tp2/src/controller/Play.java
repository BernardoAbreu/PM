package controller;

import model.*;
import view.Display;
import java.util.List;
import java.util.ArrayList;


public class Play {

    private List<Team> teams;

    private List<Player> players;

    private Team winnerTeam;

    private Table table;

    private Display d;

    private PlayValue playValue;

    public Play(Display d, List<Team> teams, int teamSize){
        this.d = d;
        this.teams = teams;
        this.table = new Table(teams.size(), teamSize);

        this.players = new ArrayList<Player>(teams.size()*teamSize);

        for (int j = 0; j < teamSize; j++){
            for (int i = 0; i < teams.size(); i++){
                this.players.add(this.teams.get(i).getPlayer(j));
            }
        }
    }


    public Team getWinnerTeam(){
        return winnerTeam;
    }


    public void run(int firstPlayerIndex){
        d.printString("Start of Play\n");

        for(Team t : this.teams){
            for(Player p : t.getPlayers()){
                System.out.println("Player " + p.getId() + " team " + t.getId());
                for(Card c : p.getHand()){
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        }

        this.playValue = PlayValue.NORMAL;

        FsmPlay chain = new FsmPlay();

        chain.setTable(this.table);
        chain.setPlayValue(this.playValue);

        for(Player p : this.players){
            chain.addPlayer(p);
        }

        chain.setFirstIndex(firstPlayerIndex);
        while (!chain.end()) {
            chain.change();
        }

        int winnerTeamId = chain.getWinnerTeamId();

        this.playValue = chain.getPlayValue();

        if(winnerTeamId != -1){
            winnerTeam = teams.get(winnerTeamId);
            System.out.println("playValue " + playValue + ": " + this.playValue.getValue());
            System.out.println("\nWinner team:  " + String.valueOf(winnerTeam.getId()));
            winnerTeam.addScore(playValue.getValue());
        }

    }

}

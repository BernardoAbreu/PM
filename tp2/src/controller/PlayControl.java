package controller;

import model.*;
import view.Display;
import java.util.List;
import java.util.ArrayList;


public class PlayControl {

    private Table table;

    private Play play;

    public PlayControl(List<Team> teams, int teamSize){

        this.play = new Play(teams, teamSize);
        this.table = new Table(teams.size(), teamSize);

    }


    public Team getWinnerTeam(){
        return this.play.getWinnerTeam();
    }


    public void run(int firstPlayerIndex){

        this.play.setPlayValue(PlayValue.NORMAL);

        FsmPlay chain = new FsmPlay();

        chain.setTable(this.table);
        chain.setPlayValue(this.play.getPlayValue());

        for(Player p : this.play.getPlayers()){
            chain.addPlayer(p);
        }

        chain.setFirstIndex(firstPlayerIndex);
        while (!chain.end()) {
            chain.change();
        }

        int winnerTeamId = chain.getWinnerTeamId();

        this.play.setPlayValue(chain.getPlayValue());

        if(winnerTeamId != -1){
            this.play.setWinnerTeam(this.play.getTeams().get(winnerTeamId));
            this.play.getWinnerTeam().addScore(this.play.getPlayValue().getValue());
        }

    }

}

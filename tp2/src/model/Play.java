package model;

import view.PlayObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by math on 5/12/17.
 */
public class Play extends Observable{
    private List<Team> teams;

    private List<Player> players;

    private Team winnerTeam;

    private PlayValue playValue;

    private Observer observer;

    public Play(List<Team> teams, int teamSize) {
        this.teams = teams;

        this.players = new ArrayList<Player>(teams.size()*teamSize);
        this.observer = PlayObserver.getInstance();

        makePlayersList(teams, teamSize);
    }

    private void makePlayersList(List<Team> teams, int teamSize) {
        for (int j = 0; j < teamSize; j++){
            for (int i = 0; i < teams.size(); i++){
                this.players.add(this.teams.get(i).getPlayer(j));
            }
        }
    }

    public Team getWinnerTeam() { return winnerTeam; }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
        this.notifyObservers();
    }

    public PlayValue getPlayValue() { return playValue; }

    public void setPlayValue(PlayValue playValue) {
        this.playValue = playValue;
    }

    public List<Team> getTeams() { return teams; }

    public List<Player> getPlayers() { return players; }

    public void notifyObservers(){
        this.observer.update(this,"etc");
    }

}

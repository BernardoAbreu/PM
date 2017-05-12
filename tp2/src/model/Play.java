package model;

import view.Display;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by math on 5/12/17.
 */
public class Play {
    private List<Team> teams;

    private List<Player> players;

    private Team winnerTeam;

    private PlayValue playValue;

    public Play(List<Team> teams, int teamSize) {
        this.teams = teams;

        this.players = new ArrayList<Player>(teams.size()*teamSize);

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
    }

    public PlayValue getPlayValue() { return playValue; }

    public void setPlayValue(PlayValue playValue) {
        this.playValue = playValue;
    }

    public List<Team> getTeams() { return teams; }

    public List<Player> getPlayers() { return players; }

}

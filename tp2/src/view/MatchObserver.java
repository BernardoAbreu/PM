package view;

import model.Match;
import model.Team;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by math on 5/12/17.
 */
public class MatchObserver implements Observer {

    private MatchObserver(){ }

    private static class MatchObserverHolder{
        private static final MatchObserver INSTANCE = new MatchObserver();
    }

    public static MatchObserver getInstance(){
        return MatchObserverHolder.INSTANCE;
    }
    @Override
    public void update(Observable observable, Object o) {
        Match m = (Match) observable;
        if(m.getWinningTeam().getId() == Team.HUMAN_PLAYER_TEAM){
            TerminalDisplay.getInstance().printString("Parabéns!! Seu time passou na frente e agora possui a maior potuação do jogo! :)" );
        }else{
            TerminalDisplay.getInstance().printString("Infelizmente seu time perdeu a liderança.. :(");
        }
        for(Team team: m.getTeams()){
            TerminalDisplay.getInstance().printString("A pontuação do time: " +  String.valueOf(team.getId()) + " é: " + String.valueOf(team.getScore()));
        }
    }
}

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
            TerminalDisplay.getInstance().printString("Parabéns!! Seu time venceu a jogada! :) Sua pontuação é: " + String.valueOf(m.getWinningTeam().getScore()));
        }else{
            TerminalDisplay.getInstance().printString("Infelizmente seu time perdeu a jogada.. :(");
        }
    }
}

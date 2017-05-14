package view;

import model.Play;
import model.Team;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by math on 5/13/17.
 */
public class PlayObserver implements Observer {

    private PlayObserver(){ }

    private static class PlayObserverHolder{
        private static final PlayObserver INSTANCE = new PlayObserver();
    }

    public static PlayObserver getInstance(){
        return PlayObserver.PlayObserverHolder.INSTANCE;
    }
    @Override
    public void update(Observable observable, Object o) {
        if(observable.getClass() == Play.class){
            Play play = (Play) observable;
            if(play.getWinnerTeam().getId() == Team.HUMAN_PLAYER_TEAM){
                TerminalDisplay.getInstance().printString("Parabéns!! Seu time venceu a jogada! :)" );
            }else{
                TerminalDisplay.getInstance().printString("Infelizmente seu time perdeu a jogada.. :(");
            }
            for(Team team: play.getTeams()){
                TerminalDisplay.getInstance().printString("A pontuação do time: " +  String.valueOf(team.getId()) + " é: " + String.valueOf(team.getScore()));
            }
            TerminalDisplay.getInstance().printString("======================= FIM DA JOGADA ======================= \n\n");
        }else{
            TerminalDisplay.getInstance().printString(String.valueOf(o));
        }
    }
}
